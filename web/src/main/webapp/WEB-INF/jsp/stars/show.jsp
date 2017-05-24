<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h1>Star Detail</h1>
    <br/>
    <div class="row">
        <label class="col-sm-2">ID</label>
        <div class="col-sm-10">${star.id}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="field.first.name"/></label>
        <div class="col-sm-10">${star.firstName}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="field.middle.name"/></label>
        <div class="col-sm-10">${star.middleName}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="field.last.name"/></label>
        <div class="col-sm-10">${star.lastName}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="field.date.birth"/></label>
        <div class="col-sm-10">${star.dateOfBirth}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="navigation.films"/></label>
        <table class="table table-striped">
            <thead>
            <th><spring:message code="field.films.title"/></th>
            <th><spring:message code="field.films.genre"/></th>
            <th><spring:message code="field.films.country"/></th>
            <th><spring:message code="field.films.releaseDate"/></th>
            </thead>
            <tbody>
            <c:forEach items="${star.filmSet}" var="film">
                <tr>
                    <td><c:out value="${film.title}"/></td>
                    <td><c:out value="${film.genre}"/></td>
                    <td><c:out value="${film.country}"/></td>
                    <td><c:out value="${film.releaseDate}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>


</body>
</html>