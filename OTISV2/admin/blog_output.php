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
while ($row = mysql_fetch_assoc($qry))
{
    if(!$row['approved'])
    {
	echo "<div>\n";
	echo "<h5>". str_replace('|', ',', $row['date']) ."</h5>\n";
	echo "<hr>\n";
	echo "<h3>". $row['title'] ."</h3>\n";
	echo $row['post']."\n";
	echo "Posted By: " . $row['author'] ."\n";

	echo "</div>\n";

	//print_r($row);
	

    }
}

//echo "test";
?>
