<?php
if($_SERVER['REQUEST_METHOD']=='POST'){ 
//Getting values 
$id = $_POST['id']; 
$name = $_POST['npm']; 
$desg = $_POST['nama']; 
$sal = $_POST['jurusan']; 
//importing database connection script 
require_once('dbConnect.php'); 
//Creating sql query 
$sql = "UPDATE mahasiswa SET npm = '$name', nama= '$desg', jurusan= '$sal' WHERE id = $id;"; 
//Updating database table 
if(mysqli_query($con,$sql)){ 
echo 'Sukses Update Data'; 
}else{ 
echo 'Gagal Update Data, Try Again'; } 
//closing connection 
mysqli_close($con); } ?>