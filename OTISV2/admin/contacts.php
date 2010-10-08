<?php
include "config.php";
//sql from database select names and email addresses

//echo firstname Lastname <emailaddress>,
$sql = "SELECT * FROM tims_users";
//echo $sql;
$qry = mysql_query($sql) or die(mysql_error());

while ($row = mysql_fetch_assoc($qry))
{
   echo $row['firstname'] . " " . $row['lastname'] . " <" . $row['email'] . ">/";
}

$sql = "SELECT * FROM MailingListMember";
//echo $sql;
$qry = mysql_query($sql) or die(mysql_error());

while ($row = mysql_fetch_assoc($qry))
{
   echo $row['name'] . " <" . $row['email'] . ">/";
}

?>
