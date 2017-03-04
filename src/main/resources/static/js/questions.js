let questions;

let questionIdIteration = 0;

let curId = location.pathname.toString().split(`/`)[2];

$(document).ready(function () {
    if(location.pathname.toString().split("/")[1] == "questions") {
        getAllQuestionsForTable(curId);
        setTestName();
    }
});

function getAllQuestionsForTable(id) {
    $.ajax({
        url: "/question/test/" + id,
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        mimeType: "application/json",
        success: function (data) {
            questions = data;
            renderQuestionTable(data);
        }
    });
}

function setTestName() {
    let a = document.createElement('a');
        a.setAttribute(`class`, `btn btn-info btn-fill pull-right`);
        a.setAttribute(`href`, `/answers`);
        a.innerText = `Добавить вопрос`;
    $.ajax({
        url: "/question/test/name/" + curId,
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        mimeType: "application/json",
        success: function (data) {
            let testName = document.getElementById(`testName`);
            testName.innerText = `Список вопросов для теста ` + `\"${data.name}\"`;
            testName.appendChild(a);
        },
        error : function () {
            $(`#testName`).text(`Список вопросов для теста`);
        }
    });
}

function renderQuestionTable(data) {
    $('#questionTable').dataTable({
        responsive: true,
        columnDefs: [
            {
                targets: [ 0, 1, 2 ],
                className: 'mdl-data-table__cell--non-numeric'
            }],
        "aoColumnDefs": [
            { "sWidth": "120px", "aTargets": [ 3 ] }
        ],
        "aaData": questions.map(item => {
            let result = [];
            result.push(++questionIdIteration);
            result.push(item.name);
            result.push(item.testName);
            result.push(createButtons(item));
            return result;
        })
    });
}

function createButtons(item) {
    return `<div class="btn-group">` +
           `<a class="btn btn-info btn-fill btn-sm" href="/answers/${item.id}" onclick=""><span class="pe-7s-angle-left"></span></a>` +
           `<button class="btn btn-danger btn-fill btn-sm" onclick="deleteQuestion(${item.id})"><span class="pe-7s-close"></span></button>` +
           `</div>`;
}

function deleteQuestion(id) {
    if(confirm(`Вы подтверждаете удаление?`)) {
        $.ajax({
            type: `DELETE`,
            url: `/question/` + id,
            success: function () {
                questions = questions.filter(function (elem) {
                    return elem.id != id;
                });
                $("#questionTable").DataTable().destroy();
                renderQuestionTable();
            }
        });
    }
}

function createQuestion() {
    $.ajax({
        url: "/question/",
        type : "PUT",
        contentType: "application/json",
        data: JSON.stringify(buildQuestion()),
        dataType: 'json',
        mimeType : "application/json",
        success : function () {
        },
        error : function () {
            getNewAnswersId();
        }
    });

}