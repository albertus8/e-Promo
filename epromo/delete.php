<?php
//Getting Id 
$id = $_GET['id']; 
//Importing database 
require_once('dbConnect.php'); 
//Buat sql query 
$sql = "DELETE FROM mahasiswa WHERE id=$id;"; 
//Deleting record didatabase 
if(mysqli_query($con,$sql)){ 
echo 'Employee Deleted Successfully'; 
}else{ 
echo 'Could Not Delete Employee Try Again';
 } 
//closing connection 
mysqli_close($con); ?>