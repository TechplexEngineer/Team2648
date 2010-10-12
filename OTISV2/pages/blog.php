<?php
session_start();
if($_REQUEST['action'] == "Submit")
{
    if(strlen($_REQUEST['title']) <= 3)
    {
        die ("You title must be longer than 3 characters.");
    }
    if(strlen($_REQUEST['author']) <= 3)
    {
        die ("You author name must be longer than 3 characters.");
    }
    $var = $_REQUEST['eventDate'];
    $date = str_replace(",", "|", $var);
    
    //$sql = "INSERT "
    $sql = "INSERT INTO `tims`.`blog` ( `title`, `date`, `post`, `author`, `approved`) \n"
			    . "VALUES (
				    '" . mysql_real_escape_string($_REQUEST['title'])     . "',
				    '" . mysql_real_escape_string($date) . "',
				    '" . mysql_real_escape_string($_REQUEST['TextArea1']) . "',
				    '" . mysql_real_escape_string($_REQUEST['author'])    . "', 'False')\n";

    //echo $sql;
    $qry = mysql_query($sql) or die(mysql_error());

    die("Entry added to database");

}




echo "<h5>Web Log</h5>";

if(empty($_SESSION['penname']))
    $penname = $_SESSION['firstname'];
else
    $penname = $_SESSION['penname'];

?>
<br/>
<script type="text/javascript" src="js/jscripts/tiny_mce/jquery.tinymce.js"></script>
<script type="text/javascript">
    $().ready(function() {
        $('textarea.tinymce').tinymce({
            // Location of TinyMCE script
            script_url : 'js/jscripts/tiny_mce/tiny_mce.js',

            // General options
            theme : "advanced",
            plugins : "pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,advlist",

            // Theme options
            theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,styleprops,styleselect,formatselect,fontselect,fontsizeselect,visualchars,nonbreaking,|,fullscreen",
            theme_advanced_buttons2 : "cut,copy,paste,pastetext,|,search,|,bullist,numlist,|,outdent,indent,|,cite,abbr,acronym,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,code,|,preview,|,forecolor,backcolor",
            theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,iespell,|,ltr,rtl,insertlayer,moveforward,movebackward,absolute",
            theme_advanced_buttons4 : "",
            theme_advanced_toolbar_location : "top",
            theme_advanced_toolbar_align : "left",
            theme_advanced_statusbar_location : "bottom",
            theme_advanced_resizing : true,

            // Example content CSS (should be your site CSS)
                    content_css : "http://team2648.com/sites/team2648.com/themes/zen_midnight/zen_midnight.css"
        });
    });

</script>

<form id="blogEntry" method="post" action="">
    <input type="hidden" name="timestamp" value="<?php echo  date('m/d/y@g:i:s');?>"/>

    Title: &nbsp; <input type="text" name="title" style="width: 35%;"/>
    <br/>
    <br/>
    Date of event: &nbsp; <input type="text" name="eventDate" value="<?php echo  date('F j, Y');?>"/>
    <br/>
    <br/>
    <textarea name="TextArea1" rows="15" cols="80"  class="tinymce"></textarea>
    <br/>
    <br/>
    Author: &nbsp; <input type="text" name="author" value="<?php echo $penname; ?>"/>
    <input type="submit" name="action" value="Submit" />
</form>