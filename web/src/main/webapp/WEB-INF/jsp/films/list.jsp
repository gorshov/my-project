<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="../fragments/header.jsp"/>
<spring:url value="/films/add" var="urlAddUser"/>
<body>

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h2><spring:message code="page.films.title"/></h2><br/>
    <spring:url value="/film/pagination/${1}/${10}" var="filmPagination"/>
    <spring:url value="/films/search/" var="searchByTitle"/>
    <sec:authorize access="isAuthenticated()">
        <div class="row">
            <div class="form-group ">
                <label class="col-sm-2 control-label">Search by Title</label>
                <div class="col-sm-10">
                    <form:form class="form-horizontal" method="post" action="${searchByTitle}">
                        <div class="col-sm-5">
                            <input type="text" id="title" name="title" class="form-control" placeholder="Film title">
                        </div>
                        <div class="col-sm-5">
                            <button type="submit" class="btn btn-danger">Search</button>
                        </div>
                        <br/>
                    </form:form>
                </div>
            </div>
        </div>
    </sec:authorize>

    <table class="table table-striped">

        <div class="row">
            <label class="col-sm-2">
                <button class="btn btn-primary" onclick="location.href='${filmPagination}'">Show by page</button>
            </label>
        </div>
        <thead>
        <th><spring:message code="field.films.title"/></th>
        <th><spring:message code="field.films.genre"/></th>
        <th><spring:message code="field.films.releaseDate"/></th>
        <th><spring:message code="field.films.country"/></th>
        </thead>
        <tbody>
        <c:forEach items="${filmList}" var="film">
            <tr>
                <td><c:out value="${film.title}"/></td>
                <td><c:out value="${film.genre}"/></td>
                <td><c:out value="${film.releaseDate}"/></td>
                <td><c:out value="${film.country}"/></td>
                <td>
                    <spring:url value="/films/${film.id}" var="filmUrl"/>
                    <spring:url value="/films/${film.id}/delete" var="deleteUrl"/>
                    <spring:url value="/films/${film.id}/update" var="updateUrl"/>

                    <button class="btn btn-info" onclick="location.href='${filmUrl}'"><spring:message
                            code="button.query"/></button>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <button class="btn btn-primary" onclick="location.href='${updateUrl}'"><spring:message
                                code="button.update"/></button>
                        <button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')"><spring:message
                                code="button.delete"/></button>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
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
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <button class="btn btn-primary" onclick="location.href='${urlAddUser}'">Add Film</button>
    </sec:authorize>
</div>

<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
