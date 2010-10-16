<?php

if (isset($_REQUEST['todo']))
{

    print_r($_REQUEST);
    //exit;
    include "mail.php";
    include "functions.php";


    echo ($_REQUEST['todo'] == "Send an email");
    if ($_REQUEST['todo'] == "Send an email")
    {
        if(empty( $_REQUEST['To']) || empty($_REQUEST['Subject']) || empty($_REQUEST['Message']))
            die("Empty");
        $addresses = explode(", ", $_REQUEST['To']);
        mutlipleMailer(chopmail($addresses), $_REQUEST['Subject'], $_REQUEST['Message']);
              //$error = true;
    }
    else if ($_REQUEST['todo'] == "Send a text message")
    {
        if(empty( $_REQUEST['To']) || empty($_REQUEST['SubjectText']) || empty($_REQUEST['MessageText']))
            die("Empty");
        $addresses = explode(", ", $_REQUEST['ToText']);
        if(!mutlipleMailer(chopmail($addresses), $_REQUEST['SubjectText'], $_REQUEST['MessageText']))
              $error = true;
    }
    else if ($_REQUEST['todo'] == "Send both")
    {
        //if(empty($Eaddresses) || empty($_REQUEST['Subject']) || empty($_REQUEST['Message']) || empty($Taddresses) || empty($_REQUEST['SubjectText']) || empty($_REQUEST['MessageText']))
            //die("Empty");
        $Eaddresses = explode(", ", $_REQUEST['To']);
        if(!mutlipleMailer(chopmail($Eaddresses), $_REQUEST['Subject'], $_REQUEST['Message']))
              $error = true;
        $Taddresses = explode(", ", $_REQUEST['ToText']);
        if(!mutlipleMailer(chopmail($Taddresses), $_REQUEST['SubjectText'], $_REQUEST['MessageText']))
              $error = true;
    }
    else
        die("There has been an error(If Else)");


    if(!$error)
        echo "Mail Sent, Should there be a from list? jluce, OTIS, Blake?";
    else
        echo "There has been an error.(error)";
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
<!--<link rel="stylesheet" href="css/demos.css">-->
<script type="text/javascript">
    $(function() {

        $.get("http://team2648.com/OTIS2/admin/contacts.php", function(data){
            emailaddresses = data.split("/");
        });

        function split( val ) {
            return val.split( /,\s*/ );
        }
        function extractLast( term ) {
            return split( term ).pop();
        }

        $( "#emailto" ).autocomplete({
            minLength: 0,
            source: function( request, response ) {
                // delegate back to autocomplete, but extract the last term
                response( $.ui.autocomplete.filter(
                emailaddresses, extractLast( request.term ) ) );
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

        $.get("http://team2648.com/OTIS2/admin/contacts.php",{ action: "sms"}, function(data){
            smsaddresses = data.split("/");
        });
        $( "#smsto" ).autocomplete({
            minLength: 0,
            source: function( request, response ) {
                // delegate back to autocomplete, but extract the last term
                response( $.ui.autocomplete.filter(
                smsaddresses, extractLast( request.term ) ) );
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
<form action="pages/email.php">
    <div id="send">
        What would you like to do?
        <br/>
        <input id="r1" type="radio" name="todo" value="Send an email" onclick="showMail();" /> Send an email
        <br/>
        <input id="r2" type="radio" name="todo" value="Send a text message" onclick="showText();;" /> Send a text message
        <br/>
        <input id="r3" type="radio" name="todo" value="Send both" onclick="showBoth();" /> Send both
        <br/>
    </div>
    <div id="mail"><!--  class="ui-widget"-->
        <hr />
        <strong>Email</strong>
        <br>

        <table width="100%">
            <tr>
                <td style="vertical-align:top;">To</td>
                <td><textarea name="To" id="emailto" cols="75" rows="2"></textarea></td>
            </tr>
            <tr>
                <td>Subject</td>
                <td><input type="textbox" name="Subject" size="72"/></td>
            </tr>
            <tr>
                <td style="vertical-align:top;">Message</td>
                <td><textarea name="Message" cols="75" rows="15"> lol </textarea></td>
            </tr>
        </table>
    </div>
    <script type="text/javascript">
        //if($("#r1").val() || $("#r1").val() || $("#r1").val())
        //console.log($('input[name=todo]:checked').val());
        //      console.log("junk");
        //      $("#MessageText").keyup(function() {
        //          console.log("test");
        //          var text = $('textarea#MessageText').val();
        //          console.log(text.length);
        //      });
        var text = $("textarea#MessageText").val();
        console.log(text);

        function limit(id, length)
        {
            console.log(id + " " + length);
        }

        //enter at least 15 characters
        //14 more to go ...
        //145 characters left
    </script>
    <div id="text">
        <hr />
        <strong>Text</strong>
        <br>

        <table width="100%">
            <tr>
                <td style="vertical-align:top;">To</td>
                <td><textarea name="ToText" id="smsto" cols="75" rows="2"></textarea></td>
            </tr>
            <tr>
                <td>Subject</td>
                <td><input type="textbox" name="SubjectText" size="72"/></td>
            </tr>
            <tr>
                <td style="vertical-align:top;">Message</td>
                <td><textarea name="MessageText" id="MessageText" cols="75" rows="15"> lol </textarea></td>
            </tr>
        </table>
        limit to 160 chars
    </div>


    <div id="sendButton">
        <script type="text/javascript">
            function which()
            {
                var which = $('input:radio[name=todo]:checked').val();
                document.write("<input type=\"hidden\" name=\"which\" value=\"  "+which+" \"/>");
            }
        </script>
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
  echo "Send a text message"; */
?>
