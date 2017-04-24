<?php

require "init.php";

$outcomeTypeId = $_POST["outcomeTypeId"];
$outcomeTypeName = $_POST["outcomeTypeName"];

$sql = "UPDATE outcome_type SET outcome_type_name = '$outcomeTypeName' WHERE outcome_type_id = '$outcomeTypeId';";

if (mysqli_query($conn, $sql)) {
	echo "Values changed";
} else {
	echo "Values not changed";
}

?>