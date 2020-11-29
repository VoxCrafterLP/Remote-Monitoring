<?php


class Database {
    public static $host = "mcsupercraft.net"; //"192.168.1.150";
    public static $user = "supercraft"; //"ap20b";
    public static $password = "nc,+-An(9p,vD(DpPtMSj1&yNpq>Zm"; //"Ru0ZRr*Q903!";
    public static $port = 3306;
    public static $database = "SuperCraft";
    public static $con;

    public static function connect() {
        self::$con = mysqli_connect(self::$host, self::$user, self::$password, self::$database);
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