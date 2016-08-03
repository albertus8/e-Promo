<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		$image = $_POST['image'];
                $name = $_POST['name'];
		
		require_once('dbConnect.php');
		
		$sql ="SELECT nomor FROM ep_masterpromo ORDER BY nomor ASC";
		
		$res = mysqli_query($con,$sql);
		
		$id = 0;
		
		while($row = mysqli_fetch_array($res)){//err
				$id = $row['nomor'];
		}
		
		$path = "image/$id.png";
		
		$actualpath = "localhost:81/epromo/image/$path";
		
		$sql = "UPDATE ep_masterpromo set gambar='$actualpath' where nomor=$id";
		
		if(mysqli_query($con,$sql)){
			file_put_contents($path,base64_decode($image));//err
			echo "Successfully Uploaded";
		}
		
		mysqli_close($con);
	}else{
		echo "Error";
	}