<?php
session_start();
include "config.php";
include "vars.php";
include "mail.php";
include "io.php";

if ($registrationDisabled)
    die("I'm Sorry we are not acepting new registrations.");

if (!empty($_REQUEST['fname']))//The form has been submitted
{
    //die("firstname"); 
    $error = false;
    //** Check for duplicate username
    $sql = "SELECT * FROM " . $login_table . " WHERE user = '" . mysql_real_escape_string(stripslashes($_REQUEST['fname'])) . "." . mysql_real_escape_string(stripslashes($_REQUEST['lname'])) . "'";
    $query = mysql_query($sql) or die(mysql_error());
    if (mysql_num_rows($query) >= 1)
    {

        echo "<h3>You have already created an account.</h3> \n<br> Try to login, with Firstname.Lastname \n<br> If you can't remember your password, send blake (at) team2648.com an email.";
        exit;
    }
    //**** PHP Validation ****

    if ($_REQUEST['pass'] != $_REQUEST['pass2'])
    {
        $error = true;
        echo ("Your passwords do not match\n<br/>");
    }
    if (!(strlen($_REQUEST['pass']) >= 6 && strlen($_REQUEST['pass']) <= 20))
    {
        $error = true;
        echo ("Your password must have at least 6 characters, and no more than 20\n<br/>");
    }
    if ($_REQUEST['email'] != $_REQUEST['email2'])
    {
        $error = true;
        echo ("Your emails do not match\n<br/>");
    }
    //@todo Fix email validation
    if (isset($_REQUEST['email']) && !filter_var($_REQUEST['email'], FILTER_VALIDATE_EMAIL))
    {
        $error = true;
        echo "Please enter a valid Email address";
    }
    if (!preg_match('%^[A-Za-z]+$%', $_REQUEST['fname']) || is_numeric($_REQUEST['fname']))
    {
        $error = true;
        echo ("Your name may only contain letters\n<br/>");
    }
    if (!preg_match('%^[A-Za-z]+$%', $_REQUEST['lname']) || is_numeric($_REQUEST['lname']))
    {
        $error = true;
        echo ("Your name may only contain letters\n<br/>");
    }
    if ($error)
    {
        die("<br/><strong>Please go back and fix the errors above.</strong>");
    }
    //http://www.position-absolute.com/articles/jquery-form-validator-because-form-validation-is-a-mess/
    //die(print_r($_REQUEST));
    //$usrtype = "member";
    $usrtype = mysql_real_escape_string($_REQUEST['as']);
    $myusername = $_REQUEST['fname'] . "." . $_REQUEST['lname'];
    $sql = "INSERT INTO `" . $login_table . "`
        (`id`, `firstname`, `lastname`, `user`, `pass`, `email`, `type`) VALUES
        (NULL, '" . mysql_real_escape_string($_REQUEST['fname']) . "', '" . mysql_real_escape_string($_REQUEST['lname']) . "', '" . $myusername . "', '" . mysql_real_escape_string(sha1($_REQUEST['pass'])) . "', '" . mysql_real_escape_string($_REQUEST['email']) . "', '" . $usrtype . "')";
    $qry = mysql_query($sql) or die(mysql_error());

    $sql = "SELECT * FROM `" . $login_table . "` WHERE user='" . $myusername . "'";
    $query = mysql_query($sql) or die(mysql_error());

    $row = mysql_fetch_assoc($query);
    $_SESSION['user'] = $row['user'];
    $_SESSION['pass'] = $row['pass'];
    $_SESSION['id'] = $row['id'];
    $_SESSION['firstname'] = $row['firstname'];
    $_SESSION['lastname'] = $row['lastname'];
    $_SESSION['fullname'] = $row['firstname'] . " " . $row['lastname'];
    $_SESSION['email'] = $row['email'];
    $_SESSION['type'] = $row['type'];

    header("location:success.php");
    exit;
}


echo "<h3>Register yourself here</h3>";
?>
<strong> NOTE: </strong> This site's advanced features do not work in Internet Explorer. <br/>

<script>
    $(document).ready(function() {
        $("#formID").validationEngine()
    });
    //@todo Fix validation in FF
</script>
<!--<a href="#" onclick="$.validationEngine.buildPrompt('#register','This is an example','error')">Build a prompt on a div</a>-->
<form id="formID" name="register" method="post" action="pages/register.php">
    <table>
        <tr>
            <td><label for="fname">Hello, My first name is:</label><td>
            <td><input value="" class="validate[required,custom[onlyLetter],length[0,100]]" type="text" name="fname" id="fname"/><td>
        </tr>
        <tr>
            <td><label for="lname">Hello, My last name is:</label><td>
            <td><input type="text" id="lname" class="validate[required,custom[onlyLetter],length[0,100]]" name="lname" /><td>
        </tr>
        <tr>
            <td><label for="uname">My username will be:</label><td>
            <td><input type="text" readonly="readonly" id="uname"  name="uname" /><td>
        </tr>
        <tr>
            <td><label for="pass">I want my password to be:</label><td>
            <td><input type="password" id="pass" class="validate[required,length[6,20]] text-input" name="pass"/> <!--onblur="passMatch(this.form);"--><td>
        </tr>
        <tr>
            <td><label for="pass2">I am sure my password is:</label><td>
            <td><input id="pass2" type="password" class="validate[required,confirm[pass]] text-input" name="pass2"/> <!-- onblur="passMatch(this.form);"--> <td>
        </tr>
        <tr>
            <td><label for="email">My email address is:</label><td>
            <td><input type="text" id="e1"class="validate[required,custom[email]] text-input" name="email" /><td>
        </tr>
        <tr>
            <td><label for="email2">I am sure my email address is:</label><td>
            <td><input type="text" id="e2"class="validate[required,confirm[e1]] text-input" name="email2" /><td>
        </tr>
        <tr>
            <td><label for="as">I am registering as:</label><td>
            <td>

                <select name="as">
                    <option>Member</option>
                    <option>Parent</option>
                    <option>Mentor</option>
                    <option>Alumni</option>
                </select>

<!--<input type="text" id="e2"class="validate[required,confirm[e1]] text-input" name="email2" />-->
            <td>
        </tr>
    </table>
    <?php include "disclaimer.php"; ?>
    <br><input type="submit" id="submit" name="Submit" value="I Agree, Create My account">
</form>