<?php
define('HOST','localhost'); 
define('USER','root'); 
define('PASS',''); 
define('DB','epromo'); 
//Connecting to Database 
$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
 
$sql = "select * from ep_masterpromo";
 
$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('nomor'=>$row[0],
'nama'=>$row[1],
'kategori'=>$row[2],
'alamat'=>$row[3],
'kontak'=>$row[4],
'deskripsi'=>$row[5],
'gambar'=>$row[6],
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($con);
 
?>