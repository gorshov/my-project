<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="container">
    <hr>
    <footer>
        <p>&copy; by Gorshov </p>
    </footer>
</div>

<script type="text/javascript">

    $("tr").not(':first').hover(
        function () {
            $(this).css("background", "grey");
        },
        function () {
            $(this).css("background", "");
        }
    );

</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<spring:url value="resources/core/js/hello.js" var="coreJs"/>
<spring:url value="resources/core/js/bootstrap.min.js"
            var="bootstrapJs"/>
<spring:url value="resources/core/js/review.js" var="revJS"/>
<script src="${revJS}"></script>
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>


