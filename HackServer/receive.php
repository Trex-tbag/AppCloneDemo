<?php
 
$data = $_GET["data"];
echo 1;

$myfile = fopen("/Library/WebServer/Documents/newfile.txt","w") or die("Unable to open file!");
 
fwrite($myfile, $data);
 
fclose($myfile);
 
?>
