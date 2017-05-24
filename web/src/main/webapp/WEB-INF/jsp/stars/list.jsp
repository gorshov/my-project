<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<jsp:include page="../fragments/header.jsp"/>
<spring:url value="/stars/add" var="urlAddStar"/>
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

    <h1><spring:message code="navigation.stars"/></h1>

    <table class="table table-striped">
        <thead>
        <th><spring:message code="field.first.name"/></th>
        <th><spring:message code="field.middle.name"/></th>
        <th><spring:message code="field.last.name"/></th>
        <th><spring:message code="field.date.birth"/></th>
        </thead>
        <tbody>
        <c:forEach items="${starSet}" var="star">
            <tr>
                <td><c:out value="${star.firstName}"/></td>
                <td><c:out value="${star.middleName}"/></td>
                <td><c:out value="${star.lastName}"/></td>
                <td><c:out value="${star.dateOfBirth}"/></td>
                <td>
                    <spring:url value="/stars/${star.id}" var="starUrl"/>
                    <spring:url value="/stars/${star.lastName}/delete" var="deleteUrl"/>
                    <spring:url value="/stars/${star.id}/update" var="updateUrl"/>

                    <button class="btn btn-info" onclick="location.href='${starUrl}'"><spring:message
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
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <button class="btn btn-primary" onclick="location.href='${urlAddStar}'"><spring:message
                code="buttons.add"/></button>
    </sec:authorize>
    ${count}${" topics   pages: "}
    <a href="/star/1/${recordPerPage}">1</a>${" "}
    <c:choose>
        <c:when test="${recordPerPage ge count}">
            <a href="/star/1/${recordPerPage}">${">"}</a>${" "}
        </c:when>
        <c:otherwise>
            <a href="/star/${page + 1}/${recordPerPage}">${">"}</a>${" ... "}
        </c:otherwise>
    </c:choose>
    <c:if test="${page>2 && page < numberOfPages}">
        <a href="/star/${page + 1}/${recordPerPage}">${page}</a>${" ... "}
    </c:if>
    <a href="/star/${numberOfPages}/${recordPerPage}">${numberOfPages}</a>${"      record per pages: "}
    <a href="/star/${1}/${10}">10</a>${" "}
    <a href="/star/${1}/${25}">25</a>${" "}
    <a href="/star/${1}/${50}">50</a>${" "}
</div>
<script type="text/javascript">

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
</script>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
