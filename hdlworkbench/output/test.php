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
$link = mysql_connect('localhost', 'root', 'joide33');
mysql_select_db('endhousesoftware_com_-_endhousesoftware');
function internalSimulate($module) {test();}
function test() {
	}
function initSignals() {
}
function storeProcessNames() {
}
simulate($_GET["entity"]);
mysql_close($link);
?>
</body>
</html>
