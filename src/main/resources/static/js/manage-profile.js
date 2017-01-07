/**
 * Created by kvasa on 06.01.2017.
 */
function getPin() {
    var username = localStorage["usernameLoginForm"];
    $.ajax({
        type: "POST",
        url: "/mail/changepassword?username=" + username,
        timeout: 600000,
        success: function (data) {
            localStorage.setItem("pin", data);
        }
    });
    var placeholderForMessage = document.getElementById("placeholderForMessage");
    placeholderForMessage.setAttribute("class", "bg-success");
    placeholderForMessage.innerText = "Письмо отправлено к Вам на почту";
}

function changePassword() {
    var pin = localStorage["pin"];
    var newPassword = document.getElementById('newPassword').value;
    var confirmPassword = document.getElementById('confirmNewPassword').value;
    var pinField = document.getElementById('pin').value;
    var result = {"username":localStorage["usernameLoginForm"],
                  "password":newPassword};
    if(pin == pinField) {
        if(newPassword == confirmPassword) {
            $.ajax({
                type:"PUT",
                contentType:"application/json",
                url:"/user/changepassword",
                data:JSON.stringify(result),
                dataType:"json",
                timeout:600000,
                success:function (data) {
                    var placeholderForMessage = document.getElementById("placeholderForMessage");
                    placeholderForMessage.setAttribute("class", "bg-info");
                    placeholderForMessage.innerText = "Ваш новый пароль: " + data.status;
                }
            });
        } else {
            var placeholderForMessage = document.getElementById("placeholderForMessage");
            placeholderForMessage.setAttribute("class", "bg-info");
            placeholderForMessage.innerText = "Пароли не совпадают";
        }
    } else {
        var placeholderForMessage = document.getElementById("placeholderForMessage");
        placeholderForMessage.setAttribute("class", "bg-danger");
        placeholderForMessage.innerText = "Неверный пинкод";
    }
}
