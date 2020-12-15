let username = $("#login-user");
let password = $("#login-password");
let btn = $("#login-btn");
let error = $("#login-error");

let str_err_blank = $("#str_err_blank").html();
let str_err_general = $("#str_err_general").html();
let str_err_wrong_credentials = $("#str_err_wrong_credentials").html();
let str_btn = btn.html();

$(document).ready(function () {
    username.on("keypress", enterPressEvent);
    password.on("keypress", enterPressEvent);
    btn.on("click", submit);
});

function enterPressEvent(e) {
    if (e.keyCode === 13) {
        submit();
    }
}

function submit() {
    username.removeClass("is-invalid");
    password.removeClass("is-invalid");
    error.text("");
    if (username.val() === "" || password.val() === "") {
        username.addClass("is-invalid");
        password.addClass("is-invalid");
        error.text(str_err_blank);
        return null;
    }

    btn.html('<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>');

    $.ajax({
        url: location.protocol + "//" + location.hostname + "/php/login.php",
        method: "POST",
        data: {
            username: username.val(),
            password: password.val()
        },
        success: function (data, textStatus, xhr) {
            if (data === "900") {
                window.location.href = location.protocol + "//" + location.hostname + "/app/";
                return;
            } else if (data === "901") {
                error.html(str_err_wrong_credentials);
                username.addClass("is-invalid");
                password.addClass("is-invalid");
            } else {
                error.html(str_err_general);
            }
            btn.html(str_btn);
        },
        error: function (data) {
            error.html(str_err_general);
            btn.html(str_btn);
        }
    })
}