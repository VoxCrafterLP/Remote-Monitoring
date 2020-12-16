<?php
/*
 * Copyright (c) 2020 Lenny Angst
 * All rights reserved.
 * See LICENSE-file for further information.
 */

require 'php/Locales.php';
require_once 'php/Config.php';
require 'php/database/DatabaseAdapter.php';

session_start();

if (isset($_POST['user'])) {
    header("Location: /app");
    exit();
}

$db = new DatabaseAdapter();

if (!$db->tableExists("users")) {
    header("Location: setup/");
}

$locales = new Locales();
$config = new Config();

?>

<!DOCTYPE html>
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

    <div id="login-container">
        <div class="card" id="login-card">
            <div class="card-body">
                <h3>Remote Monitoring</h3>
                <h4><?php echo $locales->getString("login.title")?></h4>
                <br>
                <input class="form-control" id="login-user" placeholder="<?php echo $locales->getString("login.user")?>"><br>
                <input type="password" class="form-control" id="login-password" placeholder="<?php echo $locales->getString("login.password")?>">
                <p><small class="error" id="login-error"></small></p>
                <button class="btn btn-success" id="login-btn"><?php echo $locales->getString("login.btn.text")?></button>
            </div>
        </div>
        <div id="footer">
            <p><?php echo $locales->getString("footer.text") ?></p>
        </div>
    </div>
<div style="display: none">
    <p id="str_err_blank"><?php echo $locales->getString("err.login.blank")?></p>
    <p id="str_err_general"><?php echo $locales->getString("err.login.general")?></p>
    <p id="str_err_wrong_credentials"><?php echo $locales->getString("err.login.credentials")?></p>
</div>
</body>
<script src="node_modules/jquery/dist/jquery.js"></script>
<script src="assets/js/login.js"></script>
</html>
