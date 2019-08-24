<?php	
	// simlib.php - HDL Work Bench simulation support code
	// Version 1.00 01/08/14
	// Version 1.10 01/08/15
	// Version 1.11 01/02/17 createForcePeriod(...)
	// (c) Gavin Baker - End House Software 2014 - 2017
	
	$handle = 0;
	$simTime = 0.0;	
	$waitProc = "";
	
	$start = $_GET["start"];
	$end = $_GET["end"];
	$timeunit = $_GET["timeunit"];
	$name = $_GET["entity"];
	$currententity = "$name";
	$currentprocess = "";
	$selexp = "";
	$tmpvalue = "";
	
	$processarrays_get = 0;
	$processarrays_set = 1;
	
	$entityNames = new ArrayObject();
	$functionNames = null;

	if (!isset($langkeywords)) {
		$langkeywords = "";
	}
	
	class builtInType {
		var $data;
	}

	function setSelExp($exp) {
		global $selexp;
		
		$selexp = $exp;
	}
	function getSelExp() {
		global $selexp;
		
		return $selexp;
	}
	
	function setTmpValue($exp) {
		global $tmpvalue;
		
		$tmpvalue = $exp;
	}
	function getTmpValue() {
		global $tmpvalue;
		
		return $tmpvalue;
	}

	function init() {
		global $name,$start,$end,$timeunit;	

		print("<br><br>HDL Work Bench Simulation $name ($start$timeunit to $end$timeunit)");
	}
	
	function rerunwaitchain($processname) {
		global $waitProc;
		
		$waitProc = $processname;
	}
	
	function addProcessName($archname,$processname,$senslist) {
		global $link;	

		$result = mysql_query("INSERT INTO hdlwbprocesses (hdlWBProcessesID,hdlWBProcessesEntity,hdlWBProcessesName,hdlWBProcessesSensList) VALUES (null,'$archname','$processname','$senslist')");
		mysqlError($result);
	}
	
	function composeSignalsEntityConditionString() {
		global $link,$entityNames;
		$ret = "";
		
		$index = 0;
		$iterator = $entityNames->getIterator();
		while($iterator->valid()) {
			$tmp = $iterator->current();
			if ($index > 0) {$ret = $ret . " OR";} else {$ret = $ret . "(";}
			$ret = $ret . " hdlWBSignalsEntity='$tmp'";
			$index++;
			$iterator->next();
		}
		
		$ret = $ret . ")";
		return $ret;
	}

	function followSignalChain($module,$simTime) {
		global $link,$entityNames,$timeunit,$currentprocess;
		
		$funcNames = new ArrayObject();
		
		$cond = composeSignalsEntityConditionString();
		
		// get all the changed signal names 
		$result = mysql_query("SELECT hdlWBSignalsID,hdlWBSignalsEntity,hdlWBSignalsName,hdlWBSignalsValue,hdlWBSignalsOneShot,hdlWBSignalsWaitCondition FROM hdlwbsignals WHERE $cond AND hdlWBSignalsChanged=1 AND hdlWBSignalsDefination='signal'");
		mysqlError($result);
		
		if (mysql_num_rows($result) != 0) {
			while ($row = mysql_fetch_assoc($result)) {
				
				$found = false;
				
				$id = $row["hdlWBSignalsID"];
				$name = $row["hdlWBSignalsName"];
				$entity = $row["hdlWBSignalsEntity"];
				$value = $row["hdlWBSignalsValue"];
				$oneshot = $row["hdlWBSignalsOneShot"];
				$waitcondition = $row["hdlWBSignalsWaitCondition"];
				//print("<br>Signal $entity.$name has been changed<br>");
				
				if ($oneshot != 0) {
					print("<br>Non zero signal one shot value:$oneshot<br>");
					if ($oneshot == 3) {continue;}
				}
				
				// check to see if this signal is connected to any other signals
				// as a result of the use of the portmap VHDL keyword
				$result1 = mysql_query("SELECT hdlWBMappingsID,hdlWBMappingsSubEntity,hdlWBMappingsSubSignalName FROM hdlwbmappings WHERE hdlWBMappingsEntity='$entity' AND hdlWBMappingsSignalName='$name'");
				mysqlError($result1);
		
				if (mysql_num_rows($result1) != 0) {
					while ($row1 = mysql_fetch_assoc($result1)) {
						$subentity = $row1["hdlWBMappingsSubEntity"];
						$subname = $row1["hdlWBMappingsSubSignalName"];
						if ($subentity == "") {break;}
						print("<br>Signal changed: $subentity.$subname - $value");
						setSignal($subentity,$subname,$value);
						$found = true;
					}
				}

				// check if this signal is in the sensitity list of any active VHDL process
				$result2 = mysql_query("SELECT hdlWBMappingsID,hdlWBMappingsProcessName FROM hdlwbmappings WHERE  hdlWBMappingsEntity='$entity' AND hdlWBMappingsSignalName='$name'");
				mysqlError($result2);
				
				if (mysql_num_rows($result2) != 0) {
					while ($row2 = mysql_fetch_assoc($result2)) {
						$procname = $row2["hdlWBMappingsProcessName"];
						if ($procname == "") {break;}
						// store process name in 'funcnames' as processname();
						if (!arrayObjectExists($funcNames,"$procname();")) {$funcNames[] = "$procname();";}
						$found = true;
					}
				}
				
				if ($found == false)  { // signal is not connected to anything or is a top-level output signal
				}
				
				// reset changed on the signal to indicate dealt with
				$result3 = mysql_query("UPDATE hdlwbsignals SET hdlWBSignalsChanged=0 WHERE hdlWBSignalsID=$id");
				mysqlError($result3);
			}
		}
		
		// cycle through 'funcnames' calling 'eval' on each
		$iterator = $funcNames->getIterator();
		while($iterator->valid()) {
			$tmp = $iterator->current();
			print("<br>Process called: $tmp at $simTime$timeunit");
			$currentprocess = substr($tmp,0,strlen($tmp)-3);
			eval("$tmp");
			$iterator->next();
		}
	}
	
	function checkUntilSignals($module,$simTime) {
		global $link;
		
		$cond = composeSignalsEntityConditionString();
		$result = mysql_query("SELECT hdlWBSignalsID,hdlWBSignalsName,hdlWBSignalsWaitCondition FROM hdlwbsignals WHERE $cond AND hdlWBSignalsWaitCondition!='none' AND hdlWBSignalsDefination='signal'");
		mysqlError($result);
		while ($row = mysql_fetch_assoc($result)) {
			$retval = 0;
			$tmp = $row["hdlWBSignalsWaitCondition"];
			eval("if ($tmp) {\$retval= 1;}");
			if ($retval == 1) {
				$id = $row["hdlWBSignalsID"];
				$result = mysql_query("UPDATE hdlwbsignals SET hdlWBSignalsWaitCondition='none' WHERE hdlWBSignalsID=$id");
				mysqlError($result);
				$sname = $row["hdlWBSignalsName"];
				setSignal($module,$sname,"1");
			}
		}
	}
	
	function testSignal($scope,$subscope,$signame,$sigvalue,$sigtime) {
		return false;
	}

	function checkDelayedSignals($module,$simTime) {
		global $link,$entityNames;
		
		$iterator = $entityNames->getIterator();
		while($iterator->valid()) {
			$tmp = $iterator->current();
			$result = mysql_query("SELECT hdlWBSignalsID,hdlWBSignalsName,hdlWBSignalsAfterValue,hdlWBSignalsDelay FROM hdlwbsignals WHERE hdlWBSignalsEntity='$tmp' AND hdlWBSignalsDelay>0 AND hdlWBSignalsDefination='signal'");
			mysqlError($result);
			
			if (mysql_num_rows($result) != 0) {
				while ($row = mysql_fetch_assoc($result)) {
					$delay = $row["hdlWBSignalsDelay"];
					$delay--;
					if ($delay == 0) {
						setSignal($tmp,$row["hdlWBSignalsName"],$row["hdlWBSignalsAfterValue"]);
						print("<br>Delayed Signal " . $row["hdlWBSignalsName"] . " changed to " . $row["hdlWBSignalsAfterValue"]);
					} else {
						$id = $row["hdlWBSignalsID"];
						$result1 = mysql_query("UPDATE hdlwbsignals SET hdlWBSignalsDelay=$delay WHERE hdlWBSignalsID=$id");
						mysqlError($result1);
					}
				}
			}
			$iterator->next();
		}
	}
	
	function checkForces($module,$simTime) {
		global $link,$timeunit;
		
		$result = mysql_query("SELECT hdlWBForcesName,hdlWBForcesValue FROM hdlwbforces WHERE hdlWBForcesEntity='$module' AND hdlWBForcesTime=$simTime");
		mysqlError($result);
		
		if (mysql_num_rows($result) != 0) {
			while ($row = mysql_fetch_assoc($result)) {
				setSignal($module,$row["hdlWBForcesName"],$row["hdlWBForcesValue"]);
				print("<br>Signal (Force): " . $row["hdlWBForcesName"] . " changed to " . $row["hdlWBForcesValue"] . " at $simTime$timeunit");
			}
		}
	}
	
	function writePlotData($signame,$bitorbus,$value,$time) {
		global $handle;
		
		fputs($handle,"$signame,$bitorbus,$value,$time\n");
	}

	function is_keyword($token) {
		global $langkeywords;
		
		$keywords = new ArrayObject();
		$keywords = explode(",", $langkeywords);
		$key = array_search($token,$keywords);
		if ($key == false) {return false;}
		return true;
	}
	function is_function($token) {
		global $functionNames;

		if (arrayObjectExists($functionNames,$token)) {return true;}
		return false;
	}
	function is_arrayname($entity,$token) {
		$pieces = explode(",",getArrayNames());
		for($i=0;$i<count($pieces);$i=$i+2) {
			if ($pieces[$i] == $entity && $pieces[$i+1] == $token) {return true;}
		}
	
		return false;
	}
	
	function timeUnitConvert($conTimeValue,$conTimeUnit) {
		global $timeunit;

		$arrTimeUnits = array(1 => "ps", 2 => "ns",3 => "us",4 => "ms",5 => "secs");
		
		$simKey = array_search("$timeunit", $arrTimeUnits);
		$conKey = array_search("$conTimeUnit", $arrTimeUnits);
		if ($simKey==false) {return 0;}
        if ($conKey==false) {return 0;}
		
		if ($simKey == $conKey) {return $conTimeValue;}
		if ($simKey > $conKey) {
			return $conTimeValue / pow(10,((abs($simKey-$conKey))*3));
		} else {
			return $conTimeValue * pow(10,((abs($simKey-$conKey))*3));
		}
	}

	function preSimulate($module) {
		global $handle, $simTime;
		global $start,$end,$timeunit;
		
		if (!isset($start)) {
			print("<br>Start time must be specified");
			exit();
		}
		if (!isset($end)) {
			print("<br>End time must be specified");
			exit();
		}
		
		if ($start > $end) {
			print("<br>End time has to be greater then start time");
			exit();
		}
		
		if (!isset($timeunit)) {
			print("<br>Time unit has to be specified");
			exit();
		}
		if ($timeunit != "ps" && $timeunit != "ns" && $timeunit != "us" && $timeunit != "ms" && $timeunit != "secs") {
			print("<br>Time unit can only be ps,ns,us,ms or secs");
			exit();
		}
		
		$simTime = $start;
		
		initSignals();
		storeProcessNames();
		
		$handle = fopen("results/$module.res", "w");
		$dentered = date("Y-m-d");
		$tentered = date("H:i:s");
		fputs($handle,"$dentered $tentered\n");
		fputs($handle,"$timeunit\n");
		fputs($handle,"$start\n");
				
		print("<br><br>Simulation File: $module<br>Simulation Starts At $start$timeunit");
		
		$data = getNoNameProcesses();
		if (strlen($data) != 0) { 
			$pieces = explode(",",$data);
			print("<br><br>Checking no name processes...");
			foreach ($pieces as $value) {
				$pname = $value . "();";
				print("<br>Running process $pname");
				eval("$pname");
			}
		}
	}
	
	function testfunc() {
	 return 3;
	}
	
	function postSimulate($module) {
		global $handle,$end,$entityNames,$timeunit;
		
		fputs($handle,"$end\n");
		
		fclose($handle);
		print("<br>Simulation Ends At $end$timeunit");

		$iterator = $entityNames->getIterator();
		while($iterator->valid()) {
			$tmp = $iterator->current();
			clearEntityDBEntries($tmp);
			print("<br><br>Processed Entity $tmp ...");
			$iterator->next();
		}
	}
	
	function clearDatabase($module) {
			print "<br><br>Clearing database entries for module $module";
			// getEntities() is in the PHP of the user's VHDL code and is created by postCompile(...)
			$pieces = explode(",", getEntities()); 
			foreach ($pieces as $value) {
				print "<br>Clearing data for entity $value";
				clearEntityDBEntries($value);
			}
			//clearEntityDBEntries($module); // getEntities() returns the modulename(entity) as the first element
			print "<br>Clearing data for entity $module";
	}
	function setArrayValue($module,$arrayname,$index,$value) {
		updateSignalArrayValue($module,$arrayname,$index,$value);
	}
	function getArrayValue($module,$arrayname,$index) {
		return updateSignalArrayValue($module,$arrayname,$index,"");
	}
	function updateSignalArrayValue($module,$signalname,$index,$newvalue) {
		global $link;
		
		$ub = 0;
		$lb = 0;
		$id = 0;
		$line = "";
		$value = "";
		$found = false;
		
		$index = eval("return $index;");
		
		$result = mysql_query("SELECT hdlWBSignalsArrayValues,hdlWBSignalsID,hdlWBSignalsLB,hdlWBSignalsUB FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$signalname'");
		mysqlError($result);
		while($row = mysql_fetch_assoc($result)) {
			$line = $row["hdlWBSignalsArrayValues"];
			$id = $row["hdlWBSignalsID"];
			$ub = $row["hdlWBSignalsUB"];
			$lb = $row["hdlWBSignalsLB"];
			$pieces = explode(",",$line);
			for ($i=0;$i<count($pieces);$i=$i+2) {
				if ($pieces[$i] == $index) {
					$found = true;
					$value = $pieces[$i + 1];
					break;
				}
			}
			if ($index < $lb || $index > $ub) {die("Array index out of bounds $signalname[$index]");}
			if (strlen($newvalue) == 0) { //get value
				return $value;
			} else { //set value
				if ($found == true) { //replace value
					$line = str_replace("$index,$value","$index,$newvalue",$line);
				} else { //append value
					if (strlen($line) != 0) {$line = $line . ",";}
					$line = $line . "$index,$newvalue";
				}
			}
		}
		
		$result = mysql_query("UPDATE hdlwbsignals SET hdlWBSignalsArrayValues='$line' WHERE hdlWBSignalsID = $id");
		mysqlError($result);
		
		return $newvalue;
	}
	function getSimTime() {
		global $simTime;

		return $simTime;
	}
	function simulate($module) {
		global $simTime,$start,$end;
		
			init();
		
			preSimulate($module);
			do {
				if (strlen($waitProc) !=0) {
					exec("$waitProc();");
					$waitProc = "";
				}
				
				checkForces($module,$simTime);
				checkDelayedSignals($module,$simTime);
				checkUntilSignals($module,$simTime);
				
				$count = 0;
				do { // while there are still signals marked as changed ...
					followSignalChain($module,$simTime);
					$cond = composeSignalsEntityConditionString();
					$result = mysql_query("SELECT hdlWBSignalsID FROM hdlwbsignals WHERE $cond AND hdlWBSignalsChanged=1 AND hdlWBSignalsDefination='signal'");
					mysqlError($result);
				    $count = mysql_num_rows($result);
				} while ($count != 0);
				
				$cond = composeSignalsEntityConditionString();
				$result = mysql_query("UPDATE hdlwbsignals SET hdlWBSignalsChanged=0 WHERE $cond AND hdlWBSignalsDefination='constant'");
				mysqlError($result);
				$result = mysql_query("UPDATE hdlwbsignals SET hdlWBSignalsChanged=0 WHERE $cond AND hdlWBSignalsDefination='variable'");
				mysqlError($result);
				
				$simTime = $simTime + 1;
			} while ($end > $simTime);
			postSimulate($module);
	}
	function clearEntityDBEntries($module) {
		global $link;
		
		$result = mysql_query("DELETE FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module'");
		mysqlError($result);
		$result = mysql_query("DELETE FROM hdlwbforces WHERE hdlWBForcesEntity='$module'");
		mysqlError($result);
		$result = mysql_query("DELETE FROM hdlwbprocesses WHERE hdlWBProcessesEntity='$module'");
		mysqlError($result);
		$result = mysql_query("DELETE FROM hdlwbmappings WHERE hdlWBMappingsEntity='$module'");
		mysqlError($result);
		$result = mysql_query("DELETE FROM hdlwbparameters WHERE hdlWBParametersEntity='$module'");
		mysqlError($result);
	}
	function createForce($module,$signalname,$tiggertime,$value) {
		global $link,$entityNames;

		if (!arrayObjectExists($entityNames,$module)) {$entityNames[] = "$module";}
		print("<br><br>Force Created:<br>Module:$module<br>Force Name:$signalname<br>Force Trigger Time:$tiggertime<br>Force Value:$value");

		$result = mysql_query("INSERT INTO hdlwbforces (hdlWBForcesID,hdlWBForcesEntity,hdlWBForcesName,hdlWBForcesTime,hdlWBForcesValue) VALUES (null,'$module','$signalname',$tiggertime,$value)");
		mysqlError($result);
	}
	function createForcePeriod($module,$signalname,$startValue,$startTime,$endTime,$periodTime) {
		for ($x = $startTime; $x <= $endTime; $x += $periodTime) {
			createForce($module,$signalname,$startValue,$x);
			$startValue = !$startValue;
		}
	}
	function deleteSignal($module,$signalname) {
		global $link;
		
		$result = mysql_query("DELETE FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$signalname'");
		mysqlError($result);		
	}
	function createSignal($module,$scope,$signalname,$mode,$type,$port,$default) {
		global $link,$entityNames,$simTime,$currentprocess;
		
		if ($default = "") {$default = "0";}
		
		if (!arrayObjectExists($entityNames,$module)) {$entityNames[] = "$module";}
		print("<br><br>Signal Created:<br>Module:$module<br>SignalName:$signalname<br>Signal Mode:$mode<br>Signal Type:$type<br>Signal Port:$port<br>Signal Default:$default<br>Signal Scope:$scope");
		
		$pieces = explode(",", $type);
		if (count($pieces) != 11) {print("<br><br>");exit("Invalid type string in createSignal - $type");}
		$type 		= $pieces[0];
		$lowerBound = $pieces[1];
		$upperBound = $pieces[2];
		$lowerRange = $pieces[3];
		$upperRange = $pieces[4];
		$subScope 	= $pieces[5];
		$lsr 		= $pieces[6];
		$usr 		= $pieces[7];
		$bEnum		= $pieces[8];
		$bArray		= $pieces[9];
		$bDynamic	= $pieces[10];
		
		$result = mysql_query("INSERT INTO hdlwbsignals (hdlWBSignalsID,hdlWBSignalsEntity,hdlWBSignalsName,hdlWBSignalsValue,hdlWBSignalsType,hdlWBSignalsMode,hdlWBSignalsPort,hdlWBSignalsDefination,hdlWBSignalsUser,hdlWBSignalsScope,hdlWBSignalsLB,hdlWBSignalsUB,hdlWBSignalsLR,hdlWBSignalsUR,hdlWBSignalsSubScope,hdlWBSignalsOldValue,hdlWBSignalsChangeTime,hdlWBSignalsActiveTime,hdlWBSignalsEventTime,hdlWBSignalsHistory,hdlWBSignalsStringLR,hdlWBSignalsStringUR,hdlWBSignalsEnum,hdlWBSignalsArray) VALUES (null,'$module','$signalname','$default','$type','$mode',$port,'signal','default','$scope',$lowerBound,$upperBound,$lowerRange,$upperRange,'$subScope','X',$simTime,-1,-1,'$module:$currentprocess:$signalname:X:-1','$lsr','$usr',$bEnum,$bArray)");
		mysqlError($result);
	}
	function createParameter($module,$scope,$name,$type,$pos,$mode,$return,$lb,$ub,$default) {
		global $link,$entityNames;

		print("<br><br>Parameter Created:<br>Module:$module<br>Scope:$scope<br>Parameter Name:$name<br>Parameter Type:$type<br>Parameter Pos:$pos<br>Parameter Mode:$mode<br>Parameter Return:$return<br>Parameter LB:$lb<br>Parameter UB:$ub<br>Parameter Default:$default");

		$pieces = explode(",", $type);
		if (count($pieces) != 11) {print("<br><br>");exit("Invalid type string in createParameter- $type");}
		$type 		= $pieces[0];
		$lowerBound = $pieces[1];
		$upperBound = $pieces[2];
		$lowerRange = $pieces[3];
		$upperRange = $pieces[4];
		$subScope 	= $pieces[5];
		$lsr 		= $pieces[6];
		$usr 		= $pieces[7];
		$bEnum		= $pieces[8];
		$bArray		= $pieces[9];
		$bDynamic	= $pieces[10];
	
		$result = mysql_query("INSERT INTO hdlwbparameters (hdlWBParametersID,hdlWBParametersEntity,hdlWBParametersName,hdlWBParametersType,hdlWBParametersScope,hdlWBParametersPosition,hdlWBParametersMode,hdlWBParametersDefault,hdlWBParametersLB,hdlWBParametersUB,hdlWBParametersReturn) VALUES (null,'$module','$name','$type','$scope',$pos,'$mode','$default',$lb,$ub,$return)");
		mysqlError($result);
	}
	function createVariable($module,$scope,$signalname,$mode,$type,$port,$default) {
		global $link,$entityNames;
		
		if (!arrayObjectExists($entityNames,$module)) {$entityNames[] = "$module";}
		print("<br><br>Variable Created:<br>Module:$module<br>Varaiable Name:$signalname<br>Varaiable Mode:$mode<br>Varaiable Type:$type<br>Varaiable Port:$port<br>Varaiable Default:$default<br>Varaiable Scope:$scope");
		
		$pieces = explode(",", $type);
		if (count($pieces) != 11) {print("<br><br>");exit("Invalid type string in createVariable - $type");}
		$type 		= $pieces[0];
		$lowerBound = $pieces[1];
		$upperBound = $pieces[2];
		$lowerRange = $pieces[3];
		$upperRange = $pieces[4];
		$subScope 	= $pieces[5];
		$lsr 		= $pieces[6];
		$usr 		= $pieces[7];
		$bEnum		= $pieces[8];
		$bArray		= $pieces[9];
		$bDynamic	= $pieces[10];

		$result = mysql_query("INSERT INTO hdlwbsignals (hdlWBSignalsID,hdlWBSignalsEntity,hdlWBSignalsName,hdlWBSignalsValue,hdlWBSignalsType,hdlWBSignalsMode,hdlWBSignalsPort,hdlWBSignalsDefination,hdlWBSignalsUser,hdlWBSignalsScope,hdlWBSignalsLB,hdlWBSignalsUB,hdlWBSignalsLR,hdlWBSignalsUR,hdlWBSignalsSubScope,hdlWBSignalsOldValue,hdlWBSignalsChangeTime,hdlWBSignalsActiveTime,hdlWBSignalsEventTime,hdlWBSignalsHistory,hdlWBSignalsStringLR,hdlWBSignalsStringUR,hdlWBSignalsEnum,hdlWBSignalsArray) VALUES (null,'$module','$signalname','$default','$type','$mode',$port,'signal','default','$scope',$lowerBound,$upperBound,$lowerRange,$upperRange,'$subScope','X',$simTime,-1,-1,'$module:$currentprocess:$signalname:X:-1','$lsr','$usr',$bEnum,$bArray)");
		mysqlError($result);
	}
	function createConstant($module,$scope,$signalname,$type,$port,$default) {
		global $link,$entityNames;
		
		if (!arrayObjectExists($entityNames,$module)) {$entityNames[] = "$module";}
		print("<br><br>Constant Created<br>Module:$module<br>Constant Name:$signalname,<br>Constant Mode:in<br>Constant Type:$type<br>Constant Port:$port<br>Constant Default:$default<br>Constant Scope:$scope");
		
		$result = mysql_query("INSERT INTO hdlwbsignals (hdlWBSignalsID,hdlWBSignalsEntity,hdlWBSignalsName,hdlWBSignalsValue,hdlWBSignalsType,hdlWBSignalsMode,hdlWBSignalsPort,hdlWBSignalsDefination,hdlWBSignalsUser,hdlWBSignalsScope) VALUES (null,'$module','$signalname','$default','$type','in',$port,'constant','default','$scope')");
		mysqlError($result);
	}
	function getCurrentEntity() {
		global 	$currententity;
		
		return "$currententity";
	}
	function getCurrentProcess() {
		global 	$currentprocess;
		
		return "$currentprocess";
	}
	function getSignalWaitCond($module,$signalname) {
		global $link;
		$tmp = "";
		
		//$result = mysql_query("SELECT hdlWBSignalsWaitCondition FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$signalname' AND hdlWBSignalsDefination='signal'");
		$result = mysql_query("SELECT hdlWBSignalsWaitCondition FROM hdlwbsignals WHERE hdlWBSignalsName='$signalname'");
		mysqlError($result);
		if (mysql_num_rows($result) != 0) {
			while ($row = mysql_fetch_assoc($result)) {
				$tmp = $row["hdlWBSignalsWaitCondition"];
			}
		}
		return $tmp;
	}
	function getSignalOneShot($module,$signalname) {
		global $link;
		$tmp = "";
		
		//$result = mysql_query("SELECT hdlWBSignalsOneShot FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$signalname' AND hdlWBSignalsDefination='signal'");
		$result = mysql_query("SELECT hdlWBSignalsOneShot FROM hdlwbsignals WHERE hdlWBSignalsName='$signalname'");
		mysqlError($result);
		if (mysql_num_rows($result) != 0) {
			while ($row = mysql_fetch_assoc($result)) {
				$tmp = $row["hdlWBOneShot"];
			}
		}
		return $tmp;
	}
	function getSignal($module,$signalname) {
		return getSignal1($module,$signalname,-1);
	}
	function getVariable($module,$signalname) {
		return getVariable1($module,$signalname,-1);
	}
	function getConstant($module,$signalname) {
		return getConstant1($module,$signalname,-1);
	}
	function setSignal($module,$name,$value) {
		setSignal1($module,$name,$value,-1);
	}
	function setVariable($module,$name,$value) {
		setVariable1($module,$name,$value,-1);
	}
	function setConstant($module,$name,$value) {
		setConstant1($module,$name,$value,-1);
	}
	function getSignal1($module,$signalname,$index) {
		global $link;
		$tmp = "";
				
		//$result = mysql_query("SELECT hdlWBSignalsValue FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$signalname' AND hdlWBSignalsDefination='signal'");
		$result = mysql_query("SELECT hdlWBSignalsValue FROM hdlwbsignals WHERE hdlWBSignalsName='$signalname'");
		mysqlError($result);
		if (mysql_num_rows($result) != 0) {
			while ($row = mysql_fetch_assoc($result)) {
				if ($index != -1) {
					$tmp = updateSignalArrayValue($module,$signalname,$index,"");
				} else {
					$tmp = $row["hdlWBSignalsValue"];
				}
			}
		}
		//print("<br>module=$module, signal=$signalname and value=$tmp<br>");
		return $tmp;
	}
	function getValueAsBinaryString($value) {
		return str_pad(decbin($value),8, "0", STR_PAD_LEFT);
	}
	function getValueAsHexString($value) {
		return str_pad(dechex($value),8, "0", STR_PAD_LEFT);
	}
	function getVariable1($module,$signalname,$index) {
		global $link;
		$tmp = "";
		
		$result = mysql_query("SELECT hdlWBSignalsValue FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$signalname' AND hdlWBSignalsDefination='variable'");
		mysqlError($result);
		if (mysql_num_rows($result) != 0) {
			while ($row = mysql_fetch_assoc($result)) {
				if ($index != -1) {
					$tmp = updateSignalArrayValue($module,$signalname,$index,"");
				} else {
					$tmp = $row["hdlWBSignalsValue"];
				}
			}
		}
		return $tmp;
	}
	function getConstant1($module,$signalname,$index) {
		global $link;
		$tmp = "";
		
		$result = mysql_query("SELECT hdlWBSignalsValue FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$signalname' AND hdlWBSignalsDefination='constant'");
		mysqlError($result);
		if (mysql_num_rows($result) != 0) {
			while ($row = mysql_fetch_assoc($result)) {
				if ($index != -1) {
					$tmp = updateSignalArrayValue($module,$signalname,$index,"");
				} else {
					$tmp = $row["hdlWBSignalsValue"];
				}
			}
		}
		return $tmp;
	}
	function setDelayedSignal($module,$signalname,$signalvalue,$signaltime) {
		// signalTime is assumed to be converted to same time unit as used in the simulation run
		global $link,$simTime,$timeunit;

		$oldValue = getSignal($module,$signalname);

		$result = mysql_query("SELECT hdlWBSignalsID FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$signalname' AND hdlWBSignalsDefination='signal'");
		mysqlError($result);
		$row = mysql_fetch_assoc($result);
		$id = $row["hdlWBSignalsID"];

		//print("<br>module=$module, signal=$signalname, oldvalue=$oldValue and id=$id<br>");
		$result = mysql_query("REPLACE INTO hdlwbsignals (hdlWBSignalsID,hdlWBSignalsEntity,hdlWBSignalsName,hdlWBSignalsDefination,hdlWBSignalsChanged,hdlWBSignalsDelay,hdlWBSignalsAfterValue,hdlWBSignalsOldValue,hdlWBSignalsUser) VALUES ($id,'$module','$signalname','signal',0,$signaltime,'$signalvalue','$oldvalue','default')");
		mysqlError($result);

		print("<br>Delayed Signal Set: $module.$signalname ($signalvalue) at $signaltime $timeunit");
	}
	function setSignalWaitCond($module,$signalname,$signalwaitcond) {
		global $link,$simTime,$timeunit;
		
		$result = mysql_query("UPDATE hdlwbsignals SET hdlWBSignalsWaitCondition='$signalwaitcond' WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$signalname' AND hdlWBSignalsDefination='signal'");
		mysqlError($result);
	}
	function setSignalOneShot($module,$signalname,$signalvalue) {
		global $link,$simTime,$timeunit;
		
		$result = mysql_query("UPDATE hdlwbsignals SET hdlWBSignalsOneShot=$signalvalue WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$signalname' AND hdlWBSignalsDefination='signal'");
		mysqlError($result);
	}
	function getSignalHistoryAtTime($module,$signalname,$time) {
		global $link,$simTime,$timeunit;

		$result = mysql_query("SELECT hdlWBSignalsHistory FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$signalname' AND hdlWBSignalsDefination='signal'");
		mysqlError($result);
		$row = mysql_fetch_assoc($result);
		$history = $row["hdlWBSignalsHistory"];
		$pieces = explode(",", $history);
		foreach ($pieces as $value) {
			list($sigmodule, $sigprocess, $signame, $sigvalue, $sigtime) = explode(":", $value);
			if ($time >= $sigtime) {return $sigvalue;}
		}
		
		return "";
	}
	function getAllSignals($module) {
		global $link;
		$tmp = "";

		$result = mysql_query("SELECT hdlWBSignalsName FROM hdlwbsignals hdlWBSignalsEntity='$module'");
		mysqlError($result);
		while ($row = mysql_fetch_assoc($result)) {
			if (strlen($tmp) != 0) {$tmp = $tmp . ",";}
			$tmp = $tmp . $row["hdlWBSignalsName"];
		}

		return $tmp;
	}
	function processGroupSignals($module,$signalname,$signalvalue) {
		global $link;
		
		$signals = getAllSignals($module);
		if ($signalname == "others") {
			die("Signal 'others' - NYI");
		}

		$pieces = explode(",", $signals);
		foreach ($pieces as $signal) {
			setSignal($module,$signal,$signalvalue);
		}
	}
	function setSignal1($module,$signalname,$signalvalue,$index) {
		global $link,$simTime,$timeunit,$currentprocess;
		
		if ($index != -1) {die("Signal arrays - NYI");}

		if ($signalname == "others" || $signalname == "all") {
			processGroupSignals($module,$signalname,$signalvalue);
			return "";
		}
		
		$oldValue = getSignal($module,$signalname,$index);
		$dbField = "hdlWBSignalsEventTime";
		if ($oldValue == $signalvalue) {$dbField = "hdlWBSignalsActiveTime";}
		
		$result = mysql_query("SELECT hdlWBSignalsID,hdlWBSignalsHistory FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$signalname' AND hdlWBSignalsDefination='signal'");
		mysqlError($result);
		$row = mysql_fetch_assoc($result);
		$id = $row["hdlWBSignalsID"];
		$history = $row["hdlWBSignalsHistory"];
		$history = $history . ",$module:$currentprocess:$signalname:$signalvalue:$simTime";
		$result = mysql_query("REPLACE INTO hdlwbsignals (hdlWBSignalsID,hdlWBSignalsEntity,hdlWBSignalsName,hdlWBSignalsValue,hdlWBSignalsDefination,hdlWBSignalsChanged,hdlWBSignalsDelay,hdlWBSignalsOldValue,hdlWBSignalsUser,hdlWBSignalsChangeTime,$dbField,hdlWBSignalsHistory) VALUES ($id,'$module','$signalname','$signalvalue','signal',1,0,'$oldvalue','default',$simTime,$simTime,'$history')");
		mysqlError($result);

		$result1 = mysql_query("SELECT hdlWBSignalsLB,hdlWBSignalsUB FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$signalname' AND hdlWBSignalsDefination='signal'");
		mysqlError($result);
		if (mysql_num_rows($result1) != 0) {
			while ($row = mysql_fetch_assoc($result1)) {
				$bitbus = 0;
				if($row["hdlWBSignalsUB"]-$row["hdlWBSignalsLB"] != 0) {$bitbus = 1;}
				writePlotData("$module.$signalname",$bitbus,$signalvalue,$simTime);
			}
		}
		
		print("<br>Signal Set: $module.$signalname ($signalvalue) at $simTime$timeunit");
	}
	function setVariable1($module,$variablename,$variablevalue,$index) {
		global $link;

		$result = mysql_query("SELECT hdlWBSignalsID FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$variablename' AND hdlWBSignalsDefination='variable'");
		mysqlError($result);
		$row = mysql_fetch_assoc($result);
		$id = $row["hdlWBSignalsID"];

		if ($index != -1) {
			updateSignalArrayValue($module,$variablename,$index,$variablevalue);
		}
		
		$result = mysql_query("REPLACE INTO hdlwbsignals (hdlWBSignalsID,hdlWBSignalsEntity,hdlWBSignalsName,hdlWBSignalsValue,hdlWBSignalsDefination,hdlWBSignalsChanged) VALUES ($id,'$module','$variablename','$variablevalue','variable',1)");
		mysqlError($result);
	}
	function setConstant1($module,$variablename,$variablevalue,$index) {
		global $link;

		$result = mysql_query("SELECT hdlWBSignalsID FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$variablename' AND hdlWBSignalsDefination='constant'");
		mysqlError($result);
		$row = mysql_fetch_assoc($result);
		$id = $row["hdlWBSignalsID"];

		if ($index != -1) {
			updateSignalArrayValue($module,$variablename,$index,$variablevalue);
		}

		$result = mysql_query("REPLACE INTO hdlwbsignals (hdlWBSignalsID,hdlWBSignalsEntity,hdlWBSignalsName,hdlWBSignalsValue,hdlWBSignalsDefination,hdlWBSignalsChanged) VALUES ($id,'$module','$variablename','$variablevalue','constant',1)");
		mysqlError($result);
	}
	function processCondAsg($module,$str) {
		global $selexp;
		
		$substring = strchr($str,"when");
		if ($substring != "") {
			if (getSelExp() != "") { // select
				$pieces = explode(",",$str);
				foreach ($pieces as $piece) {
					preg_match_all("((\\w+)\\swhen\\s(.*));",$str,$matches,PREG_SET_ORDER);
					if ($matches[3] == "others") {
						$str = $matches[2];
						break; // out of foreach
					}
					$evalExp = "if (" . getSelExp() . "==" . $matches[3] . ") {\$str=\$matches[2];break;}";
					eval("$evalExp");
				}
				setSelExp("");
			} else { // cond. asgn.
				preg_match_all("((\\w+)\\swhen\\s(.*));",$str,$matches,PREG_SET_ORDER);
				$evalExp = $matches[3];
				preg_match_all("(\\((.*)\\)",$evalExp,$matches1,PREG_SET_ORDER);
				if (count($matches1) > 0) {// function
					if (eval("$matches[3];")) {$str=$matches[2];} else {$str = "";}
				} else { // cond. asgn.
					$evalExp = "if (" . $matches[3] . ") {\$str = \$matches[2];} else {\$str = \"\";}";
					$evalExp = str_replace("=","==",$evalExp);
					eval("$evalExp");
				}
			}
		}
		return $str;
	}
	function processArrays($module,$str,$op) {
		global $processarrays_set,$processarrays_get;
		
		setTmpValue(-1); // default - no index
		$pattern = "/((\w+)\s*\\((.*)\\))/";
		preg_match_all($pattern,$str,$matches,PREG_SET_ORDER);
		foreach($matches as $match) {
			if (is_arrayname($match[2])) {
				if ($op == $processarrays_get) {
					$str = str_replace($match[1],"getArrayValue(getCurrentEntity(),$match[2],$match[3])",$str);
				}
				if ($op == $processarrays_set) {
					$str = $match[2];
					setTmpValue($match[3]); // index
				}
			}
		}
		
		return $str;
	}
	function processVariable($module,$firstPart,$secondPart) {
		global $link;
		
		$varName = processArrays($module,$firstPart,$processarrays_set);
		$index = getTmpValue();
		$tmp = processArrays($module,$secondPart,$processarrays_get);
		$tmp = processCondAsg($module,$tmp);
		if ($tmp == "") {return;}
		eval("\$varValue = $tmp");
		setVariable($module,$varName,$varValue,$index);
	}
	function processSignal($module,$firstPart,$secondPart,$delayPart) {
		global $link;
		
		$signalName = processArrays($module,$firstPart,$processarrays_set);
		$index = getTmpValue();
		$tmp = processArrays($module,$secondPart,$processarrays_get);
		$tmp = processCondAsg($module,$tmp);
		if ($tmp == "") {return;}
		eval("\$signalValue = $tmp");
		
		if ($delayPart == "") {
			setSignal($module,$signalName,$signalValue,$index);
		} else {
			list($tcommand, $tvalue, $tunit) = explode(":", $delayPart);
			$tvalue = timeUnitConvert($tvalue,$tunit);
			setDelayedSignal($module,$signalName,$signalValue,$tvalue);
		}
	}
	function processWaitUntilSignal($module,$signalName,$waituntilsignaldef) {
		setSignalWaitCond($module,$signalName,$waituntilsignaldef);
	}
	function processWaitForSignal($waitforsignaldef) {
		list($module, $signalName, $tvalue, $tunit) = explode(":", $waitforsignaldef);
		$tvalue = timeUnitConvert($tvalue,$tunit);
		setDelayedSignal($module,$signalName,'1',$tvalue);
	}
	function createSSMap($module,$signalname,$submodule,$subsignalname) {
		global $link,$entityNames;
		
		$subsignalname = str_replace(" ","",$subsignalname);
		$signalname = trim($signalname);
		$signalname = str_replace(" : ",":",$signalname);
		$signalname = str_replace(" ; ",";",$signalname);
		
		if (!arrayObjectExists($entityNames,$module)) {$entityNames[] = "$module";}
		
		$index = 0;
		$pieces = explode(",", $subsignalname);
		foreach ($pieces as $value) {
			$pieces1 = explode(";", $signalname);
			$value1 = explode(":",$pieces1[$index]);
			$dir = explode(" ",$value1[1]);
			$dir = $dir[0];
			$value1 = $value1[0];
			
			$tmodule = $module;
			$tsubmodule = $submodule;
			$tsignal = $value1;
			$tsubsignal = $value;
			if ($dir == "in") { // for an 'in' signal we need to swap 
				$tmodule = $submodule;
				$tsubmodule = $module;
				$tsignal = $value;
				$tsubsignal = $value1;
			}
			print("<br><br>Signal to Signal Map Created:dir=$dir,mod=$tmodule,signal=$tsignal,submod=$tsubmodule,subsignal=$tsubsignal");
			$result = mysql_query("INSERT INTO hdlwbmappings (hdlWBMappingsID,hdlWBMappingsEntity,hdlWBMappingsSignalName,hdlWBMappingsSubEntity,hdlWBMappingsSubSignalName) VALUES (null,'$tmodule','$tsignal','$tsubmodule','$tsubsignal')");
			mysqlError($result);
			
			$index = $index + 1;
		}
	}
	function deleteSPMap($module,$processname,$signalname) {
		global $link;

		$result = mysql_query("DELETE FROM hdlwbmappings WHERE hdlWBMappingsEntity='$module' AND hdlWBMappingsSignalName='$signalname' AND hdlWBMappingsProcessName='$processname'");
		mysqlError($result);
	}
	function createSPMap($module,$processname,$signalname) {
		global $link,$entityNames;

		if (strlen($signalname) == 0) {return;}
		
		if (!arrayObjectExists($entityNames,$module)) {$entityNames[] = "$module";}
		
		$result = mysql_query("INSERT INTO hdlwbmappings (hdlWBMappingsID,hdlWBMappingsEntity,hdlWBMappingsSignalName,hdlWBMappingsProcessName) VALUES (null,'$module','$signalname','$processname')");
		mysqlError($result);
	}
?>

