<?php
	function last_value($signal) {
		global $link;
		$entity = getCurrentEntity();

		$result = mysql_query("SELECT hdlWBSignalsOldValue FROM hdlwbsignals WHERE hdlWBSignalsEntity='$entity' AND hdlWBSignalsName='$signal' AND hdlWBSignalsDefination='signal'");
		if (mysql_num_rows($result) != 0) {
			while ($row = mysql_fetch_assoc($result)) {
				$tmp = $row["hdlWBSignalsOldValue"];
			}
			return $tmp;
		}
		
		return "0";
	}
	function event($signal) {
		global $link;

		$entity = getCurrentEntity();
		$result = mysql_query("SELECT hdlWBSignalsChangeTime,hdlWBSignalsActiveTime,hdlWBSignalsEventTime FROM hdlwbsignals WHERE hdlWBSignalsEntity='$entity' AND hdlWBSignalsName='$signal' AND hdlWBSignalsDefination='signal'");
		$row = mysql_fetch_assoc($result);
		$changetime = $row["hdlWBSignalsChangeTime"];
		$eventtime = $row["hdlWBSignalsEventTime"];
		
		if ($changetime == $eventtime) {return true;}
		return false;
	}
	function active($signal) {
		global $link;

		$entity = getCurrentEntity();
		$result = mysql_query("SELECT hdlWBSignalsChangeTime,hdlWBSignalsActiveTime,hdlWBSignalsEventTime FROM hdlwbsignals WHERE hdlWBSignalsEntity='$entity' AND hdlWBSignalsName='$signal' AND hdlWBSignalsDefination='signal'");
		$row = mysql_fetch_assoc($result);
		$changetime = $row["hdlWBSignalsChangeTime"];
		$activetime = $row["hdlWBSignalsActiveTime"];
		
		if ($changetime == $activetime) {return true;}
		return false;
	}
	function last_event($signal) {
		global $link,$simTime;
	
		$entity = getCurrentEntity();
		$result = mysql_query("SELECT hdlWBSignalsChangeTime,hdlWBSignalsActiveTime,hdlWBSignalsEventTime FROM hdlwbsignals WHERE hdlWBSignalsEntity='$entity' AND hdlWBSignalsName='$signal' AND hdlWBSignalsDefination='signal'");
		$row = mysql_fetch_assoc($result);
		return $simTime - $row["hdlWBSignalsEventTime"];
	}
	function last_active($signal) {
		global $link,$simTime;
	
		$entity = getCurrentEntity();
		$result = mysql_query("SELECT hdlWBSignalsChangeTime,hdlWBSignalsActiveTime,hdlWBSignalsEventTime FROM hdlwbsignals WHERE hdlWBSignalsEntity='$entity' AND hdlWBSignalsName='$signal' AND hdlWBSignalsDefination='signal'");
		$row = mysql_fetch_assoc($result);
		return $simTime - $row["hdlWBSignalsActiveTime"];
	}
	function stable($signal,$time) {
		if ($time < last_event($signal)) {return true;}
		
		return false;
	}
	function quiet($signal,$time) {
		if ($time < last_active($signal)) {return true;}
		
		return false;
	}
	function delayed($signal,$time) {
		return getSignalHistoryAtTime(getCurrentEntity(),$signal,$time);
	}
	function driving_value($signalname) {
		global $link,$simTime,$timeunit;

		$module = getCurrentEntity();
		$result = mysql_query("SELECT hdlWBSignalsHistory FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$signalname' AND hdlWBSignalsDefination='signal'");
		mysqlError($result);
		$row = mysql_fetch_assoc($result);
		$history = $row["hdlWBSignalsHistory"];
		$pieces = explode(",", $history);
		if (count($pieces) == 1) {return "";}
		foreach ($pieces as $value) {
			list($sigmodule, $sigprocess, $signame, $sigvalue, $sigtime) = explode(":", $value);
			if ($signalname == $signame && $sigprocess == getCurrentProcess()) {return $sigvalue;}
		}
		return "";
	}
	function driving($signalname) {
		global $link,$simTime,$timeunit;

		$module = getCurrentEntity();
		$result = mysql_query("SELECT hdlWBSignalsHistory FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$signalname' AND hdlWBSignalsDefination='signal'");
		mysqlError($result);
		$row = mysql_fetch_assoc($result);
		$history = $row["hdlWBSignalsHistory"];
		$pieces = explode(",", $history);
		if (count($pieces) == 1) {return false;}
		foreach ($pieces as $value) {
			list($sigmodule, $sigprocess, $signame, $sigvalue, $sigtime) = explode(":", $value);
			if ($signalname == $signame && $sigprocess == getCurrentProcess()) {return true;}
		}
		return false;
	}
	function transaction($signalname) {
		global $link,$simTime,$timeunit;

		$module = getCurrentEntity();
		$result = mysql_query("SELECT hdlWBSignalsHistory FROM hdlwbsignals WHERE hdlWBSignalsEntity='$module' AND hdlWBSignalsName='$signalname' AND hdlWBSignalsDefination='signal'");
		mysqlError($result);
		$row = mysql_fetch_assoc($result);
		$history = $row["hdlWBSignalsHistory"];
		$pieces = explode(",", $history);
		if (count($pieces) % 2 == 1) {return true;}
		return false;
	}
	
	function op_cat($sig1,$sig2) {$result = 0;return "$result";}
	function op_or($sig1,$sig2) {$result = $sig1 | $sig2;return "$result";}
	function op_and($sig1,$sig2) {$result = $sig1 & $sig2;return "$result";}
	function op_xor($sig1,$sig2) {$result = $sig1 ^ $sig2;return "$result";}
	function op_nand($sig1,$sig2) {$result = ~($sig1 & $sig2);return "$result";}
	function op_nor($sig1,$sig2) {$result = ~($sig1 | $sig2);return "$result";}
	function op_sll($sig1,$val) {$result = $sig1 << $sig2;return "$result";}
	function op_srl($sig1,$val) {$result = $sig1 >> $sig2;return "$result";}
	function op_sla($sig1,$val) {$result = $sig1 << $sig2;return "$result";}
	function op_sra($sig1,$val) {$result = $sig1 >> $sig2;return "$result";}
	function op_rol($sig1,$val) {$result = $sig1 << $sig2;return "$result";}
	function op_ror($sig1,$val) {$result = $sig1 >> $sig2;return "$result";}
?>

