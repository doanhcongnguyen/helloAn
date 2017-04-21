<?php

 require "init.php";
 
 $name=$_POST["name"];
 $email=$_POST["email"];
 $contact=$_POST["contact"];
 $password=$_POST["password"];
 
 $sql= "insert into users values('$name','$email','$contact','$password');";
 
 if(mysqli_query($con,$sql))
 {
     echo "values added";
     
 }
 else{
     
      echo "values not added";
 }
 

?>