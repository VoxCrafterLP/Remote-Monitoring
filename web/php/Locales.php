<?php

require 'Config.php';

class Locales {

    private $path;
    private $strings;

    /**
     * Locales constructor.
     */
    public function __construct() {
        $config = new Config();
        $this->path =  __DIR__ . "/../assets/locales/" . $config->get("language") . ".lang";
        $this->strings = json_decode(file_get_contents($this->path), true);
    }

    public function getString($path) {
        return $this->strings[$path];
    }
}