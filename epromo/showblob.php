<?php
$db = mysqli_connect("localhost","root","","epromo"); //keep your db name
$sql = "SELECT * FROM ep_masterpromo WHERE nomor = 7";
$sth = $db->query($sql);
$result=mysqli_fetch_array($sth);
echo '<img src="data:image/jpeg;base64,'.base64_encode($image->load()) .'" />';
?>