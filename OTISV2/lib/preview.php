<?php
session_start();
include "functions.php";
?>

<link href="../css/reset.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/styling.css" rel="stylesheet" type="text/css" media="screen" />

<?php

echo "<div class=\"widget\" style=\"width: 30%;\">";
//echo "This part is still under development <br/> preview comming soon";

if(isset($_REQUEST['nickname']) && $_REQUEST['nickname'] != "[NONE]")
    echo $_REQUEST['myname'] . "(".$_REQUEST['nickname'] . ")";
else
    echo $_REQUEST['myname'];

echo "\n <br/> \n";
echo "Grade: " . YOG($_REQUEST['yog']);
echo "\n <br/> \n";
echo "From: " . $_REQUEST['town'];
echo "\n <br/> \n";
echo "Role: " . $_REQUEST['role'];
echo "\n <br/> \n";
echo "Interests: " . $_REQUEST['interests'];
echo "\n <br/> \n";
echo "Favorite Team Moment: " . $_REQUEST['fav_moment'];
echo "\n <br/> \n";

if($_REQUEST['gain'] != "[NONE]")
{
    echo "Gain: " . $_REQUEST['gain'];
    echo "\n <br/> \n";
}

echo "Future: " . $_REQUEST['future'];
echo "\n <br/> \n";

if($_REQUEST['bio'] != "[NONE]")
{
    echo "Bio: " . $_REQUEST['bio'];
    echo "\n <br/> \n";
}



//@todo make this prettier
//@todo 'let me make a change'
echo "</div>";

$urlProf = $_SERVER["REQUEST_URI"];
$urlProf = substr($urlProf, strpos($urlProf, "?"));
$urlProf1 = substr($urlProf, 1);
$urlProf1 = "&".$urlProf1;

//send them back to manage.profile
?>


<div class="widget" style="text-align: center;">
    <?php include "disclaimer.php"; ?>
    <br/>
<!--    <a href="../pages/manage.profile.php<?php echo $urlProf;?>">I Agree Submit</a>-->
    <input type="button" name="goback" value="I need to make a change" onClick="window.location='../?page=manage.profile<?php echo $urlProf1;?>&action=change'">
    <input type="button" name="submit" value="I Agree Submit" onClick="window.location='../pages/manage.profile.php<?php echo $urlProf;?>&action=submit'"/>
    <!--  @todo I Don't agree  -->
</div>
