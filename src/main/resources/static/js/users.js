/**
 * Created by kvasa on 08.01.2017.
 */

function getAllUsersForTable() {
    $.ajax({
        url: "/user/fortable",
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        mimeType: "application/json",
        success: function (data) {
            $('#usersTable').DataTable({
                responsive: true,
                "aoColumns" : [
                    { "sWidth": '50px' },
                    { "sWidth": '100px' },
                    { "sWidth": '120px' },
                    { "sWidth": '30px' },
                    { "sWidth": '30px' }
                ],
                "aaData": data.map(item => {
                    var result = [];
                    result.push("<p id='"+ item.id +"'>" + item.fullName + "</p>");
                    result.push(item.department);
                    var roles = "";
                    if (item.roles.length > 0) {
                        item.roles.forEach((role, index) => {
                            roles += index > 0 ? `, ${role.role}` : role.role
                        });
                    }
                    result.push(roles);
                    result.push(item.email);
                    result.push("<a class=\"glyphicon glyphicon-trash pull-right\" aria-hidden=\"true\" href=\""+ "/del/" + item.id +"\">" +
                        "</a>" + "<br/>" + "<a class=\"glyphicon glyphicon-pencil pull-right\" aria-hidden=\"true\" href=\""+ "/changeuser/" + item.id +"\">" +
                        "</a>");
                    return result;
                })
        });
        }
    });
}
