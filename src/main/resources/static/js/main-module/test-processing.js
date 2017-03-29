let test = [];

let listOfButtons = [];

let currentButton = [];

$(document).ready(function () {
    getCurrentTestProcessing(localStorage["tpId"]);
});

function getCurrentTestProcessing(id) {
    $.ajax({
        url : "/test-processing/" + id,
        type : "GET",
        dataType : "json",
        contentType : "application/json",
        success : function (data) {
            test = data;
            renderButtonForList();
        }
    });
}

function renderButtonForList() {
    let listOfQuestionsNumber = $(`#listOfQuestionNumbers`);
    for(let i = 0, j = test.questions.length; i < test.questions.length, j > 0; ++i, --j) {
        let button = document.createElement('button');
        button.setAttribute('type', 'button');
        button.setAttribute('class', 'list-group-item list-group-item-action');
        button.setAttribute('id', "button" + j);
        button.setAttribute('name', test.questions[i].serialNumber);
        button.setAttribute('onclick', `renderQuestion(${test.questions[i].serialNumber}, ${j})`);
        button.setAttribute('class', 'list-group-item list-group-item-action');
        button.innerText = j;
        listOfButtons.push(button);
        listOfQuestionsNumber.prepend(button);
    }
    listOfButtons.reverse();
    $(`#button1`).attr('class', 'list-group-item list-group-item-action active');
    $(`#button1`).click();
}

function renderQuestion(serialNumber, buttonId) {
    for (let i = 0; i < listOfButtons.length; i++) {
        listOfButtons[i].setAttribute('class', 'list-group-item list-group-item-action');
        if(listOfButtons[i].id == "button" + buttonId) {
            currentButton = {
                button : listOfButtons[i],
                serialNumber : serialNumber,
                buttonId : buttonId
            };
        }
    }
    if(currentButton.button == listOfButtons[0]) {
        $(`#prevBtn`).attr(`disabled`, `disabled`);
        $(`#nextBtn`).attr(`disabled`, false);
    } else if(currentButton.button == listOfButtons[listOfButtons.length-1]) {
        $(`#nextBtn`).attr(`disabled`, `disabled`);
        $(`#prevBtn`).attr(`disabled`, false);
    } else {
        $(`#nextBtn`).attr(`disabled`, false);
        $(`#prevBtn`).attr(`disabled`, false);
    }
    $(`#button` + buttonId).attr('class', 'list-group-item list-group-item-action active');
    let question = [];
    let currentQuestion = [];
    for(let i = 0; i < test.questions.length; i++) {
        if(test.questions[i].serialNumber == parseInt(serialNumber)) {
            question = test.questions[i];
        }
    }
    switch (question.type) {
        case 'ONE_RIGHT_VALUE' :
            console.log('ONE_RIGHT_VALUE');
            break;
        case 'MANY_RIGHT_VALUE' :
            console.log('MANY_RIGHT_VALUE');
            break;
        case 'MANUAL_INPUT' :
            console.log('MANUAL_INPUT');
            break;
        default :
            console.log('Type not found');
            break;
    }
    $('#question').html(question.question);
}

function nextPrev(nextOrPrev) {
    for (let i = 0; i < listOfButtons.length; i++) {
        if(nextOrPrev == "next") {
            if(currentButton.button == listOfButtons[i]) {
                if(i < listOfButtons.length) {
                    listOfButtons[i + 1].onclick();
                    currentButton.button = listOfButtons[i + 1];
                    currentButton.serialNumber = parseInt(listOfButtons[i + 1].name);
                    currentButton.buttonId = parseInt(listOfButtons[i + 1].id
                        .toString()[listOfButtons[i + 1].id.toString().length-1]);
                    break;
                }
            }
        }
        else {
            if(currentButton.button == listOfButtons[i]) {
                if (i >= 1) {
                    listOfButtons[i - 1].onclick();
                    currentButton.button = listOfButtons[i - 1];
                    currentButton.serialNumber = parseInt(listOfButtons[i - 1].name);
                    currentButton.buttonId = parseInt(listOfButtons[i - 1].id
                        .toString()[listOfButtons[i - 1].id.toString().length-1]);
                    break;
                }
            }
        }
    }
}

function renderAnswer() {

}