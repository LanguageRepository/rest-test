let tasks = [];

$(document).ready(function () {
    findTasks();
});

function findTasks() {
    $.ajax({
        url: "/testaccess/currentuser",
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        mimeType: "application/json",
        success : function (data) {
            tasks = data;
            renderTaskTable();
        }
    });
}

function renderTaskTable() {
    $("#task-table").DataTable({
        responsive : true,

        "aaData" : tasks.map(item => {
            let result = [];
            result.push(item.testName);
            result.push(item.ownerName);
            result.push(item.initialTerm + " до " + item.deadline);
            result.push(item.type == "Control" ? "Контрольный" : "Обычный");
            result.push("Осталось " + item.numberOfAttempts + " попытки");
            result.push(item.active ? renderButton()[1] : renderButton()[0]);
            return result;
        })
    })
}

function renderButton() {
    return [`<button type="button" disabled class="btn btn-danger">Приступить</button>`,
            `<button type="button" class="btn btn-info">Приступить</button>`];
}