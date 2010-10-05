<?php
session_start();

//echo($_REQUEST['action']);

if (empty($_SESSION['user']) && empty($_REQUEST['form'])) //check this code!!1

{
    exit;
}
if ($_REQUEST['action']=="submit")
{
    //echo "Let's process this form!";
    include "config.php";
    include "mail.php";
    if ($_REQUEST['form'] == "profile")
    {//public profile
        //print_r($_REQUEST);
        //"UPDATE `tims`.`pending_profile` SET `nickname` = 'I Don''t Have One' WHERE `pending_profile`.`id` = 1;";
        $sql = "INSERT INTO `tims`.`pending_profile`"
                . "(`id`, `nickname`, `location`, `role`, `yog`, `interests`, `favMoment`, `gainThisYr`, `futurePlans`, `bio`) \n"
                . "VALUES ('" . mysql_real_escape_string($_SESSION['id']) . "', '" . mysql_real_escape_string($_REQUEST['nickname']) . "', '" . mysql_real_escape_string($_REQUEST['town']) . "', '" . mysql_real_escape_string($_REQUEST['role']) . "', '" . mysql_real_escape_string($_REQUEST['yog']) . "', '" . mysql_real_escape_string($_REQUEST['interests']) . "', '" . mysql_real_escape_string($_REQUEST['fav_moment']) . "', '" . mysql_real_escape_string($_REQUEST['gain']) . "', '" . mysql_real_escape_string($_REQUEST['future']) . "', '" . mysql_real_escape_string($_REQUEST['bio']) . "')\n"
                . "ON DUPLICATE KEY UPDATE nickname ='" . mysql_real_escape_string($_REQUEST['nickname']) . "', location='" . mysql_real_escape_string($_REQUEST['town']) . "', role= '" . mysql_real_escape_string($_REQUEST['role']) . "', yog='" . mysql_real_escape_string($_REQUEST['yog']) . "', interests='" . mysql_real_escape_string($_REQUEST['interests']) . "', favMoment='" . mysql_real_escape_string($_REQUEST['fav_moment']) . "', gainThisYr='" . mysql_real_escape_string($_REQUEST['gain']) . "', futurePlans='" . mysql_real_escape_string($_REQUEST['future']) . "', bio='" . mysql_real_escape_string($_REQUEST['bio']) . "'\n";
        $qry = mysql_query($sql) or die(mysql_error());

//@todo overlay this
//http://flowplayer.org/tools/overlay/index.html
        //send mail to moderators
        include "vars.php";
        $to = $captMail;
        $prof = implode("\n", $_REQUEST);
        $subject = "Moderation Needed";
        $body = $_SESSION['fullname'] . " Has just changed their public profile.\n" .
                "Please login here to moderate their changes:\n" .
                //"http://team2648.com/OPIS/login.php?page=manage".
                "http://www." . $sysurl . "/login.php?page=manage\n" .
                "Best,\n" .
                "Blake\n\n\n" .
                "Click here to accept the profile bleow\n\n" .
                "http://www." . $sysurl . "/login.php?page=manage&acceptID=".$_SESSION['id']."\n" .
                $prof;
        mailer($to, $subject, $body);
        $to = $mentorMail;
        mailer($to, $subject, $body);

        echo "<link href=\"../css/styling.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />";
        echo "<div class =\"widget\" style=\"width:350px\">";
        echo "Your changes have been saved, they will not go live until reviewed by a moderator";
        echo "<br>";
        echo "<a href=\"../\">Click here to continue</a>";
        echo "</div>";
    }
    exit;
}
else if($_REQUEST['action']=="change")
{

    //print_r($_REQUEST);

    //Fill the fields below with previously submiotted data

    $row['nickname']            = $_REQUEST['nickname'];
    $row['location']            = $_REQUEST['town'];
    $row['role']                = $_REQUEST['role'];
    $row['yog']                 = $_REQUEST['yog'];
    $row['interests']           = $_REQUEST['interests'];
    $row['favMoment']           = $_REQUEST['fav_moment'];
    $row['gainThisYr']          = $_REQUEST['gain'];
    $row['futurePlans']         = $_REQUEST['future'];
    $row['bio']                 = $_REQUEST['bio'];


}
else
{
    $sql = "SELECT * FROM `pending_profile` WHERE id ='" . $_SESSION['id'] . "'";
    $qry = mysql_query($sql) or die(mysql_error());
    $row = mysql_fetch_assoc($qry);

    if(empty($row['nickname']))
        $row['nickname'] = "[NONE]";

    if(empty($row['location']))
        $row['location'] = "Town";

    if(empty($row['role']))
        $row['role'] = "[NONE]";

    if(empty($row['yog']))
        $row['yog'] = "[NONE]";

    if(empty($row['interests']))
        $row['interests'] = "[NONE]";

    if(empty($row['favMoment']))
        $row['favmoment'] = "[NONE]";

    if(empty($row['gainThisYr']))
        $row['gainThisYr'] = "[NONE]";

    if(empty($row['futurePlans']))
        $row['futurePlans'] = "[NONE]";
    if(empty($row['bio']))
        $row['bio'] = "This is a Place for you to write whatever you want the world to know about yourself.";
}
?>
<!--<h3>Use this page to manage your profile information</h3>-->
<h4>Public Profile</h4>
<strong>NOTE:</strong> Fields filled with [NONE] will not show on the website.
<br />
<form id="profile" name="profile" method="get" action="lib/preview.php">
    <input type="hidden" value="profile" name="form">
    <input type="hidden" value="<?php echo $_SESSION['id']; ?>" name="id">
    <table>
        <tr>
            <td><label for="myname">Hello, My name is:</label><td>
            <td><input type="text" readonly="readonly" name="myname" value="<?php echo $_SESSION['firstname']; ?>"/><td>
        </tr>
        <tr>
            <td><label for="nickname">But I like to be called:</label><td>
            <td><input type="text" name="nickname" value="<?php echo $row['nickname']; ?>"/><td>
        </tr>
        <tr>
            <td><label for="town">I live in:</label><td>
            <td><input type="text" name="town" value="<?php echo $row['location']; ?>"/><td>
        </tr>
        <tr>
            <td><label for="role">My role on the team is:</label><td>
            <td><input type="text" name="role" value="<?php echo $row['role']; ?>"/><td>
        </tr>
        <tr>
            <td><label for="yog">I will graduate High School in:</label><td>
            <td><input type="text" name="yog" value="<?php echo $row['yog']; ?>"/><td>
        </tr>
        <tr>
            <td><label for="interests">Some of my interests are:</label><td>
            <td><input type="text" name="interests" value="<?php echo $row['interests']; ?>"/><td>
        </tr>
        <tr>
            <td><label for="fav_moment">One of my favorite team moments:</label><td>
            <td><input type="text" name="fav_moment" value="<?php echo $row['favMoment']; ?>"/><td>
        </tr>
        <tr>
            <td><label for="gain">I would like to gain the following this year:</label><td>
            <td><input type="text" name="gain" value="<?php echo $row['gainThisYr']; ?>"/><td>
        </tr>
        <tr>
            <td><label for="future">My future plans include:</label><td>
            <td><input type="text" name="future" value="<?php echo $row['futurePlans']; ?>"/><td>
        </tr>
        <tr>
            <td><label for="bio">My Bio:</label><td>
            <td><textarea name="bio" ><?php echo $row['bio']; ?></textarea><td>
        </tr>
    </table>
    * All fields are required.
    <?php
    include "disclaimer.php";
// @todo add js validation of all fields filled in
    ?>
    <br/>
    <input type="submit" name="Submit" value=" I Agree, Preview "/>

</form>
