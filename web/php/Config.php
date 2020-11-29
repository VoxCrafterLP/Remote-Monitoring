<?php

class Config {

    private $config;

    /**
     * Config constructor.
     */
    public function __construct() {
        $this->config = json_decode(file_get_contents( __DIR__ . "/../assets/config.json"), true);
    }

    public function get($property) {
        return $this->config[$property];
    }

}