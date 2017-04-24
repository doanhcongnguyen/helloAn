<?php

require "init.php";

$sql = "SELECT outcome_type_id, outcome_date, day, month, year, note FROM outcome;";
$result = mysqli_query($conn, $sql);

$response = array();
while ($row = mysqli_fetch_array($result)) {
	array_push($response,  
		array(
			"outcomeTypeId" => $row[0], 
			"outcomeDate" => $row[1], 
			"day" => $row[2], 
			"month" => $row[3],
			"year" => $row[4],
			"note" => $row[5]
			)
		);
}
echo json_encode(array("outcome" => $response));

mysqli_close($conn);

?>