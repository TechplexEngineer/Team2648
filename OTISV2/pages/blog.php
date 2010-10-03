<?php
session_start();
echo "<h5>Web Log</h5>";

if(empty($_SESSION['penname']))
    $penname = $_SESSION['username']

?>
<label for="author" >Author</label>

<input type="text" name="author" value="<?php echo $penname; ?>">
<br>
<textarea></textarea>


pen name (based on username)