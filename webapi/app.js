const express = require('express')
const path = require('path')
const database = require('./database/database');
const helmet = require('helmet');

const app = express();

//Init middleware
app.use(helmet());

//Body parser Middleware
app.use(express.json());
app.use(express.urlencoded({extended: false}));

//Static
app.use(express.static(path.join(__dirname, 'public')));

//Routes
app.use('/api/auth', require('./routes/api/authentication'));


const PORT = process.env.PORT || 5000;

app.listen(PORT, () => console.log(`Server started on port ${PORT}.`));