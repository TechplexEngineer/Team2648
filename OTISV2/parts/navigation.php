<ul>
    <?php
    include "io.php";
    include "vars.php";
    if ($_SESSION['user'] != "register")
    {
	echo "<li> My </li>\n";
	echo "<ul>\n";
	echo "<li><a href=\"./\"> Dashboard </a></li>\n";
	echo "<li><a href=\"?page=manage.profile\"> Profile</a></li>\n";
	echo "<li><a href=\"?page=manage.info\"> Information</a></li>\n";
	echo "<li><a href=\"?page=manage.econtact\"> Emergency Contact</a></li>\n";
        echo "<li><a href=\"?page=blog\"> Blog </a></li>";
	echo "</ul>\n";
    }


    include "admin/nav.php";
    if (!($_REQUEST['page'] == "parts/bugs.php" || $_REQUEST['page'] == "register" || $_REQUEST['user'] == "register"))
	echo "<li><a href=\"?page=bugs.php&referrer=" . $_SERVER['REQUEST_URI'] . "\"> Report a Bug </a></li>\n";

    if ($_SESSION['user'] != "register")
	echo "<li><a href=\"logout.php\"> Logout </a></li>\n";
    else
	echo "<li><a href=\"logout.php\"> Exit </a></li>\n";
    ?>


</ul>
<?
    if (!empty($_SESSION['user']) && $_SESSION['user'] != "register")
    {
	$str;
	$str .= "\t\t\t<br />\n";
	$str .= "\t\t\tLogged in as: \n";
	$str .= "\t\t\t<br />\n";
	$str .= "\t\t\t<a href=\"?page=manage.profile\">" . $_SESSION['fullname'] . "</a>\n";
//$str .= "\t\t\t<br />\n";
	echo $str;
    }
?>