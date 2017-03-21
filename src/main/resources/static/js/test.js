let username;

let testList = [];

let tableRowId = 1;

let typeOfAnswer = "manyRight";

let rootParagraph;

let paragraphList;

const questionValidation = {
    rules : {
        form_checkbox : {
            required : true,
            minlength: 6
        },
        form_question_name : {
            required : true,
            minlength: 6
        }
    },
    messages : {
        form_checkbox : {
            required : "Поле ввода ответа должно быть заполнено",
            minlength: "Введите не менее 6 символов в поле"
        },
        form_question_name : {
            required : "Поле ввода вопроса должно быть заполнено",
            minlength: "Введите не менее 6 символов в поле"
        }
    }
};

$(document).ready(function() {
    if(location.href.includes("teststable")) {
        getAllParagraph();
        getAllTestsForTable();
        $("#questionCreateForm").validate(questionValidation);
        hello();
        $("#allParagraph").on("loaded.jstree", function (e, data) {
            data.instance.open_node(1);
        });
    }
});

function hello() {
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
    })
}

function renderTestTable() {
    $('#testsTable').dataTable({
        responsive: true,
        columnDefs: [
            {
                targets: [ 0, 1, 2 ],
                className: 'mdl-data-table__cell--non-numeric'
            }],
        "aoColumnDefs": [
            { "sWidth": "100px", "aTargets": [ 4 ] }
        ],
        "aaData": testList.map(item => {
            let result = [];
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
            rootParagraph = JSON.stringify(data);
            paragraphList = JSON.stringify(JSON.parse(rootParagraph).children);
            paragraphToTree();
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
            $("#testsTable").DataTable().destroy();
            renderTestTable();
        }
    });
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
    return `<div class="btn-group">` +
           `<a class="btn btn-info btn-fill btn-sm" href="/testservice/questiontable/${item.id}" onclick="saveTestId(${item.id})"><span class="pe-7s-angle-left"></span></a>` +
           `<button class="btn btn-danger btn-fill btn-sm" onclick="deleteTest(${item.id})"><span class="pe-7s-close"></span></button>` +
           `</div>`;
}

function paragraphToTree() {
    let allParagraph = $('#allParagraph');
    $(function() {
        $('#tree').jstree({
            'core' : {
                'data' : JSON.parse(rootParagraph),
                'themes': {
                    'name': 'proton',
                    'responsive': true
                }
            }
        });
        $('#prg-tree').jstree({
            'core' : {
                'data' : JSON.parse(rootParagraph),
                'themes': {
                    'name': 'proton',
                    'responsive': true
                }
            }
        });
        $('#allParagraph').jstree({
            'core' : {
                'data' : JSON.parse(rootParagraph),
                'themes': {
                    'name': 'proton',
                    'responsive': true
                }
            }
        })
    });
    allParagraph.on("select_node.jstree", function (e, data) {
        localStorage.setItem("current_prg_id", data.instance.get_selected(true)[0].id);
        console.log(data.instance.get_selected(true)[0]);
    });
    allParagraph.on("select_node.jstree", function (e, data) {
        localStorage.setItem("selected_prg_id", data.instance.get_selected(true)[0].id);
    });
    allParagraph.on("select_node.jstree", function (e, data) {
        if(data.instance.get_selected(true)[0].text == "Дисциплины") {
            document.getElementById(`inputParagraphDelete`).setAttribute("disabled", "disabled");
        } else {
            $(`#inputParagraphDelete`).removeAttr("disabled");
        }
        $.ajax({
            url : "/test/table/" + data.instance.get_selected(true)[0].id,
            type : "GET",
            dataType: "json",
            contentType: "application/json",
            mimeType: "application/json",
            success : function (data) {
                testList = data;
                $("#testsTable").DataTable().destroy();
                renderTestTable();
            }
        });
        document.getElementById("paragraphInput1").value = data.instance.get_selected(true)[0].text;
        document.getElementById("paragraphInput2").value = data.instance.get_selected(true)[0].text;
    });
    allParagraph.jstree(true).settings.core.data = JSON.parse(rootParagraph);
    allParagraph.jstree(true).refresh();
    $("#allParagraph").on("refresh.jstree", function (e, data) {
        data.instance.open_node(1);
    });
}

function createTest() {
    let testName = $("#test-name").val(),
        selectPrg = localStorage.getItem("current_prg_id"),
        testDesc = $("#test-desc").val(),
        ownerName = username;
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
            $("#testsTable").DataTable().row.add([data.id, data.name, data.ownerName, data.description, createButtons(data)]).draw();
            alert("Тест" + testName + " успешно создан");
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(xhr);
            alert(thrownError);
        }
    });
}

function createParagraph() {
    let paragraphName = document.getElementById("paragraph-name").value;
    let parentParagraph = localStorage.getItem("selected_prg_id");
    let result = {
        "name"     : paragraphName,
        "parentId" : parentParagraph
    };
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/prg",
        data: JSON.stringify(result),
        dataType: 'json',
        success : function () {
            $.ajax({
                async   : true,
                type    : "GET",
                url     : "/prg/tree",
                dataType: "json",
                success : function(data) {
                    rootParagraph = JSON.stringify(data);
                    paragraphToTree();
                }
            });
        },
        error : function () {
            $.ajax({
                async: true,
                type: "GET",
                url: "/prg/tree",
                dataType: "json",
                success: function (data) {
                    rootParagraph = JSON.stringify(data);
                    paragraphToTree();
                }
            });
        }
    });
}

function deleteTest(id) {
    if(confirm("Вы подтверждаете удаление?")) {
        $.ajax({
            type: `DELETE`,
            url: `/test/` + id,
            success: function () {
                let array = testList.filter(function (elem) {
                    return elem.id != id;
                });
                testList = array;
                $("#testsTable").DataTable().destroy();
                renderTestTable();
            }
        });
    }
}

function deleteParagraph() {
    let paragraphId = localStorage.getItem("current_prg_id");
    if(!(paragraphId == 1)) {
        if (confirm("Вы подтверждаете удаление?")) {
            $.ajax({
                type: `DELETE`,
                url: `/prg/` + paragraphId,
                success: function () {
                    getAllParagraph();
                }
            });
        }
    }
}

function saveTestId(id) {
    localStorage.setItem(`current_test_id`, id);
}

