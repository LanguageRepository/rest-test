let testAccess;



$(document).ready(function () {
    if(location.pathname.includes(`manage`)) {
        findAllTestAccess();
    } else {
        useFlatPickr();
    }
});

function findAllTestAccess() {
    $.ajax({
        url: "/testaccess/all",
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        mimeType: "application/json",
        success : function (data) {
            testAccess = data;
            renderTaskTable();
        }
    });
}

function renderTaskTable() {
    $("#usersTable").DataTable({
        responsive : true,
        "aoColumnDefs": [
            { "sWidth": "80px", "aTargets": [ 6 ] }
        ],
        "aaData" : testAccess.map(item => {
            let result = [];
            result.push(item.testOwner);
            result.push(item.departments);
            result.push(item.testName);
            if(item.initialTerm != null && item.deadline != null) {
                result.push(item.initialTerm);
                result.push(item.deadline);
            } else {
                result.push("Без срока");
                result.push("Без срока");
            }
            result.push(item.type);
            result.push(renderButton(item));
            return result;
        })
    })
}

function renderButton(item) {
    return `<div class="btn-group">` +
        `<a class="btn btn-info btn-fill btn-sm" href="/taskservice/entity/${item.id}" onclick=""><span class="pe-7s-angle-left"></span></a>` +
        `<button class="btn btn-danger btn-fill btn-sm" onclick=""><span class="pe-7s-close"></span></button>` +
        `</div>`;
}



function renderUserTable() {

}
