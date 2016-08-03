<?php
if($_SERVER['REQUEST_METHOD']=='POST'){ 
//Getting values 
$alamat = $_POST['alamat']; 
$nama = $_POST['nama']; 
$deskripsi = $_POST['deskripsi']; 
$kontak = $_POST['kontak']; 
$kategori = $_POST['kategori']; 
//Creating an sql query 
$sql = "INSERT INTO ep_masterpromo (nama,kategori,alamat,kontak,deskripsi) VALUES ('$nama','$kategori','$alamat','$kontak','$deskripsi')"; 
//Panggil Koneksi 
require_once('dbConnect.php'); 
//Executing query ke database 
if(mysqli_query($con,$sql)){
 echo 'Sukses Tambah Data'; 
}else{ 
echo 'Gagal Tambah Data'; 
} 
//Closing the database 
mysqli_close($con); } ?>