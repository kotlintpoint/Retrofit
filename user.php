<?php 
	$servername="localhost";
	$username="root";
	$password="";
	$dbname="androidapp";

	$conn=new mysqli($servername,$username,$password,$dbname);

	if($conn->connect_error)
	{
		die("Connection failed: ".$conn->connect_error);
	}


	$flag=$_POST["flag"];
	switch ($flag) {
		case '1':
			# Insert...
			insertUser($conn);
			break;
		case '2':
			# update...
			updateUser($conn);
			break;
		case '3':
			# Delet...
			deleteUser($conn);
			break;
		case '4':
			# select...
			selectUser($conn);
			break;
		default:
			# code...
			break;
	}
	function insertUser($conn){
		$username=$_POST["username"];
		$email=$_POST["email"];
		$mobile=$_POST["mobile"];
		$sql="insert into user (username, email, mobile) values ('$username','$email',$mobile)";
		if($conn->query($sql)===TRUE){
			echo json_encode("Inserted Successfully...");
		}else{
			echo json_encode("Operation Fail...");
		}
		$conn->close();
	}
	function updateUser($conn){
		$id=$_POST["id"];
		$username=$_POST["username"];
		$email=$_POST["email"];
		$mobile=$_POST["mobile"];
		$sql="update user set username='$username', email='$email', mobile=$mobile where id=$id";
		if($conn->query($sql)===TRUE){
			echo json_encode("Updated Successfully...");
		}else{
			echo json_encode("Operation Fail...");
		}
		$conn->close();
	}
	function deleteUser($conn){
		$id=$_POST["id"];
		$sql="delete from user where id=$id";
		if($conn->query($sql)===TRUE){
			echo json_encode("Deleted Successfully...");
		}else{
			echo json_encode("Operation Fail...");
		}	
		$conn->close();
	}
	function selectUser($conn){
		$sql="select * from user";
		$result=$conn->query($sql);
		$obj=new stdClass();
		if($result->num_rows>0){
			while($row=$result->fetch_assoc()){
				$data[]=$row;
			}
			$obj->users=$data;
			echo json_encode($obj);
		}else {
		    echo "0 results";
		}
		$conn->close();
	}
?>