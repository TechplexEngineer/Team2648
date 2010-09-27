<?php

$dbhost = 'localhost:8889';
$dbuser = 'root';
$dbpass = 'root';
$dbname = 'tims';
$user_table = 'tims_users';

mysql_connect($dbhost, $dbuser, $dbpass) or die('ERROR: CANNOT CONNECT TO DATABASE.');
mysql_select_db($dbname) or die('ERROR: CANNOT SELECT DATABASE. \n' . mysql_error());

?>