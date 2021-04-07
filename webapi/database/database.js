const mysql = require('mysql');
const fs = require('fs');
const path = require('path');
const config = require('../config/config.json').database;

config.multipleStatements = true;

const connection = mysql.createConnection(config);
connection.connect();

fs.readFile(path.join(__dirname, 'database.sql'), 'UTF-8', (err, data) => {
    if(err) {
        console.log('An error occurred during the reading of the database structure file.');
        console.log(err);
        return;
    }

    data.split('monitoring').join(config.database);

    connection.query(data, (err) => {
        if(err) throw err;
    });

    console.log("Successfully connected to the database.");
});

global.connection = connection;