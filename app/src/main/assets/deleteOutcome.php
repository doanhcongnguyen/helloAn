<?php

require "init.php";

$outcomeId = $_POST["outcomeId"];

$sql = "DELETE FROM outcome WHERE outcome_id = '$outcomeId';";

if (mysqli_query($conn, $sql)) {
	echo "Values removed";
} else {
	echo "Values not removed";
}

?>