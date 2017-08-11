<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="../fragments/header.jsp"/>

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h1>Film Detail</h1>
    <div class="row">

        <label class="col-sm-2">
            <c:if test="${film.image.imagePath != null}">
                <img src="${pageContext.request.contextPath}/${film.image.imagePath}" class="img-rounded"/>
            </c:if>
            <c:if test="${film.image.imagePath == null}">
                <img src="${pageContext.request.contextPath}/resources/core/image/Image-Not-Available.png" class="img-rounded"/>
            </c:if>
        </label>
        <div class="col-xs-1"></div>
        <div class="col-sm-6">
            <table class="table table-striped">
                <tbody>
                <td>${film.filmDescription}</td>
                </tbody>
            </table>
        </div>


    </div>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <spring:url value="/film/add/image/${film.id}" var="addImage"/>
        <div class="row">
            <label class="col-sm-2"><form:form class="form-horizontal" method="post" enctype="multipart/form-data"
                                               action="${addImage}">
                <input type="file" name="file" value=""/>
                <button type="submit" class="btn btn-xs btn-success">save</button>
            </form:form></label>
        </div>
    </sec:authorize>
    <div class="row">
        <label class="col-sm-2">ID</label>
        <div class="col-sm-10">${film.id}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="field.films.title"/></label>
        <div class="col-sm-10">${film.title}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="field.films.genre"/></label>
        <div class="col-sm-10">${film.genre}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="field.films.country"/></label>
        <div class="col-sm-10">${film.country}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="field.films.releaseDate"/></label>
        <div class="col-sm-10">${film.releaseDate}</div>
    </div>

    <spring:url value="/review/add/${film.id}" var="addReview"/>
    <sec:authorize access="isAuthenticated()">
        <div class="row">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Field for your review</label>
                <div class="col-sm-10">
                    <form:form class="form-horizontal" method="post" action="${addReview}" modelAttribute="review">
                        <div class="col-sm-5">
                <textarea name="text" id="text" class="form-control"
                          style="margin: 0px; width: 328px; height: 106px;"></textarea> <br/>
                        </div>
                        <div class="col-sm-5">
                            <button id="addReviewRorFilm" onclick="review(addReview)"
                                    value="<spring:message code="review.for.films"/>"
                                    class="btn btn-xs btn-success">Add review
                            </button>
                        </div>
                        <br/>
                    </form:form>
                    <form:errors path="text" class="control-label"/>
                </div>
            </div>
        </div>
    </sec:authorize>
    <br/>
    <br/>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <spring:url value="/stars/add/film${film.id}" var="addStar"/>
        <button class="btn btn-info" onclick="location.href='${addStar}'">Add Star</button>
    </sec:authorize>
    <br/>
    <br/>
    <button class="btn btn-info" onclick="sTable(star)"><spring:message code="button.show.star"/></button>
    <button class="btn btn-info" onclick="sTable(review)"><spring:message code="button.show.review"/></button>
    <div class="row">
        <table id="starTable" hidden class="table table-striped">
            <thead>
            <th><spring:message code="field.first.name"/></th>
            <th><spring:message code="field.middle.name"/></th>
            <th><spring:message code="field.last.name"/></th>
            </thead>
            <tbody>
            <c:forEach items="${film.starSet}" var="star">
                <tr>
                    <td><c:out value="${star.firstName}"/></td>
                    <td><c:out value="${star.middleName}"/></td>
                    <td><c:out value="${star.lastName}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row">
        <table id="reviewTable" hidden class="table table-striped">
            <thead>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th>Review Id</th>
            </sec:authorize>
            <th>User Name</th>
            <th>Review</th>
            </thead>
            <tbody>
            <c:forEach items="${film.reviewList}" var="review">
                <tr><sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td><c:out value="${review.id}"/></td>
                </sec:authorize>

                    <td><c:out value="${review.user.lastName}"/></td>
                    <td><c:out value="${review.text}"/></td>
                    <td>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <spring:url value="/review/${review.id}/${review.film.id}/delete" var="deleteUrl"/>
                            <button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">
                                <spring:message code="button.delete"/></button>
                        </sec:authorize>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">

    var addReview = '/review/add/${film.title}/${film.id}';
    var deleteUrl = '/review/${review.id}/${review.film.id}';

    var star = $("#starTable");
    var review = $("#reviewTable");
    var flag = true;
    function sTable(idTable) {
        if (flag) {
            idTable.show('fast');
            flag = false;
        } else {
            idTable.hide('fast');
            flag = true;
        }
    }

    function post(path, params, method) {
        method = method || "post";

        var form = document.createElement("form");
        form.setAttribute("method", method);
        form.setAttribute("action", path);

        for (var key in params) {
            if (params.hasOwnProperty(key)) {
                var hiddenField = document.createElement("input");
                hiddenField.setAttribute("type", "hidden");
                hiddenField.setAttribute("name", key);
                hiddenField.setAttribute("value", params[key]);

                form.appendChild(hiddenField);
            }
        }

        document.body.appendChild(form);
        form.submit();
    }

    /*  $(document).ready(function () {
     $("#image-push").fancybox({
     openEffect: 'elastic',
     closeEffect: 'elastic',
     helpers: {
     title: {
     type: 'inside'
     }
     }
     });
     $.fancybox.close(true)
     });*/

    /*  $(document).ready(function() {
     $(".fancybox").fancybox({
     openEffect	: 'none',
     closeEffect	: 'none'
     });
     });*/

</script>
<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
