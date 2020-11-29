<?php

class Config {

    private $configuration;

    /**
     * Config constructor.
     */
    public function __construct() {
        $this->configuration = json_decode(file_get_contents( __DIR__ . "/../assets/config.json"), true);
    }

    public function get($property) {
        return $this->configuration[$property];
    }

}