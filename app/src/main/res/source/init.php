<?php

//$host="doanh.000webhost.com";
$host = "localhost";
$database="id1414255_doanh_database";
$username="id1414255_doanh";
$password="doanh";

$con=mysqli_connect($host,$username,$password,$database);

if(!con){
    
    echo "Connection unsuccessful";
}
else
{
    echo "Connection successful";
    
}

?>