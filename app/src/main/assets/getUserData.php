<?php
	//$host="localhost";
	//$database="id1414255_doanh_database";
	//$username="id1414255_doanh";
	//$password="doanh";

    $host="localhost";
    $database="doanh";
    $username="root";
    $password="";


	$sql= "select name, password, contact, country from user;";
	$con = mysqli_connect($host,$username,$password,$database);

	$result= mysqli_query($con,$sql);

	$response= array();

	while($row=mysqli_fetch_array($result)) {
	    array_push($response,array("name"=>$row[0],"password"=>$row[1],"contact"=>$row[2],"country"=>$row[3]));

	}
	echo json_encode(array("server_response"=>$response));
	mysqli_close($con);
?>