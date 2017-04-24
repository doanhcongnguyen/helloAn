<?php

require "init.php";

$outcomeId = $_POST["outcomeId"];
$outcomeTypeId = $_POST["outcomeTypeId"];
$outcomeDate = array_key_exists("outcomeDate", $_POST) ? $_POST["outcomeDate"] : '';
$day = array_key_exists("day", $_POST) ? $_POST["day"] : 0;
$month = array_key_exists("month", $_POST) ? $_POST["month"]: 0;
$year = array_key_exists("year", $_POST) ? $_POST["year"] : 0;
$note = array_key_exists("note", $_POST) ? $_POST["note"] : '';

$sql = "UPDATE outcome 
		SET outcome_date = '$outcomeDate', 
			outcome_type_id = '$outcomeTypeId',
			day = '$day',
			month = '$month',
			year = '$year',
			note = '$note'
		WHERE outcome_id = '$outcomeId';";

if (mysqli_query($conn, $sql)) {
	echo "Values changed";
} else {
	echo "Values not changed";
}

?>