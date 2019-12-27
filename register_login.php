<?php

include 'db_connection.php';


if($_SERVER['REQUEST_METHOD']=='POST'){

	$name = $_POST['name']; 
    $address = $_POST['address'];
	$password = $_POST['password'];
	$email = $_POST['email'];
    $phone = $_POST['phone'];
  

}

if($_SERVER['REQUEST_METHOD']=='GET'){

	$name = $_GET['name']; 
    $address = $_GET['address'];
	$password = $_GET['password'];
	$email = $_GET['email'];
    $phone = $_GET['phone'];
}


	
 
 $sql = "INSERT INTO user_data ( name,password,address,email,phone)VALUES
 									('$name','$password','$address','$email','$phone')";

 $result=mysqli_query($conn,$sql);


if($result)
    {
    

    $response = array("response"=>"success");
      echo json_encode($response);
    }
else
    {
      $response = array("response"=>"failure");
      echo json_encode($response);
    }
?>