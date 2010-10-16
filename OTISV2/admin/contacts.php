<?php

include "config.php";
//sql from database select names and email addresses
//echo firstname Lastname <emailaddress>,



if (isset($_REQUEST['action']))
{
    $sql = "SELECT firstname, lastname, sms FROM tims_users WHERE sms ORDER BY firstname, lastname DESC";
    $qry = mysql_query($sql) or die(mysql_error());
    while ($row = mysql_fetch_assoc($qry))
    {
	echo $row['firstname'] . " " . $row['lastname'] . " <" . $row['sms'] . ">/ ";
    }
    exit;
}

$sql = "SELECT * FROM tims_users ORDER BY firstname, lastname DESC";
$qry = mysql_query($sql) or die(mysql_error());


while ($row = mysql_fetch_assoc($qry))
{
    echo $row['firstname'] . " " . $row['lastname'] . " <" . $row['email'] . ">/ ";
}

$sql = "SELECT * FROM MailingListMember ORDER BY firstname, lastname DESC";
//echo $sql;
$qry = mysql_query($sql) or die(mysql_error());

while ($row = mysql_fetch_assoc($qry))
{
    echo $row['name'] . " <" . $row['email'] . ">/";
}
?>
