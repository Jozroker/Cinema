<%--
  Created by IntelliJ IDEA.
  User: Jozroker
  Date: 08.06.2020
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Login page</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/login.css">
</head>
<jsp:include page="header.jsp"/>
<body>
<div id="header">

</div>
<%--    <div class="container">--%>
<%--        <form method="POST" action="${contextPath}/login" class="form-signin">--%>
<%--            <h2 class="form-heading">Log in</h2>--%>

<%--            <div class="form-group ${error != null ? 'has-error' : ''}">--%>
<%--                <span>${message}</span>--%>
<%--                <input name="username" type="text" class="form-control" placeholder="Username"--%>
<%--                       autofocus="true"/>--%>
<%--                <input name="password" type="password" class="form-control" placeholder="Password"/>--%>
<%--                <span>${error}</span>--%>
<%--                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>

<%--                <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>--%>
<%--                <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </div>--%>

<div class="login-wrap">


    <div class="login-html">
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab"><spring:message code="login.default"/></label>
        <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab"><spring:message code="registration"/></label>
        <div class="login-form ${error != null ? 'has-error' : ''}">
            <springForm:form method="POST" modelAttribute="loginUser">
                <div class="sign-in-htm">
                    <div class="group">
                            <%--                    <label class="label"></label>--%>
                            <%--                    <input name="username" id="user-login" type="text" class="input">--%>
                        <spring:message code="login.email_or_name" var="loginEmailOrUsername"/>
                        <springForm:input path="usernameOrEmail" class="input" placeholder="${loginEmailOrUsername}" cssErrorClass="form-control is-invalid"/>
                        <springForm:errors path="usernameOrEmail" cssClass="has-error"/>
                    </div>
                    <div class="group">
                            <%--                    <label for="pass-login" class="label">Password</label>--%>
                            <%--                    <input id="pass-login" type="password" class="input" data-type="password">--%>
                        <spring:message code="login.password" var="loginPassword"/>
                        <springForm:input path="pass" class="input" placeholder="${loginPassword}" cssErrorClass="form-control is-invalid"/>
                        <springForm:errors path="pass" cssClass="has-error"/>
                    </div>
                        <%--                <div class="group">--%>
                        <%--                    <input id="check" type="checkbox" class="check" checked>--%>
                        <%--                    <label for="check"><span class="icon"></span> Keep me Signed in</label>--%>
                        <%--                </div>--%>
                    <div class="group">
                        <spring:message code="login.default" var="login"/>
                        <input type="submit" class="button" value="${login}">
                    </div>
                    <div class="hr"></div>
                </div>
            </springForm:form>


            <springForm:form method="POST" modelAttribute="registerUser">
                <div class="sign-up-htm">
                    <div class="group">
                            <%--                    <label for="user-reg" class="label">Username</label>--%>
                            <%--                    <input id="user-reg" type="text" class="input">--%>
                        <spring:message code="registration.username" var="registerUsername"/>
                        <springForm:input path="username" class="input" placeholder="${registerUsername}" cssErrorClass="form-control is-invalid"/>
                        <springForm:errors path="username" cssClass="has-error"/>
                    </div>
                    <div class="group">
                            <%--                    <label for="pass-reg" class="label">Password</label>--%>
                            <%--                    <input id="pass-reg" type="password" class="input" data-type="password">--%>
                        <spring:message code="registration.first.name" var="registerFirstname"/>
                        <springForm:input path="firstName" class="input" placeholder="${registerFirstname}" cssErrorClass="form-control is-invalid"/>
                        <springForm:errors path="firstName" cssClass="has-error"/>
                    </div>
                    <div class="group">
                            <%--                    <label for="pass-reg" class="label">Password</label>--%>
                            <%--                    <input id="pass-reg" type="password" class="input" data-type="password">--%>
                        <spring:message code="registration.last.name" var="registerLastname"/>
                        <springForm:input path="lastName" class="input" placeholder="${registerLastname}" cssErrorClass="form-control is-invalid"/>
                        <springForm:errors path="lastName" cssClass="has-error"/>
                    </div>
                    <div class="group">
                            <%--                    <label for="pass-reg" class="label">Password</label>--%>
                            <%--                    <input id="pass-reg" type="password" class="input" data-type="password">--%>
                        <spring:message code="registration.email" var="registerEmail"/>
                        <springForm:input path="email" class="input" placeholder="${registerEmail}" cssErrorClass="form-control is-invalid"/>
                        <springForm:errors path="email" cssClass="has-error"/>
                    </div>
                    <div class="group">
                            <%--                    <label for="pass-reg" class="label">Password</label>--%>
                            <%--                    <input id="pass-reg" type="password" class="input" data-type="password">--%>
                        <spring:message code="registration.phone" var="registerPhone"/>
                        <springForm:input path="phone" class="input" placeholder="${registerPhone}" cssErrorClass="form-control is-invalid"/>
                        <springForm:errors path="phone" cssClass="has-error"/>
                    </div>
                    <div class="group">
                            <%--                    <label for="pass-reg" class="label">Password</label>--%>
                            <%--                    <input id="pass-reg" type="password" class="input" data-type="password">--%>
                        <spring:message code="registration.password" var="registerPassword"/>
                        <springForm:input path="password" class="input" placeholder="${registerPassword}" cssErrorClass="form-control is-invalid"/>
                        <springForm:errors path="password" cssClass="has-error"/>
                    </div>
                    <div class="group">
                            <%--                    <label for="pass-reg" class="label">Password</label>--%>
                            <%--                    <input id="pass-reg" type="password" class="input" data-type="password">--%>
                        <spring:message code="registration.confirm" var="registerConfirmPassword"/>
                        <springForm:input path="confirmPassword" class="input" placeholder="${registerConfirmPassword}" cssErrorClass="form-control is-invalid"/>
                        <springForm:errors path="confirmPassword" cssClass="has-error"/>
                    </div>
                    <div class="group">
                        <spring:message code="registration" var="register"/>
                        <input type="submit" class="button" value="${register}">
                    </div>
                    <div class="hr"></div>
                </div>
            </springForm:form>


        </div>
    </div>
</div>

</body>
</html>
