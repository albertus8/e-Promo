<?php
//Ambil requested id 
$id = $_GET['id']; 
//Importing database 
require_once('dbConnect.php'); 
//buat sintak sql query Untuk mbil data berdasarkan ID 
$sql = "SELECT * FROM ep_masterpromo WHERE nomor='$id'"; 
//result 
$r = mysqli_query($con,$sql); 
//pushing result kedalam array 
$result = array(); $row = mysqli_fetch_array($r); 
array_push($result,array( "nomor"=>$row['nomor'],
"nama"=>$row['nama'],
"kategori"=>$row['kategori'],
"alamat"=>$row['alamat'],
"kontak"=>$row['kontak'],
"deskripsi"=>$row['deskripsi'],
"gambar"=>$row['gambar']
));
 
//$result = $sql; 
//Tampilkan dalam format json
echo json_encode(array('result'=>$result));
mysqli_close($con);
?>