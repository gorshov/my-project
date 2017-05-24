<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<html>

<jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>

<body>

<div class="container">

    <c:choose>
        <c:when test="${star['id']==null}">
            <h1>Add Star</h1>
        </c:when>
        <c:otherwise>
            <h1>Update Star</h1>
        </c:otherwise>
    </c:choose>
    <br/>

    <spring:url value="/stars" var="starActionUrl"/>

    <form:form class="form-horizontal" method="post" action="${starActionUrl}" modelAttribute="starForm">
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

        <spring:bind path="dateOfBirth">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label"><spring:message code="field.date.birth"/></label>
                <div class="col-sm-10">
                    <form:input path="dateOfBirth" type="date" class="form-control " id="dateOfBirth"
                                placeholder="Date of birth"/>
                    <form:errors path="dateOfBirth" class="control-label"/>
                </div>
            </div>
        </spring:bind>


        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${starForm['id']==null}">
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

<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>

</body>
</html>
