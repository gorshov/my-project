<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Video Library</title>
    <spring:url value="/resources/core/js/jquery-1.4.2.min.js"
                var="jqJs"/>
    <spring:url value="/resources/core/css/hello.css" var="coreCss"/>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <script type="text/javascript" src="${jqJs}"></script>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${coreCss}" rel="stylesheet"/>
</head>

<spring:url value="/films" var="urlHome"/>
<spring:url value="/star/${1}/${10}" var="urlStars"/>
<spring:url value="/users" var="urlUsers"/>
<nav class="navbar navbar-inverse ">
    <div class="container">
        <div class="navbar-header">

            <a class="navbar-brand" href="${urlHome}"><spring:message code="navigation.films"/></a>
            <a class="navbar-brand" href="${urlStars}"><spring:message code="navigation.stars"/></a>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a class="navbar-brand" href="${urlUsers}"><spring:message code="navigation.users"/></a>
            </sec:authorize>
        </div>
        <div id="navbar">
            <div class="nav navbar-nav navbar-right">
                <sec:authorize access="isAnonymous()">
                    <form method="post" class="form-inline" action="/login">
                        <div class="form-group">
                            <br/>
                            <input class="form-control" id="login" name="login" placeholder="Email" type="text"/>
                        </div>
                        <div class="form-group">
                            <br/>
                            <input class="form-control" id="password" name="password" placeholder="Password"
                                   type="password"/>
                        </div>
                        <input id="remember_me" name="_spring_security_remember_me" type="checkbox"/>
                        <label for="remember_me" class="inline" style="color: white">Remember me</label>

                        <div class="form-group">
                            <br/>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                        <br/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <a href="/registration" style="color: white"><spring:message code="user.registration"/></a>
                    </form>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <form class="form-inline">
                        <div class="form-group">
                            <c:if test="${pageContext.request.userPrincipal.name != null}">
                                <h5>
                                    <br/>
                                    <a style="color: white">Welcome : ${pageContext.request.userPrincipal.name}</a>
                                </h5>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <br/>
                            <button class="btn btn-warning">
                                <a href="<%=request.getContextPath()%>?locale=ru"><spring:message
                                        code="header.locale.ru"/></a></button>
                            <br/>
                        </div>
                        <div class="form-group">
                            <br/>
                            <button class="btn btn-warning">
                                <a href="<%=request.getContextPath()%>?locale=en"><spring:message
                                        code="header.locale.en"/></a></button>
                            <br/>
                        </div>
                        <div class="form-group">
                            <br/>
                            <spring:url value="/logout" var="logoutHref"/>
                            <button class="btn btn-success"><a href="${logoutHref}"><spring:message
                                    code="header.signOut"/></a></button>
                            <br/>
                        </div>
                    </form>
                </sec:authorize>

            </div>
        </div>
    </div>
</nav>
