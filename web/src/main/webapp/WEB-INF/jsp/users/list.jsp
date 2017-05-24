<%@ page session="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp"/>
<spring:url value="/users/add" var="urlAddUser"/>
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

    <h1><spring:message code="page.users.title"/></h1>
    <spring:url value="/user/search/" var="searchByName"/>
    <div class="row">
        <div class="form-group ">
            <label class="col-sm-2 control-label">Search by Last Name</label>
            <div class="col-sm-10">
                <form:form class="form-horizontal" method="post" action="${searchByName}">
                    <div class="col-sm-5">
                        <input type="text" id="name" name="name" class="form-control" placeholder="User Last Name">
                    </div>
                    <div class="col-sm-5">
                        <button type="submit" class="btn btn-danger">Search</button>
                    </div>
                    <br/>
                </form:form>
            </div>
        </div>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>#ID</th>
            <th><spring:message code="field.first.name"/></th>
            <th><spring:message code="field.middle.name"/></th>
            <th><spring:message code="field.last.name"/></th>
            <th><spring:message code="field.users.password"/></th>
            <th><spring:message code="field.users.email"/></th>
            <th><spring:message code="field.users.role"/></th>
            <th></th>
        </tr>
        </thead>

        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                        ${user.id}
                </td>
                <td>${user.firstName}</td>
                <td>${user.middleName}</td>
                <td>${user.lastName}</td>
                <td>${user.passwords}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td>
                    <spring:url value="/users/${user.id}" var="userUrl"/>
                    <spring:url value="/users/${user.id}/delete" var="deleteUrl"/>
                    <spring:url value="/users/${user.id}/update" var="updateUrl"/>

                    <button class="btn btn-info" onclick="location.href='${userUrl}'"><spring:message
                            code="button.query"/></button>
                    <button class="btn btn-primary" onclick="location.href='${updateUrl}'"><spring:message
                            code="button.update"/></button>
                    <button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')"><spring:message
                            code="button.delete"/></button>
                </td>
            </tr>
        </c:forEach>
    </table>
    <button class="btn btn-primary" onclick="location.href='${urlAddUser}'"><spring:message
            code="buttons.add"/></button>
    <br/>
    <br/>

</div>
<div class="container">
    <form:form class="form-horizontal" method="post" enctype="multipart/form-data" modelAttribute="uploadedFile"
               action="uploadFile">
        <table class="table table-striped">
            <tr>
                <td>Upload File:</td>
                <td><input type="file" name="file"/></td>
                <td style="color: red; font-style: italic;">
                    <form:errors path="file" class="control-label"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Upload"/></td>
                <td></td>
            </tr>
        </table>
    </form:form>
</div>

<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>