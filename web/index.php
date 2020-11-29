<?php

require 'php/Locales.php';

session_start();

if (isset($_POST['user'])) {
    header("Location: /app");
    exit();
}

$locales = new Locales();

?>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Remote Monitoring | Login</title>
    <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>

    <div class="login-container">
        <div class="login-content">
            <span>SPAN</span>
        </div>
    </div>

</body>
</html>
