<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

    <h1>Users Datails</h1>
    <br/>

    <div class="row">
        <label class="col-sm-2">ID</label>
        <div class="col-sm-10">${user.id}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="field.first.name"/></label>
        <div class="col-sm-10">${user.firstName}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="field.middle.name"/></label>
        <div class="col-sm-10">${user.middleName}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="field.last.name"/></label>
        <div class="col-sm-10">${user.lastName}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="field.users.email"/></label>
        <div class="col-sm-10">${user.email}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="field.users.password"/></label>
        <div class="col-sm-10">${user.passwords}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="field.users.role"/></label>
        <div class="col-sm-10">${user.role}</div>
    </div>

    <div class="row">
        <table class="table table-striped">
            <thead>
            <th><spring:message code="navigation.films"/></th>
            <th><spring:message code="field.review.text"/></th>
            </thead>
            <tbody>
            <c:forEach items="${user.reviewList}" var="review">
                <tr>
                    <td><c:out value="${review.film.title}"/></td>
                    <td><c:out value="${review.text}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>