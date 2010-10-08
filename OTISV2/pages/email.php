<?php
if(isset($_REQUEST['To']))
{
    print_r($_REQUEST);





    exit;
}
?>


<script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.8.1.custom.min.js" type="text/javascript"></script>
<script src="js/jquery.autofocus-min.js" type="text/javascript"></script>

<link rel="stylesheet" href="css/jq/jquery.ui.all.css">
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/ui/jquery.ui.position.js"></script>
<script type="text/javascript" src="js/ui/jquery.ui.autocomplete.js"></script>
<link rel="stylesheet" href="css/demos.css">
<script type="text/javascript">
    $(function() {

        $.get("http://team2648.com/OTIS2/admin/contacts.php", function(data){
            availableTags = data.split("/");
        });

        function split( val ) {
            return val.split( /,\s*/ );
        }
        function extractLast( term ) {
            return split( term ).pop();
        }

        $( "#tags" ).autocomplete({
            minLength: 0,
            source: function( request, response ) {
                // delegate back to autocomplete, but extract the last term
                response( $.ui.autocomplete.filter(
                availableTags, extractLast( request.term ) ) );
            },
            focus: function() {
                // prevent value inserted on focus
                //alert("Junk");
                return false;
            },
            select: function( event, ui ) {
                var terms = split( this.value );
                // remove the current input
                terms.pop();
                // add the selected item
                terms.push( ui.item.value );
                // add placeholder to get the comma-and-space at the end
                terms.push( "" );
                this.value = terms.join( ", " );
                return false;
            }
        });
        /*$( "#tags" ).click(function(){
            alert("Clicked");
            $( "#tags" ).attr()
            $( "#tags" ).attr()


        });
        $( "#tags" ).blur(function(){
            alert("Small");
            $( "#tags" ).attr()
            $( "#tags" ).attr()

        });*/
    });
</script>

<div id="send">
    What would you like to do?
    <br/>
    <input id="r1" type="radio" name="todo" value="Send an email" onclick="showMail();" /> Send an email
    <br/>
    <input id="r2" type="radio" name="todo" value="Send a text message" onclick="showText();" /> Send a text message
    <br/>
    <input id="r3" type="radio" name="todo" value="Send both" onclick="showBoth();" /> Send both
    <br/>
</div>

<form action="pages/email.php">
    <div id="mail"><!--  class="ui-widget"-->
        <strong>Mail</strong>
        <br>
        <table width="100%">
            <tr>
                <td style="vertical-align:top;">To</td>
                <td><textarea name="To" id="tags" cols="75" rows="2"></textarea></td>
            </tr>
            <tr>
                <td>Subject</td>
                <td><input type="textbox" name="Subject" /></td>
            </tr>
            <tr>
                <td style="vertical-align:top;">Message</td>
                <td><textarea name="Message" cols="75" rows="15"> lol </textarea></td>
            </tr>
        </table>
    </div>

    <div id="text">
	Text 
        <br>
        <textarea> lol </textarea>limit to 160 chars
    </div>
    <script type="text/javascript">
        //if($("#r1").val() || $("#r1").val() || $("#r1").val())
        //console.log($('input[name=todo]:checked').val());


    </script>

    <div id="sendButton">
        <input type="submit" id="send" name="Send" value="Send" />
    </div>
</form>


<?php

//@todo hide options on beginning typing

//include "io.php";
//include "lookup.php";
//echo "lookupname: " .lookUpName(1);



/*
echo "What would you like to do?";
echo "<br/> \n";
echo 
echo "Send an email";
echo "<br/> \n";
echo "Send a text message";*/


?>
