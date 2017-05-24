/**
 * Created by Александр Горшов on on 24.05.2017.
 */
function getBYId() {
    var userId = $("#userID").val();
    var url = "/ajax/find/user/" + userId;

    $.ajax({
        data: userId,
        url: url,
        dataType: "json",
        type: 'get',
        success: function (jsondata) {
            $('.results').html('firstName = ' + jsondata.firstName + ', email = ' + jsondata.email);
        }
    })
}