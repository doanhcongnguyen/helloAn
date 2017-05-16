<?php

require "init.php";

$name = $_POST["name"];
$password = $_POST["password"];
$contact = $_POST["contact"];
$country = $_POST["country"];

$sql = "INSERT INTO user(name, password, contact, country) values('$name', '$password', '$contact', '$country');";

if (mysqli_query($conn, $sql)) {
	echo "Values added";
} else {
	echo "Values not add";
}

?>