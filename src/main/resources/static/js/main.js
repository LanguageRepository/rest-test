/**
 * Created by kvasa on 01.01.2017.
 */

const profileValidation = {
    rules : {
        pin_input : {
            required:true,
            minlength: 2
        },
        new_password : {
            required:true,
            minlength: 6,
            equalTo: "#new-password-repeat"
        },
        new_password_repeat : {
            required:true,
            minlength: 6
        }
    },
    messages : {
        pin_input : {
            required:"",
            minlength: ""
        },
        new_password : {
            required:"Поле не должно оставаться пустым",
            minlength: "Введите не менее 6-ти символов",
            equalTo:"Пароли не соответствуют"
        },
        new_password_repeat : {
            required:"Поле не должно оставаться пустым",
            minlength: "Введите не менее 6-ти символов"
        }
    }
};

$(document).ready(function () {
    if(location.pathname.split("/")[2] == `profile`) {
        formValidation();
    }
});

function manageAttribute() {
    $(`#pin-input`).removeAttr(`disabled`); 
    $(`#pin-button`).removeAttr(`disabled`);
    $("#helpText").css('display','block');
}

function formValidation() {
    $("#profileForm").validate(profileValidation);
}
