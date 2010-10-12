<?php

include "config.php";
//sql from database select names and email addresses
//echo firstname Lastname <emailaddress>,



if (isset($_REQUEST['action']))
{
    $sql = "SELECT firstname, lastname, sms FROM tims_users WHERE sms";
    $qry = mysql_query($sql) or die(mysql_error());
    while ($row = mysql_fetch_assoc($qry))
    {
	echo $row['firstname'] . " " . $row['lastname'] . " <" . $row['sms'] . ">/ ";
    }
    exit;
}

$sql = "SELECT * FROM tims_users";
$qry = mysql_query($sql) or die(mysql_error());


while ($row = mysql_fetch_assoc($qry))
{
    echo $row['firstname'] . " " . $row['lastname'] . " <" . $row['email'] . ">/ ";
}

$sql = "SELECT * FROM MailingListMember";
//echo $sql;
$qry = mysql_query($sql) or die(mysql_error());

while ($row = mysql_fetch_assoc($qry))
{
    echo $row['name'] . " <" . $row['email'] . ">/";
}
?>
