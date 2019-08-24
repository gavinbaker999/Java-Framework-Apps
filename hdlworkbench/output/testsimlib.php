<html>
<head>
<script src="../../scripts/common.js" type="text/javascript"></script>
<script src="../../scripts/jquery.js" type="text/javascript"></script>
<link rel="stylesheet" href="../../css/ehs_default.css" type="text/css" media="screen">
</head>
<body>
<?
include("../../scripts/common.php");
include("../support/simlib.php");
include("../support/vhdlstandardfuncs.php");

composePHPCode("module","getSignal ( \"testnand\" , \"pc\" ) + 10 ;","getSignal");
//$ret = timeUnitConvert(3,"secs","ms");
//print("<br>ret=$ret<br>");

?>
</body>
</html>
