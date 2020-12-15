<?php

if (!isset($_POST['username']) || !isset($_POST['password'])) {
    http_send_status(400);
    exit();
}

echo 900;