<ul>
    <?php
    include "io.php";
    include "vars.php";
    if ($_SESSION['user'] != "register")
    {
    echo "<li> My </li>";
    echo "<ul>";
        echo "<li><a href=\"./\"> Dashboard </a></li>";
        echo "<li><a href=\"?page=manage.profile\"> Profile</a></li>";
        echo "<li><a href=\"?page=manage.info\"> Information</a></li>";
        echo "<li><a href=\"?page=manage.econtact\"> Emergency Contact</a></li>";
        echo "</ul>";
    }
    
    
    include "admin/nav.php";
    if (!($_REQUEST['page'] == "parts/bugs.php" || $_REQUEST['page'] == "register" || $_REQUEST['user'] == "register"))
        echo "<li><a href=\"?page=bugs.php&referrer=" . $_SERVER['REQUEST_URI'] . "\"> Report a Bug </a></li>";

    if ($_SESSION['user'] != "register")
        echo "<li><a href=\"logout.php\"> Logout </a></li>";
    else
        echo "<li><a href=\"logout.php\"> Exit </a></li>";
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