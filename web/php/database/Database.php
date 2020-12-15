<?php

class Database {
    public static $con;

    public static function connect() {
        $config = new Config();
        self::$con = mysqli_connect($config->get("database.host"), $config->get("database.user"), $config->get("database.password"), $config->get("database.database"), $config->get("database.port"));
        self::$con->set_charset("utf8");
    }

    public static function disconnect() {
        if(self::isConnected()) {
            mysqli_close(self::$con);
        }
    }

    public static function isConnected() {
        return self::$con != null;
    }

    public static function getConnection() {
        return self::$con;
    }

}