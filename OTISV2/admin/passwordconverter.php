<?php
include "vars.php";
echo "Salted: " . sha1(sha1($_REQUEST['pass']).$salt);
echo "<br>";
echo "Unsalted: " . sha1($_REQUEST['pass']);

?>
