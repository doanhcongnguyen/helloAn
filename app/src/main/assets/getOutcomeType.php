<?php

require "init.php";

$sql = "SELECT outcome_type_id, outcome_type_name, is_active, note FROM outcome_type;";
$result = mysqli_query($conn, $sql);

$response = array();
while ($row = mysqli_fetch_array($result)) {
	array_push($response,  
		array(
			"outcomeTypeId" => $row[0], 
			"outcomeTypeName" => $row[1], 
			"isActive" => $row[2], 
			"note" => $row[3])
		);
}
echo json_encode(array("outcome_type" => $response));

mysqli_close($conn);

?>