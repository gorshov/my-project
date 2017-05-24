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
    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <c:choose>
        <c:when test="${filmForm['id']==null}">
            <h1>Add Film</h1>
        </c:when>
        <c:otherwise>
            <h1>Update Film</h1>
        </c:otherwise>
    </c:choose>
    <br/>

    <spring:url value="/films" var="filmActionUrl"/>

    <form:form class="form-horizontal" method="post" action="${filmActionUrl}" modelAttribute="filmForm">

        <form:hidden path="id"/>

        <spring:bind path="title">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Title</label>
                <div class="col-sm-10">
                    <form:input path="title" type="text" class="form-control " id="title"
                                placeholder="Title"/>
                    <form:errors path="title" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="releaseDate">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Release Date</label>
                <div class="col-sm-10">
                    <form:input path="releaseDate" type="date" class="form-control " id="releaseDate"
                                placeholder="Release Date"/>
                    <form:errors path="releaseDate" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="country">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Country</label>
                <div class="col-sm-10">
                    <form:input path="country" type="text" class="form-control " id="country"
                                placeholder="Country"/>
                    <form:errors path="country" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="genre">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Genre</label>
                <div class="col-sm-5">
                    <form:select path="genre" class="form-control">
                        <form:options items="${genres}"/>
                    </form:select>
                    <form:errors path="genre" class="control-label"/>
                </div>
                <div class="col-sm-5"></div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${filmForm['id']==null}">
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
