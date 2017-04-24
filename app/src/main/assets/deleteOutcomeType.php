<?php

require "init.php";

$outcomeTypeId = $_POST["outcomeTypeId"];

$sql = "UPDATE outcome_type SET is_active = 0 WHERE outcome_type_id = '$outcomeTypeId';";

if (mysqli_query($conn, $sql)) {
	echo "Values removed";
} else {
	echo "Values not removed";
}

?>