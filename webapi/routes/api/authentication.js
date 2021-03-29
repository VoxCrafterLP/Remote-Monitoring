const express = require('express');
const router = express.Router();
const cookieParser = require('cookie-parser');
const session = require('express-session');
const bodyParser = require('body-parser');
const bcrypt = require('bcrypt');

router.use(cookieParser());
router.use(session({
    secret: "Change me",
    resave: false,
    saveUninitialized: false,
    cookie: {
        maxAge: Number.MAX_VALUE,
        sameSite: true
    }
}));
router.use(bodyParser.urlencoded({
    extended: true
}))

//Get authentication status
router.get('/', (req, res) => {
    return res.status(200).json({status: req.session.user_id ? "Logged in" : "Logged out"});
});

//Login
router.post('/login', (req, res) => {
    if(req.session.user_id)
        return res.status(400).json({status: "Already logged in"});

    const {email, password} = req.body;

    if(email && password) {
        global.connection.query("SELECT * from users WEHRE email=?", [email], (err, result) => {
            if(err) throw err;


        });
    } else
        return res.status(400).json({status: "Invalid credentials"});
});

//Logout
router.get('/logout', (req, res) => {

});

module.exports = router;