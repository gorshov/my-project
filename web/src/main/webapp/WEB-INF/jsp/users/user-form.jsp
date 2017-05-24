<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp"/>

<spring:url value="/users" var="userActionUrl"/>

<div class="container">

    <c:choose>
        <c:when test="${userForm['id']==null}">
            <h1>Add User</h1>
        </c:when>
        <c:otherwise>
            <h1>Update User</h1>
        </c:otherwise>
    </c:choose>
    <br/>

    <form:form class="form-horizontal" method="post" modelAttribute="userForm" action="${userActionUrl}">

        <form:hidden path="id"/>

        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label"><spring:message code="field.first.name"/></label>
                <div class="col-sm-10">
                    <form:input path="firstName" type="text" class="form-control " id="firstName"
                                placeholder="First Name"/>
                    <form:errors path="firstName" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="middleName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label"><spring:message code="field.middle.name"/></label>
                <div class="col-sm-10">
                    <form:input path="middleName" type="text" class="form-control " id="middleName"
                                placeholder="Middle Name"/>
                    <form:errors path="middleName" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label"><spring:message code="field.last.name"/></label>
                <div class="col-sm-10">
                    <form:input path="lastName" type="text" class="form-control " id="lastName"
                                placeholder="Last Name"/>
                    <form:errors path="lastName" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label"><spring:message code="field.users.email"/></label>
                <div class="col-sm-10">
                    <form:input path="email" class="form-control" id="email" placeholder="Email"/>
                    <form:errors path="email" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="passwords">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label"><spring:message code="field.users.password"/></label>
                <div class="col-sm-10">
                    <form:password path="passwords" class="form-control" id="passwords" placeholder="passwords"/>
                    <form:errors path="passwords" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="passwords">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label"><spring:message code="field.users.confirm.password"/></label>
                <div class="col-sm-10">
                    <form:password path="confirmPassword" class="form-control" id="confirmPassword"
                                   placeholder="confirmPassword"/>
                    <form:errors path="confirmPassword" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="role">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label"><spring:message code="field.users.role"/></label>
                <div class="col-sm-5">
                    <form:select path="role" class="form-control">
                        <form:options items="${roleList}"/>
                    </form:select>
                    <form:errors path="role" class="control-label"/>
                </div>
                <div class="col-sm-5"></div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${userForm['id']==null}">
                        <button type="submit" class="btn-lg btn-primary pull-right">Add</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn-lg btn-primary pull-right">Update</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

    </form:form>
</div>

<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>