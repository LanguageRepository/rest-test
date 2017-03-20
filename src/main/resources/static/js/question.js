let answers;

let questionId = location.pathname.toString().split(`/`)[3];

let answersIds = [];

let questionType;

let questionNode;

let insertedAnswer = [];

$(document).ready(function () {
    insertSimpleMarcdownEditor();
    if(questionId != null) {
        getAllAnswerForQuestion(questionId);
    }
});

function insertSimpleMarcdownEditor() {
    let node = new SimpleMDE({
        spellChecker:false
    });
    let firstAnswerNode = new SimpleMDE({
        element: document.getElementById(`answer1`),
        spellChecker:false
    });

    insertedAnswer.push(firstAnswerNode);
    questionNode = node;
}

function addRow() {
    let rootElement = document.getElementById('tablebody');
    let tr = document.createElement('tr');
    tr.setAttribute('id', `row${++tableRowId}`);
    let firstCell = document.createElement('td');
    let secondCell = document.createElement('td');
    let textarea = document.createElement('textarea');
    textarea.setAttribute('id', `answer${tableRowId}`);
    textarea.setAttribute('class', 'form-control');
    textarea.setAttribute('rows', '3');
    textarea.setAttribute('placeholder', 'Введите текст ответа...');
    let rightValue = document.createElement('input');
    rightValue.setAttribute('id', `answerTypeInput${tableRowId}`);
    rightValue.setAttribute('type', 'checkbox');
    rightValue.setAttribute('class', 'form-control');
    rootElement.appendChild(tr);
    tr.appendChild(firstCell);
    tr.appendChild(secondCell);
    firstCell.appendChild(textarea);
    secondCell.appendChild(rightValue);
    let temp = new SimpleMDE({
        element: textarea,
        spellChecker:false,
        tabSize: 1
    });
    insertedAnswer.push(temp);
    return tr;
}

function deleteRow() {
    let rootElement = document.getElementById('tablebody');
    let rootId = document.getElementById('row1').getAttribute('id');
    let currentElement = document.getElementById(`row${tableRowId}`);
    if(!(currentElement.getAttribute('id') == rootId)) {
        rootElement.removeChild(currentElement);
        --tableRowId;
    } else {
        alert('Ответов должно быть не менее одного');
    }

}

function setManyRight() {
    $(`#addAnswer`).attr('disabled', false);
    $(`#deleteAnswer`).attr('disabled', false);
    document.getElementById(`answerTypeInput1`).style.display = "inline";
    typeOfAnswer = "manyRight";
}

function setManualInput() {
    let addAnswerInput = document.getElementById('addAnswer');
    addAnswerInput.setAttribute('disabled', true);
    let deleteAnswerInput = document.getElementById('deleteAnswer');
    deleteAnswerInput.setAttribute('disabled', true);
    let tableBodyNode = document.getElementById('tablebody');
    document.getElementById(`answerTypeInput1`).style.display = "none";
    typeOfAnswer = "manualInput";
    let childArray = document.getElementById(`tablebody`).children;
    while (document.getElementById(`tablebody`).children.length > 2) {
        for (let i = 1; i < childArray.length; i++) {
            if (childArray[i].getAttribute('id') != "row1") {
                tableBodyNode.removeChild(tableBodyNode.lastChild);
            }
        }
    }
}

function redrawTable() {
    for(let i = document.getElementById("tablebody").children.length-3; i >= 0; --i) {
        deleteRow();
    }
    getAllAnswerForQuestion();
}

function getAllAnswerForQuestion(questionId) {
    if(questionId != null) {
        $.ajax({
            url: "/question/" + questionId,
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            mimeType: "application/json",
            success: function (data) {
                answersIds = [];
                answers = data.answers;
                questionNode.value(data.question);
                for (let i = 0; i < data.answers.length; i++) {
                    answersIds.push(data.answers[i].id);
                    insertedAnswer[i].value(data.answers[i].answer);
                    if(i != answers.length-1) {
                        addRow();
                    }
                    if(data.answers[i].rightValue == `true`) {
                        $("#answerTypeInput" + (1 + i)).attr("checked","checked");
                    }
                }
                if(data.type == "manual_input") {
                    setManualInput();
                }
            }
        });
    }
}

function considerAnswer() {
    let answerArray = [];
    let temp;
    let type;
    if(answers != null && answers.length != 0 || answersIds != null) {
        for (let i = 0; i < document.getElementById(`tablebody`).children.length - 1; i++) {
            temp = {
                "id"        : answersIds[i],
                "answer"    : insertedAnswer[i].value(),
                "questionId": parseInt(questionId),
                "type"      : typeOfAnswer == "manyRight" ? "ANSWER_TYPE_BOOL" : "ANSWER_TYPE_STRING",
                "rightValue": document.getElementById(`tablebody`).children[1 + i].children[1].children[0].checked.toString(),
            };
            answerArray.push(temp);
        }
    } else {
        for (let i = 0; i < document.getElementById(`tablebody`).children.length - 1; i++) {
            if(document.getElementById(`tablebody`).children[1 + i].children[2].children[0].checked) {
                questionType.push(true);
            }
            temp = {
                "answer"    : insertedAnswer[i].value(),
                "questionId": parseInt(questionId),
                "type"      : typeOfAnswer == "manyRight" ? "ANSWER_TYPE_BOOL" : "ANSWER_TYPE_STRING",
                "rightValue": document.getElementById(`tablebody`).children[1 + i].children[1].children[0].checked.toString(),
            };
            answerArray.push(temp);
        }
    }
    return answerArray;
}

function getNewAnswersId() {
    $.ajax({
        url: "/question/new/" + questionId,
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        mimeType: "application/json",
        success: function (data) {
            answersIds = data;
        }
    });
}

function buildQuestion() {
    let currentQuestion = {
        "id" : questionId ? parseInt(questionId) : null,
        "question" : questionNode.value(),
        "answers" : considerAnswer(),
        "testId" : localStorage.getItem(`current_test_id`)
    };
    return currentQuestion;
}

