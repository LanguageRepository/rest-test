let tasks = [];

$(document).ready(function () {
    if(location.pathname.includes('taskservice')) {
        findTasks();
    }
});

function findTasks() {
    $.ajax({
        url: "/testaccess/currentuser",
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        mimeType: "application/json",
        success: function (data) {
            tasks = data;
            renderTaskTable();
        }
    });
}

function renderTaskTable() {
    $("#task-table").DataTable({
        responsive: true,

        "aaData": tasks.map(item => {
            let result = [];
            result.push(item.testName);
            result.push(item.ownerName);
            result.push(item.initialTerm + " до " + item.deadline);
            result.push(item.type == "Control" ? "Контрольный" : "Обычный");
            result.push("Осталось " + item.numberOfAttempts + " попытки");
            result.push(item.active ? renderButton(item.id)[1] : renderButton(item.id)[0]);
            return result;
        })
    })
}

function renderButton(id) {
    return [`<button type="button" id="${id}" disabled class="btn btn-danger" onclick="saveCurrentTestProcessingId(${id})">Приступить</button>`,
            `<button type="button" id="${id}" class="btn btn-info" onclick="saveCurrentTestProcessingId(${id})">Приступить</button>`];
}

function findChoosenValue() {
    let values = $('input:checked');
    let choosenValues = [];
    choosenValues.push({
        answerId: function () {
            return 0;
        },
        questionId: $('.card-title')[0].id
    });

    return choosenValues;
}

function saveCurrentTestProcessingId(id) {
    localStorage.setItem("tpId", id);
    location.pathname = "/test-processing";
}