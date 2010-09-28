<?php

session_start();
if (!($_SESSION['type'] == "admin" || $_SESSION['type'] == "superuser" || $_SESSION['user'] == "blake.bourque"))
    echo "You are not authorized to access this page";

if (isset($_REQUEST['acceptID']))
{
    $sql = "SELECT * FROM `pending_profile` WHERE id ='" . $_REQUEST['acceptID'] . "'";
    $qry = mysql_query($sql) or die(mysql_error());
    $row = mysql_fetch_assoc($qry);

    $sql = "INSERT INTO `tims`.`public_profile`"
            . "(`id`, `nickname`, `location`, `role`, `yog`, `interests`, `favMoment`, `gainThisYr`, `futurePlans`, `bio`) \n"
            . "VALUES ( '" . $_SESSION['id'] . "',
                        '" . mysql_real_escape_string($row['nickname']) . "',
                        '" . mysql_real_escape_string($row['location']) . "',
                        '" . mysql_real_escape_string($row['role']) . "',
                        '" . mysql_real_escape_string($row['yog']) . "',
                        '" . mysql_real_escape_string($row['interests']) . "',
                        '" . mysql_real_escape_string($row['favMoment']) . "',
                        '" . mysql_real_escape_string($row['gainThisYr']) . "',
                        '" . mysql_real_escape_string($row['futurePlans']) . "',
                        '" . mysql_real_escape_string($row['bio']) . "')\n"
            . "ON DUPLICATE KEY UPDATE 
                nickname        ='" . mysql_real_escape_string($row['nickname']) . "',
                location        ='" . mysql_real_escape_string($row['location']) . "',
                role            ='" . mysql_real_escape_string($row['role']) . "',
                yog             ='" . mysql_real_escape_string($row['yog']) . "',
                interests       ='" . mysql_real_escape_string($row['interests']) . "',
                favMoment       ='" . mysql_real_escape_string($row['favMoment']) . "',
                gainThisYr      ='" . mysql_real_escape_string($row['gainThisYr']) . "',
                futurePlans     ='" . mysql_real_escape_string($row['futurePlans']) . "',
                bio             ='" . mysql_real_escape_string($row['bio']) . "'\n";
    $qry = mysql_query($sql) or die(mysql_error());

    echo "User " . $_SESSION['id'] . ", profile approved.<br><br>";
    //@todo make this a red notification box
    //@todo Id2Name
    //@todo edit fields
    //@todo reject / approve
    //@todo preview
    //exit;
}
include "vars.php";
$sql = "SELECT * FROM `pending_profile`";
$qry = mysql_query($sql) or die(mysql_error());
if (mysql_num_rows($qry) == 1)
    echo "There is " . mysql_num_rows($qry) . " profile to be moderated <br>";
else if (mysql_num_rows($qry) >= 1)
    echo "There are " . mysql_num_rows($qry) . " profiles to be moderated <br>";
//select one from the list below
//then we need manage.prfile?cmd=manage
$sql = "SELECT * FROM `public_profile`";
$qry = mysql_query($sql) or die(mysql_error());
if (mysql_num_rows($qry) == 1)
    echo "There is " . mysql_num_rows($qry) . " profile displayed <br>";
else if (mysql_num_rows($qry) >= 1)
    echo "There are " . mysql_num_rows($qry) . " profiles displayed <br>";

$sql = "SELECT * FROM `" . $login_table . "`";
$qry = mysql_query($sql) or die(mysql_error());
if (mysql_num_rows($qry) == 1)
    echo "There is " . mysql_num_rows($qry) . " profile <br>";
else if (mysql_num_rows($qry) >= 1)
    echo "There are " . mysql_num_rows($qry) . " profiles <br>";
?>
