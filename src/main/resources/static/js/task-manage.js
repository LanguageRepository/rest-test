const optional_config = {
    dateFormat: "d.m.Y h:i",
    enableTime: true
};

let users = [];

let tests = [];

let currentTestAccessId = location.pathname.split(`/`)[3] == "" ? null : location.pathname.split(`/`)[3];

let currentTestAccess = [];

$(document).ready(function () {
    getAllUsers(currentTestAccessId);
    getAllTests();
    if(currentTestAccessId != null) {
        getCurrentTestAccess();
    }
});

function useFlatPickr() {
    activeFrom = new Flatpickr(document.getElementById('active-from'), optional_config);
    activeTo = new Flatpickr(document.getElementById('active-before'), optional_config);

}

function getCurrentTestAccess() {
        $.ajax({
            url: "/testaccess/" + currentTestAccessId,
            type: "GET",
            dataType: "json",
            success: function (data) {
                currentTestAccess = data;
                $("#timeToPass").val(data.timeToPass);
                activeFrom.setDate(data.initialTerm);
                activeTo.setDate(data.deadline);
                $(`#numberOfAttempts`).val(data.numberOfAttempts);
                data.type == "Control" ? $("#typeOfTest [id='opt2']").attr("selected", "selected"):
                     $("#typeOfTest [id='opt1']").attr("selected", "selected");
            }
        });
}

function getAllUsers(id) {
    if(id != null) {
        $.ajax({
            url: "/testaccess/users/" + id,
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            mimeType: "application/json",
            success : function (data) {
                users = data;
                renderUserTable();
            }
        });
    } else {
        $.ajax({
            url: "/user/fortable",
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            mimeType: "application/json",
            success : function (data) {
                users = data;
                renderUserTable();
            }
        });
    }
}

function getAllTests() {
    $.ajax({
        url: "/test/table",
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        mimeType: "application/json",
        success: function (data) {
            tests = data;
            renderTestSelect();
        }
    });
}

function renderUserTable() {
    let checkbox = function (item) {
        if(item.accessByCurrentTest) {
            return `<input type="checkbox" id="${item.id}" checked>`;
        } else {
            return `<input type="checkbox" id="${item.id}">`;
        }
    };
    let i = 0;
    $("#userTable").DataTable({
        "aaData" : users.map(item => {
            let result = [];
            result.push(++i);
            result.push(item.fullName);
            result.push(item.department);
            result.push(checkbox(item));
            return result;
        })
    });
}

function renderTestSelect() {
    let mainSelect = document.getElementById('sp');
    for(let i = 0; i < tests.length; i++) {
        let newOption = document.createElement('option');
        newOption.innerText = tests[i].name;
        newOption.setAttribute('id', tests[i].id);
        mainSelect.appendChild(newOption);
    }
}

function buildTaskData() {
    let testId = $("#sp").find("option:selected").attr(`id`);
    let userIds = function () {
        let idArray = [];
        let result = [];
        for(let i = 0; i < $("#userTable").find(`input:checked`).length; i++) {
            idArray.push($("#userTable").find(`input:checked`)[i].id);
        }
        for (let i = 0; i < idArray.length; i++)
            result.push({ id: idArray[i] });
        return result;
    };
    let activeFrom = $(`#active-from`).val();
    let activeBefore = $(`#active-before`).val();
    let numberOfAttempts = $('#numberOfAttempts').val();
    let typeOfTask = $("#typeOfTest").find("option:selected").text() == "Обязательный" ? "Control" : "Default";
    let timeToPass = $("#timeToPass").val();
    return {
        "id" : currentTestAccessId,
        "testDto": {id : testId},
        "users": userIds(),
        "initialTerm": activeFrom,
        "deadline": activeBefore,
        "numberOfAttempts" : numberOfAttempts,
        "type" : typeOfTask,
        "timeToPass" : timeToPass
    };
}

function createTask() {
    $.ajax({
        type : "POST",
        url  : "/testaccess",
        contentType : "application/json",
        data : JSON.stringify(buildTaskData()),
        dataType : "json",
        success : function (data) {
            alert(`Задание успешно создано`);
        }
    });
}