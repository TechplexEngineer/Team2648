<?php

//function timsMail($to, $subject, $body)
//{
//    include "vars.php";
//    $headers = "From: ".$shortname."@team2648.com\r\n" .
//            "X-Mailer: php";
//    return (mail($to, $subject, $body, $headers));
//}

function mailer($to, $subject, $body)
{
    include "vars.php";
    $headers = "From: " . $shortname . "@team2648.com\r\n" .
            "X-Mailer: php";
    return (mail($to, $subject, $body, $headers));
}

function mutlipleMailer($addresses, $subject, $body)
{
    $error = false;
    foreach (removeBlankEntries($addresses) as $num => $addy)
    {
        if(!mailer($addy, $subject, $body))
            $error = true;
    }
    return $error;
}
function chopmail($array)
{
    $newArray = array();
    foreach ($array as $num => $addy)
    {
        $newArray[] = substr($addy, strpos($addy, "<") + 1, strpos($addy, ">") - strlen($addy));
    }
    return $newArray;
}

//$to = "techwiz@techwizworld.net";
//$subject = "Your account was successfully created";
//$body = "Greetings,\n\nBe sure to check back frequently, to track your progress.\ntechwizworld.net/TIMS";
?>
