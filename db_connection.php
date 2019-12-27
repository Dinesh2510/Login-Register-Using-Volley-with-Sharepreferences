<?php

// $servername = "localhost";
// $username = "onthejf4_car";
// $password = "a?u~?vObu1uL";
// $dbname = "onthejf4_car_booking";

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "app_data";

$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
date_default_timezone_set("Asia/Kolkata");
mysqli_set_charset($conn, "utf8");
?>