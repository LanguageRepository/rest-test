/**
 * Created by kvasa on 08.01.2017.
 */

const username = localStorage.getItem("usernameLoginForm");

const userValidator = {
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

const departmentValidator = {
    rules : {
        department_name : {
            required:true
        }
    },
    messages : {
        department_name : {
            required: "Поле 'Подразделение должно быть заполнено'"
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
            $(`#role_choose :contains(${data.simpleRole})`).attr("selected", true);
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

$(document).ready(function () {
    $.ajax({
        url: "/department/all/short",
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        mimeType: "application/json",
        success: function (data) {
            for(let i = 0; i < data.length; i++) {
                console.log(i + data[i].name);
                if(data[i].type == "unv") {
                    $("#univ").append(`<option value="${data[i].id}">${data[i].name}</option>`);
                }else if(data[i].type == "fclt") {
                    $("#facult").append(`<option value="${data[i].id}">${data[i].name}</option>`);
                }else if(data[i].type == "caf") {
                    $("#cafedra").append(`<option value="${data[i].id}">${data[i].name}</option>`);
                }else if(data[i].type == "group") {
                    $("#group").append( `<option value="${data[i].id}">${data[i].name}</option>`);
                }else {
                    $("#other").append( `<option value="${data[i].id}">${data[i].name}</option>`);
                }
            }
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
                "aoColumnDefs": [
                    { "sWidth": "93px", "aTargets": [ 4 ] }
                ],
                "aaData": data.map(item => {
                    var result = [];
                    result.push("<p id='"+ item.id +"'>" + item.fullName + "</p>");
                    result.push(item.department);
                    result.push(item.simpleRole);
                    result.push(item.email);
                    result.push(createButtons(item));
                    return result;
                })
            });
        }
    });
});

$(document).ready(function () {
    $("#user-creation-form").validate(userValidator);
    $("#department_form_creation").validate(departmentValidator);
});

$(document).ready(function () {
    $(`.selectpicker`).selectpicker({
        style : `btn-info`,
        size  : 4
    })
});

function updateUser() {
    let username = $("#login_form").val(),
        password = $("#pswd").val(),
        email = $("#email_form").val(),
        firstName = $("#firstname_form").val(),
        lastName = $("#lastname_form").val(),
        middleName = $("#middlename_form").val(),
        description = $("#description_form").val(),
        department = localStorage.getItem("current_dep_id"),
        role = $("#role_choose").find("option:selected").text(),
        notify = false;
    if($('#notify').prop('checked')) {
        notify = true;
    }
    let resultJson = {
        "username": username,
        "password": password,
        "role": role,
        "lastName": lastName,
        "firstName": firstName,
        "middleName": middleName,
        "email": email,
        "description": description,
        "department_id": department,
        "notifyByMail": notify
    };
    console.log(JSON.stringify(resultJson));
    if(!(username == "" || password == "" || email == "" || role == "")) {
        if ($("#login_form").prop("disabled")) {
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
}

function unlockButton() {
    $("#login_form").attr("disabled", false);
}

function createButtons(item) {
    return `<div class="btn-group pull-right" role="group">` +
        `<a class="btn btn-primary btn-fill" href="/cpanel/${item.id}" role="button">Обновить</a>` +
        `<button class="btn btn-primary"` +
        `type="submit" onclick="saveDelete(${item.id})">Удалить</button></div>`;
}

function saveDelete(id) {
    if(confirm("Удалить пользователя?")) {
        $.ajax({
            url: `/user/delete/${id}`,
            type: `DELETE`,
            success: function (data) {
                console.log(data.message);
            }
        });
        location.reload(true);
    }
}

function createDepartment() {
    if($("#department-update").attr("disabled", true)) {
        updateDepartment();
    } else {
        let department = $("#department-input").val(),
            parent_department = $("#parent-select").find(":selected").val(),
            result = {
                "name": department,
                "parent_id": parent_department
            };
        if (!(department == "")) {
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/department/",
                data: JSON.stringify(result),
                dataType: 'json',
                success: function (data) {
                    console.log(true);
                }
            });
        }
    }
}

function updateDepartment() {
    $("#department_choose").text("Выберете узел для обновления");
    $("#department-update").attr("disabled", true);
    let department = $("#department-input").val(),
        department_choose = $("#parent-select").find(":selected").val(),
        result = {
            "name"      : department,
            "parent_id" : department_choose
        };
    if(!(department == "")) {
        $.ajax({
            type : "PUT",
            contentType : "application/json",
            url : "/department",
            data: JSON.stringify(result),
            success : function () {
                console.log("Подразделение создано");
            }
        })
    }
}
