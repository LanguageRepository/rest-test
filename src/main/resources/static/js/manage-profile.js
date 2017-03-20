/**
 * Created by kvasa on 06.01.2017.
 */

let currentUser = $.ajax({
    url: "/user",
    type: "GET",
    dataType: "json",
    contentType: "application/json",
    mimeType: "application/json",
    success: function (data) {
        currentUser = data;
    }
});


function getPin() {
    var username = currentUser.username;
    $.ajax({
        type: "POST",
        url: "/mail/changepassword/" + username,
        timeout: 600000,
        success: function (data) {
            localStorage.setItem("pin", data);
        }
    });
}

function changePassword() {
    let pin = localStorage["pin"];
    let newPassword = $("#new-password").val();
    let confirmPassword = $("#new-password-repeat").val();
    let pinField = $("#pin-input").val();
    let result = {"username":currentUser.username,
                  "password":newPassword};
    if(pin == pinField) {
        $.ajax({
            type:"PUT",
            contentType:"application/json",
            url:"/user/changepassword",
            data:JSON.stringify(result),
            dataType:"json",
            timeout:600000,
            success:function (data) {
                let p = $(`#needed`);
                p.css("color", "#000000");
                p.text("Пароль успешно изменён");
            }
        });
        } else {
            let p = $(`#needed`);
            p.css("color", "red");
            p.text("Проверьте правильность ввода pin-кода");
        }
}
