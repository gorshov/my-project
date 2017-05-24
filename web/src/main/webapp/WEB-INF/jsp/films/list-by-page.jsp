<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <spring:url value="/films" var="allFilms"/>
    <table class="table table-striped">
        <div class="row">
            <label class="col-sm-2">
                <button class="btn btn-primary" onclick="location.href='${allFilms}'">Show All</button>
            </label>
        </div>
        <thead>
        <th><spring:message code="field.films.title"/></th>
        <th><spring:message code="field.films.genre"/></th>
        <th><spring:message code="field.films.releaseDate"/></th>
        <th><spring:message code="field.films.country"/></th>
        </thead>
        <tbody>
        <c:forEach items="${films}" var="film">
            <tr>
                <td><c:out value="${film.title}"/></td>
                <td><c:out value="${film.genre}"/></td>
                <td><c:out value="${film.releaseDate}"/></td>
                <td><c:out value="${film.country}"/></td>
                <td>
                    <spring:url value="/films/${film.id}" var="filmUrl"/>
                    <spring:url value="/films/${film.id}/delete" var="deleteUrl"/>
                    <spring:url value="/films/${film.id}/update" var="updateUrl"/>

                    <button class="btn btn-info" onclick="location.href='${filmUrl}'">Query</button>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
                        <button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <spring:url value="/stars/add/film${film.id}" var="addStar"/>
    <button class="btn btn-primary" onclick="location.href='${urlAddUser}'">Add Film</button>
    </sec:authorize>
    ${count}${" topics   pages: "}
    <a href="/film/pagination/1/${recordPerPage}">1</a>${" "}
    <c:choose>
    <c:when test="${recordPerPage ge count}">
    <a href="/film/pagination/1/${recordPerPage}">${">"}</a>${" "}
    </c:when>
    <c:otherwise>
    <a href="/film/pagination/${page + 1}/${recordPerPage}">${">"}</a>${" ... "}
    </c:otherwise>
    </c:choose>
    <c:if test="${page>2 && page < numberOfPages}">
    <a href="/film/pagination/${page + 1}/${recordPerPage}">${page}</a>${" ... "}
    </c:if>
    <a href="/film/pagination/${numberOfPages}/${recordPerPage}">${numberOfPages}</a>${"      record per pages: "}
    <a href="/film/pagination/${1}/${10}">10</a>${" "}
    <a href="/film/pagination/${1}/${25}">25</a>${" "}
    <a href="/film/pagination/${1}/${50}">50</a>${" "}

    <jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
