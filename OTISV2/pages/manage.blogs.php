<?php
$sql= "Select * FROM blog";
$qry = mysql_query($sql) or die(mysql_error());


echo "<table>";
echo "<tr>";
echo "<td> ID </td> Title </td><td> Date </td><td> Post </td><td> Author </td><td> approved </td>";
echo "</tr>";
while ($row = mysql_fetch_assoc($qry))
{
    echo "<tr>";
    echo "<td>";
        echo $row['id'];
    echo "</td>";
    echo "<td>";
        echo $row['title'];
    echo "</td>";
    echo "<td>";
        echo $row['date'];
    echo "</td>";
    echo "<td>";
        echo "POST";
    echo "</td>";
    echo "<td>";
        echo $row['author'];
    echo "</td>";
    echo "<td>";
        echo $row['approved'];
    echo "</td>";
    echo "</tr>";
}
echo "</table>";
?>
