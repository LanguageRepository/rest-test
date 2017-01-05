/**
 * Created by kvasa on 01.01.2017.
 */
$(document).ready(function setUsernameOnload() {
    document.getElementById('hi').innerHTML = "Привет, " + localStorage["usernameLoginForm"];
});