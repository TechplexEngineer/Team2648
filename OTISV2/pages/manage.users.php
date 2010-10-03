<?php

include "vars.php";
$sql = "SELECT tims_users.*, personal_information.homephone, personal_information.cellphone, personal_information.medications, personal_information.mailaddress FROM tims_users LEFT OUTER JOIN personal_information
    ON tims_users.id = personal_information.id
    ORDER BY firstname, lastname ASC";
//echo $sql;
$qry = mysql_query($sql) or die(mysql_error());

echo "<script src=\"js/sorttable.js\"></script>";

echo "What collums do you want to see? ";
echo "<br/>\n";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('id');\"                        /> ID \n";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('fl');\" checked=\"checked\"    /> First Last \n";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('ln');\"                        /> Lastname \n";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('fn');\"                        /> Firstname \n";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('em');\" checked=\"checked\"    /> Email \n";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('ty');\"                        /> Type \n";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('hp');\"                        /> Homephone \n";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('cp');\" checked=\"checked\"    /> Cellphone \n";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('me');\"                        /> Meds \n";
echo "<input type=\"checkbox\" name=\"todo\" value=\"\" onclick=\"toggleVis('ad');\"                        /> Address \n";

echo "<table width=\"100%\" class=\"sortable\">\n";
echo "<tr>\n";
echo "<th class=\"id\"> <strong> ID </strong>           </th>\n";
echo "<th class=\"fl\"> <strong> First Last</strong>    </th>\n";
echo "<th class=\"ln\"> <strong> Last Name</strong>     </th>\n";
echo "<th class=\"fn\"> <strong> First Name</strong>    </th>\n";
echo "<th class=\"em\"> <strong> Email</strong>         </th>\n";
echo "<th class=\"ty\"> <strong> Type</strong>          </th>\n";
echo "<th class=\"hp\"> <strong> HomePhone</strong>     </th>\n";
echo "<th class=\"cp\"> <strong> CellPhone</strong>     </th>\n";
echo "<th class=\"me\"> <strong> Meds</strong>          </th>\n";
echo "<th class=\"ad\"> <strong> Address</strong>       </th>\n";
echo "</tr>\n";

while ($row = mysql_fetch_assoc($qry))
{
    echo "<tr>\n";
    echo "<td style=\"padding:0 5px 0 5px;\" class=\"id\"> " . $row["id"] . "</td>\n";
    echo "<td style=\"padding:0 5px 0 5px;\" class=\"fl\"> " . $row["firstname"] . " " . $row["lastname"] . "</td>\n";
    echo "<td style=\"padding:0 5px 0 5px;\" class=\"ln\"> " . $row["lastname"] . "</td>\n";
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