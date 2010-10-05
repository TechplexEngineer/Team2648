<?php
$dbhost = 'localhost';
$dbuser = 'tims';
$dbpass = 'timspass';
$dbname = 'tims';
$user_table = 'tims_users';

mysql_connect($dbhost, $dbuser, $dbpass) or die('ERROR: CANNOT CONNECT TO DATABASE.');
mysql_select_db($dbname) or die('ERROR: CANNOT SELECT DATABASE. \n' . mysql_error());

$sql = "SELECT * FROM `blog`";
$qry = mysql_query($sql) or die(mysql_error());
$row = mysql_fetch_assoc($qry);

print_r($row);
//echo "test";

?>
