
<div id="send">
    What would you like to do?
    <br/>
    <input type="radio" name="todo" value="Send an email" onclick="showMail();" /> Send an email
    <br/>
    <input type="radio" name="todo" value="Send a text message" onclick="showText();" /> Send a text message
    <br/>
    <input type="radio" name="todo" value="Send both" onclick="showBoth();" /> Send both
    <br/>
</div>

<div id="mail">Mail 
    <br>
    <table width="100%">
        <tr>
            <td><label for="To">To</label></td>
            <td><input type="textbox" name="To" /></td>
        </tr>
        <tr>
            <td><label for="Subject">Subject</label></td>
            <td><input type="textbox" name="Subject" /></td>
        </tr>
        <tr>
            <td valign="top"><label for="Message">Message</label></td>
            <td><textarea name="Message" width="100%"> lol </textarea></td>
        </tr>
    </table> 
</div>
<div id="text">
	Text 
    <br>  
    <textarea> lol </textarea>
</div>


<?php

//@todo hide options on beginning typing

//include "io.php";
include "lookup.php";
echo "lookupname: " .lookUpName(1);



/*
echo "What would you like to do?";
echo "<br/> \n";
echo 
echo "Send an email";
echo "<br/> \n";
echo "Send a text message";*/


?>
