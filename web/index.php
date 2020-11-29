<?php

require 'php/Locales.php';
require 'php/Config.php';
require 'php/database/DatabaseAdapter.php';

session_start();

if (isset($_POST['user'])) {
    header("Location: /app");
    exit();
}

$db = new DatabaseAdapter();

$locales = new Locales();
$config = new Config();

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
<body style="background-image: url('assets/img/bg1.jpg'); background-repeat: no-repeat; background-size: cover">

    <div class="login-container">
        <div class="card" id="login-card">
            <div class="card-body" style="text-align: center; padding: 30px 100px">
                <h3>Remote Monitoring</h3>
                <h4>Login</h4>
                <br>
                <input class="form-control" id="login-user" placeholder="Username"><br>
                <input type="password" class="form-control" id="login-user" placeholder="Password"><br>
                <button class="btn btn-success" id="login-btn">Login</button>
            </div>
        </div>
    </div>

</body>
</html>
