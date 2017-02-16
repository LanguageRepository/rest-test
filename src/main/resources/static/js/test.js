let username;

let testList = [];

function renderTestTable() {
    $('#testsTable').dataTable({
        responsive: true,
        "aoColumnDefs": [
            { "sWidth": "93px", "aTargets": [ 4 ] }
        ],
        "aaData": testList.map(item => {
            var result = [];
            result.push(item.id);
            result.push(item.name);
            result.push(item.ownerName);
            result.push(item.description);
            result.push(createButtons(item));
            return result;
        })
    });
}

function getAllParagraph() {
    $.ajax({
        async   : true,
        type    : "GET",
        url     : "/prg/tree",
        dataType: "json",
        success : function (data) {
            paragraphToTree(JSON.stringify(data));
        },
        error   : function () {
            alert("Не удалось получить данные");
        }
    });
}

function getAllTestsForTable() {
    $.ajax({
        url: "/test/table",
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        mimeType: "application/json",
        success: function (data) {
            testList = data;
            renderTestTable();
        }
    });
}

$(document).ready(function() {
    getAllParagraph();
    getAllTestsForTable();
});

/*$(document).ready(function hello() {
    $.ajax({
        url: "/user",
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        mimeType: "application/json",
        success: function (data) {
            username = data.username;
            $("#hello").text(`Здравствуйте, ${data.username}`);
        }
    });
});
*/
function renderQuestion(question) {
    alert(question.question);
}

function readQuestion(id) {
    let question = new Question();
    question.render = renderQuestion;
    question.load(id);
}

class Question {

    addAnswer() {
        this.question.answers.put({answer: '', type: 'ANSWER_TYPE_BOOL', rightValue: 'false'});
        if (this.render) {
            this.render(this.question);
        }
    }

    load(id, render) {
        $.ajax({
            url: `/question/${id}`,
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            mimeType: "application/json",
            success: (data) => {
                this.question = data;
                if (render) {
                    render(this.question);
                } else {
                    this.render(this.question);
                }
            }
        });
   }

   save(render) {
       if (this.question) {
           $.ajax({
               url: this.question.id ? `/question/${this.question.id}` : '/question',
               type: this.question.id ? 'PUT' : 'POST',
               dataType: "json",
               contentType: "application/json",
               mimeType: "application/json",
               success: (data) => {
                   this.question = data;
                   if (render) {
                       render(this.question);
                   } else {
                       this.render(this.question);
                   }
               }
           });
       }
   }
}

function addAnswer() {
    let rootNode = document.getElementById('answer');
    let row = document.createElement('div');
        row.setAttribute('class', 'row');
    let column = document.createElement('div');
        column.setAttribute('class', 'col-md-6');
    let formGroup = document.createElement('div');
        formGroup.setAttribute('class', 'form-group');
    let label = document.createElement('label');
        label.setAttribute('id', 'answer-label');
        label.textContent = 'Ответ';
    let textarea = document.createElement('textarea');
        textarea.setAttribute('rows', '2');
        textarea.setAttribute('class', 'form-control');
        textarea.setAttribute('id', 'answer-area');
        textarea.setAttribute('placeholder', 'Текст ответа');
    let answerParent = rootNode.parentElement;
    let needNode = answerParent.children[1];
    answerParent.insertBefore(row, needNode.nextSibling);
    row.appendChild(column);
    column.appendChild(formGroup);
    formGroup.appendChild(label);
    formGroup.appendChild(textarea);
}

function createButtons(item) {
    return `<div class="btn-group pull-right" role="group">` +
        `<a class="btn btn-primary btn-fill" href="/cpanel/${item.id}" role="button">Обновить</a>` +
        `<button class="btn btn-primary"` +
        `type="submit" onclick="saveDelete(${item.id})">Удалить</button></div>`;
}

$(document).ready();

function paragraphToTree(json) {
    console.log(JSON.parse(json));
    $(function() {
        $('#tree').jstree({
            'core' : {
                'data' : JSON.parse(json),
                'themes': {
                    'name': 'proton',
                    'responsive': true
                }
            }
        });
        $('#prg-tree').jstree({
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
        localStorage.setItem("current_prg_id", data.instance.get_selected(true)[0].id);
    });
    /*$('#tree').on("loaded.jstree", function (e, data) {
        //data.instance.select_node(localStorage.getItem("dep_id"));
    })*/
}

function createTest(e) {
    e.preventDefault();
    e.stopPropagation();
    let testName = $("#test-name").val(),
        selectPrg = localStorage.getItem("current_prg_id"),
        testDesc = $("#test-desc").val(),
        ownerName = "admin";
    let result = {
        "name"        : testName,
        "ownerName"   : ownerName,
        "paragraphId" : selectPrg,
        "description" : testDesc
    };
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/test",
        data: JSON.stringify(result),
        dataType: 'json',
        success: function (data) {
            alert("Тест" + testName + " успешно создан");
            testList.push(data);
            //renderTestTable();
            console.log(testList);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(xhr);
            alert(thrownError);
        }
    });
}

function test() {
    let testName = $("#test-name").val(),
        selectPrg = localStorage.getItem("current_prg_id"),
        testDesc = $("#test-desc").val(),
        ownerName = "admin";//localStorage.getItem("usernameLoginForm");
    let result = {
        "name"        : testName,
        "ownerName"   : ownerName,
        "paragraphId" : selectPrg,
        "description" : testDesc
    };
}