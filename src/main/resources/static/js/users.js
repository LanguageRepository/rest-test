/**
 * Created by kvasa on 08.01.2017.
 */

const username = localStorage.getItem("usernameLoginForm");

const validator = {
    rules : {
        form_login : {
            required:true,
            minlength: 2
        },
        form_email : {
            required:true,
            email:true
        },
        form_password : {
            required:true,
            minlength: 6
        },
        form_confirm_password : {
            required:true,
            equalTo:"#pswd",
        },
        form_firstname : {
            required:true,
            minlength: 2
        },
        form_middlename : {
            required:true,
            minlength: 2
        },
        form_lastname : {
            required:true,
            minlength: 2
        }
    },
    messages : {
        form_login : {
            required: "Поле 'Логин' должно быть заполнено",
            minlength: "Введите не менее двух символов в поле 'Логин'"
        },
        form_email : {
            required: "Поле 'Email' должно быть заполнено",
            email: "Проверьте правильность ввода почтового адреса"
        },
        form_password : {
            required: "Поле 'Пароль' должно быть заполнено",
            minlength: "Пароль должен состоять из 6 и более символов"
        },
        form_confirm_password : {
            required: "Поле должно быть заполнено",
            equalTo: "Пароли не совпадают"
        },
        form_firstname : {
            required: "Поле 'Имя' должно быть заполнено",
            minlength: "Введите не менее двух символов в поле 'Имя'"
        },
        form_middlename : {
            required: "Поле 'Отчевство' должно быть заполнено",
            minlength: "Введите не менее двух символов в поле 'Отчевство'"
        },
        form_lastname : {
            required: "Поле 'Фамилия' должно быть заполнено",
            minlength: "Введите не менее двух символов в поле 'Фамилия'"
        }
    }
};

function departmentToTree(json) {
    $(function() {
        $('#tree').jstree({
            'core' : {
                'data' : JSON.parse(json),
                'themes': {
                    'name': 'proton',
                    'responsive': true
                }
            }

        })
    });
    $('#tree').on("changed.jstree", function (e, data) {
        localStorage.setItem("current_dep_id", data.instance.get_selected(true)[0].id);
    });
    $('#tree').on("loaded.jstree", function (e, data) {
        data.instance.select_node(localStorage.getItem("dep_id"));
    })
}

$(document).ready(function () {
    $("#current-hello").text(`Здраствуйте, ${username}`);
    let refId = window.location.pathname.charAt(8);
    if(window.location.href.endsWith('/cpanel')) {
        refId = "";
    }
    $.ajax({
        type : "GET",
        url  : "/user/" + refId,
        dataType : "json",
        contentType  : "application/json",
        mimeType : "application/json",
        success  : function (data) {
            $("#login_form").val(data.username);
            $("#email_form").val(data.email);
            $("#firstname_form").val(data.firstName);
            $("#lastname_form").val(data.lastName);
            $("#middlename_form").val(data.middleName);
            $("#description_form").val(data.description);
            $("#department_form").val(`${data.department}(ID: ${data.department_id})`);
            $("#short-name").text(data.firstName);
            $("#short-description").text(`${data.department}(ID: ${data.department_id})`);
            $("#short-username").text(data.id);
            $(`#role_choose :contains(${data.role})`).attr("selected", true);
            localStorage.setItem("dep_id", data.department_id);
        }
    });
});

$(document).ready(function getAllDepartment() {
    $.ajax({
        async   : true,
        type    : "GET",
        url     : "/department/all",
        dataType: "json",
        success : function (data) {
            departmentToTree(JSON.stringify(data));
        },
        error   : function () {
            alert("Не удалось получить данные");
        }
    });
});

$(document).ready(function getAllUsersForTable() {
    $.ajax({
        url: "/user/fortable",
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        mimeType: "application/json",
        success: function (data) {
            $('#usersTable').dataTable({
                responsive: true,
                "aaData": data.map(item => {
                    var result = [];
                    result.push("<p id='"+ item.id +"'>" + item.fullName + "</p>");
                    result.push(item.department);
                    result.push(item.role);
                    result.push(item.email);
                    result.push(createDropdown(item));
                    return result;
                })
            });
        }
    });
});

$(document).ready(function () {
    $("#user-creation-form").validate(validator);
});

function updateUser() {
    var checkOperation = document.getElementById('reset-btn').getAttribute('disabled');
    var username = $("#login_form").val();
    var password = $("#pswd").val();
    var email = $("#email_form").val();
    var firstName = $("#firstname_form").val();
    var lastName = $("#lastname_form").val();
    var middleName = $("#middlename_form").val();
    var description = $("#description_form").val();
    var department = localStorage.getItem("current_dep_id");
    var role = $("#role_choose").find("option:selected").text();
    var resultJson = {
        "username" : username,
        "password" : password,
        "role" : role,
        "lastName" : lastName,
        "firstName" : firstName,
        "middleName" : middleName,
        "email" : email,
        "description" : description,
        "department_id" : department
    };
    console.log(JSON.stringify(resultJson));
    if(checkOperation) {
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/user/",
            data: JSON.stringify(resultJson),
            dataType: 'json',
            mimeType: "application/json",
            success: function (data) {
                alert("Пользователь" + data.name + " успешно обновлён");
                console.log(data.name);
            },
            error: function () {
                alert("Пожалуйста, заполните необходимые поля перед отправкой запроса");
            }
        });
    } else {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/user/",
            data: JSON.stringify(resultJson),
            dataType: 'json',
            success: function (data) {
                alert("Пользователь" + data.name + " успешно создан");
            },
            error: function () {
                alert("Пожалуйста, заполните необходимые поля перед отправкой запроса");
            }
        });
    }
}

function unlockButton() {
    $("#login_form").attr("disabled", false);
}

function createDropdown(item) {
    let dropdown = "<div class='dropdown'>" +
        "<button class='btn btn-success dropdown-toggle' " +
        "type='button' id='dropdownMenu1' " +
        "data-toggle='dropdown' " +
        "aria-haspopup='true' " +
        "aria-expanded='true'>Опции" +
        "<span class='caret'></span>" +
        "</button>" +
        "<ul class='dropdown-menu' " +
        "aria-labelledby='dropdownMenu1'>" +
        `<li><a href='/cpanel/${item.id}'>Редактировать</a></li>` +
        "<li><a href='#'>Удалить</a></li>" +
        "</ul>" +
        "</div>";
    return dropdown;
}
