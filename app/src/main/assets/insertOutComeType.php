<?php

require "init.php";

$outcomeTypeName = $_POST["outcomeTypeName"];

$sql = "INSERT INTO outcome_type(outcome_type_name, created_date) values('$outcomeTypeName', now());";

if (mysqli_query($conn, $sql)) {
	echo "Values added";
} else {
	echo "Values not add";
}

?>