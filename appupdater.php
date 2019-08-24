<HTML>
<HEAD>
<link rel="stylesheet" href="../css/ehs_default.css" type="text/css" media="screen">
</HEAD>
<BODY bgcolor="lime">
<?
	include("../scripts/common.php");

	$curbuildnum = $_GET['curbuildnum'];
	$appproduct = $_GET['appproduct'];
	$serialbase = $_GET['serialbase'];
	$directory = $_GET['directory'];
	
	if (!isset($curbuildnum) || !isset($appproduct) || !isset($serialbase) || !isset($directory)) {
		print("Wrong number of parameters.<br>");
		exit();
	}
	
	if (file_exists($directory)) {
		//print("Using build.number<br>");
		$handle = fopen($directory, "r");
		if ($handle) {
			while (!feof($handle)) {
				$buffer = fgets($handle, 4096);
				$pos = stripos($buffer, "build.number=");
				if ($pos !== false) {
					$latestbuildnum = str_replace("build.number=", "", $buffer);
				}
			}
			fclose($handle);
			$latestbuildnum = trim($latestbuildnum);
		}
	} else {
		//print("Not using build.number<br>");
		$latestbuildnum = $curbuildnum;
	}
	
?>
<center>
<table border=0 cellspacing=0 cellpadding=0>
 <tr>
 <td width="25%">
 <img src="../ehslogo.gif">
 </td>
  <td>
  <b><i><span style='font-size:30.0pt;font-family:Verdana;color:black'>End House Software</span></i></b>
  <br><i><font size="+2">&nbsp&nbspProduct Update Services</font></i>
 <br><div><b>
 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href="mailto:endhousesoftware999@gmail.com">endhousesoftware999@gmail.com</a>&nbsp 690 249 745
 </b>
 &nbsp&nbsp&nbsp<a href="../rssfeeds/endhousesoftwarenews.xml"><img src="..\images\rss.jpg"></a>
  </div>
  </td>
 </tr>
</table>
</center>

Checking <? echo $appproduct ?> for updates ... (current build number: <? echo $curbuildnum ?> - lastest build number: <? echo $latestbuildnum ?>)<br><br>
<?
	if ($curbuildnum < $latestbuildnum) {
		$query = "SELECT sysEHSProdUpdateURL FROM sysehsproducts WHERE sysEHSProdSerialBase=" . $serialbase;

	$dentered = date("Y-m-d");
	$tentered = date("H:i:s");

	include("../scripts/dbconnect.php");

		$result=mysql_query($query);
		$row=mysql_fetch_assoc($result);
		$updateURL=$row["sysEHSProdUpdateURL"];
		mysql_close($link);
		if ($updateURL != "") {
			print("An update is available. Click <a href='".$updateURL."'>here</a> to download");
			exit();
		}
	}
	
	print("No updates are available, you are running the latest version.");
?>
<br>&copy End House Software 2012-2016
</center>
</BODY>
</HTML>

