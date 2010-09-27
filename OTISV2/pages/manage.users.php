<?php
include "vars.php";
$sql = "SELECT tims_users.*, personal_information.homephone, personal_information.cellphone, personal_information.medications, personal_information.mailaddress FROM tims_users LEFT OUTER JOIN personal_information
    ON tims_users.id = personal_information.id
    ORDER BY lastname, firstname ASC";
//echo $sql;
$qry = mysql_query($sql) or die(mysql_error());

echo "What collums do you want to see? ";
echo "<br/>\n";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('ln');\" checked=\"checked\"    /> Lastname ";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('fn');\" checked=\"checked\"    /> Firstname ";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('em');\" checked=\"checked\"    /> Email ";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('ty');\"                        /> Type ";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('hp');\"                        /> Homephone ";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('cp');\" checked=\"checked\"    /> Cellphone ";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('me');\"                        /> Meds ";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('ad');\"                        /> Address ";

echo "<table width=\"100%\">\n";
echo "<tr>\n";
//echo "<td> <strong> ID </strong>        </td>\n";
echo "<td class=\"ln\"> <strong> Last Name</strong>  </td>\n";
echo "<td class=\"fn\"> <strong> First Name</strong> </td>\n";
echo "<td class=\"em\"> <strong> Email</strong>      </td>\n";
echo "<td class=\"ty\"> <strong> Type</strong>       </td>\n";
echo "<td class=\"hp\"> <strong> HomePhone</strong>      </td>\n";
echo "<td class=\"cp\"> <strong> CellPhone</strong>      </td>\n";
echo "<td class=\"me\"> <strong> Meds</strong>      </td>\n";
echo "<td class=\"ad\"> <strong> Address</strong>      </td>\n";
echo "</tr>\n";

while($row = mysql_fetch_assoc($qry))
{
    echo "<tr>\n";
    //echo "<td style=\"padding:0 5px 0 5px;\"> " . $row["id"] . "</td>\n";
    echo "<td style=\"padding:0 5px 0 5px;\" class=\"ln\"> " . $row["lastname"]  . "</td>\n";
    echo "<td style=\"padding:0 5px 0 5px;\" class=\"fn\"> " . $row["firstname"] . "</td>\n";
    echo "<td style=\"padding:0 5px 0 5px;\" class=\"em\"> " . $row["email"] . "</td>\n";
    echo "<td style=\"padding:0 5px 0 5px;\" class=\"ty\"> " . $row["type"] . "</td>\n";
    echo "<td style=\"padding:0 5px 0 5px;\" class=\"hp\"> " . $row["homephone"] . "</td>\n";

    echo "<td style=\"padding:0 5px 0 5px;\" class=\"cp\"> " . $row["cellphone"] . "</td>\n";
    echo "<td style=\"padding:0 5px 0 5px;\" class=\"me\"> " . $row["medications"] . "</td>\n";
    echo "<td style=\"padding:0 5px 0 5px;\" class=\"ad\"> " . $row["mailaddress"] . "</td>\n";

    echo "</tr>\n";

    //print_r($row);
}
echo "</table>\n";


?>
