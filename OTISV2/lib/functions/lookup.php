
<?php

function lookUpName($id)
{
	include "vars.php";
	$sql = "SELECT * FROM ".$login_table." where id = '". $id ."'";
	$qry = mysql_query($sql) or die(mysql_error());
	$row = mysql_fetch_assoc($qry);
	return $row['firstname'] . " | " . $row['lastname'];
}
function getFirstName($id)
{
	include "vars.php";
	$sql = "SELECT * FROM ".$login_table." where id = '". $id ."'";
	$qry = mysql_query($sql) or die(mysql_error());
	$row = mysql_fetch_assoc($qry);
	return $row['firstname'];
}
function getLastName($id)
{
	include "vars.php";
	$sql = "SELECT * FROM ".$login_table." where id = '". $id ."'";
	$qry = mysql_query($sql) or die(mysql_error());
	$row = mysql_fetch_assoc($qry);
	return $row['lastname'];
}
?>