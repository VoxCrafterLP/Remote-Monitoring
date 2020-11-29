<?php

session_start();

if (isset($_POST['user'])) {
    header("Location: /app");
    exit();
}

