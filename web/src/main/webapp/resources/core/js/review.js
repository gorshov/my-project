
function review(url) {
    var reviewText = $("#reviewText").val();

    $.post(url,
        {
            id: null,
            reviewText: reviewText,
            mark: null,
            film: null,
            user: null
        },
        function (data) {
        $(".review").val("Ваша рецензия добавлена!");
    });
}
