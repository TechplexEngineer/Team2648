<?php
if ($_SESSION['type'] == "superuser" || $_SESSION['type'] == "admin" || $_SESSION['id'] == 1 )
{
    echo "<li> Management </li>";
    echo "<ul>";
    echo "<li><a href=\"?page=manage.users\"> Users </a></li>";
    echo "<li><a href=\"?page=email\"> Email </a></li>";
    echo "<li><a href=\"?page=blog\"> Blog </a></li>";
    echo "</ul>";
}
?>

