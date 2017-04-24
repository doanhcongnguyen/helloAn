<?php

require "init.php";

$outcomeTypeId = $_POST["outcomeTypeId"];
$outcomeDate = array_key_exists("outcomeDate", $_POST) ? $_POST["outcomeDate"] : '';
$day = array_key_exists("day", $_POST) ? $_POST["day"] : 0;
$month = array_key_exists("month", $_POST) ? $_POST["month"]: 0;
$year = array_key_exists("year", $_POST) ? $_POST["year"]: 0;
$note = array_key_exists("note", $_POST) ? $_POST["note"]: '';


$sql = "INSERT INTO outcome (outcome_type_id, outcome_date, day, month, year, note, created_date) 
					values  ('$outcomeTypeId', '$outcomeDate', '$day', '$month', '$year', '$note', now());";

if (mysqli_query($conn, $sql)) {
	echo "Values added";
} else {
	echo "Values not add";
}

?>