<?php

include "db_connection.php";

$name = null;
$email=null;
$phone=null;
$address=null;
$password=null;
$user_id=null;


if($_SERVER['REQUEST_METHOD']=='POST')
{
        $email=$_POST['email'];
        $password=$_POST['password'];

       
}


if($_SERVER['REQUEST_METHOD']=='GET')
{
   
        $email=$_GET['email'];
        $password=$_GET['password'];
       
   
}


   
$sql_select="SELECT * FROM `user_data` WHERE `email`='$email' && `password`= '$password' ";
$result_select=mysqli_query($conn,$sql_select);

$result_count=mysqli_num_rows($result_select);

if($result_count>0)
{
    while($row=mysqli_fetch_array($result_select))
        {
            $name=$row['name'];
            $phone=$row['phone'];
            $address=$row['address'];
            $password=$row['password']; 
            $email=$row['email']; 
             $user_id=$row['user_id'];
           
  

             $UserDetails=array(
                "user_id" =>$user_id,
                                "name" =>$name,
                                "phone"=>$phone,
                                "address" => $address,
                                "password" => $password,
                                "email" => $email
                                

                                );
     

        }

   
   
}

    if($result_count)
    {          
       $response=array("response"=> $UserDetails);
       echo json_encode($response);
       
    }else
    {
        $response=array("response"=> "failure");
        echo json_encode($response);
    }

   


?>