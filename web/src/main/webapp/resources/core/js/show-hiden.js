/**
 * Created by Александр Горшов on 20.05.2017.
 */
$(document).ready(function(){
    $('#showStar').click(function () {
        sTable(star);
    });
    $('#showReview').click(function () {
        sTable(review);
    });
});


var star = $("#starTable");
var review = $("#reviewTable");
var flag = true;
function sTable(a) {
    if (flag) {
        a.show('fast');
        flag = false;
    } else {
        a.hide(1000);
        flag = true;
    }
}