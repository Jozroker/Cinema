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
    <style>
        .invalid {
            border: 1px solid red;
            color: red;
            padding: 15px 20px;
            border-radius: 25px;
            background: rgba(255, 255, 255, .1);
            width: 100%;
        }

        .invalid-text {
            color: red;
            margin-left: 5%;
        }
    </style>
    <link rel="stylesheet" href="${contextPath}/resources/css/authorization.css">
</head>
<jsp:include page="header.jsp"/>
<body>
<div id="header">

</div>

<div class="login-wrap">


    <div class="login-html">
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab"><spring:message code="login.default"/></label>
        <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab"><spring:message code="registration"/></label>
        <div class="login-form ${error != null ? 'has-error' : ''}">
            <springForm:form method="POST" modelAttribute="loginUser" action="/login">
                <div class="sign-in-htm">
                    <div class="group">
                        <spring:message code="login.email_or_name" var="loginEmailOrUsername"/>
                        <springForm:input path="usernameOrEmail" class="input" placeholder="${loginEmailOrUsername}" cssErrorClass="invalid"/>
                        <springForm:errors path="usernameOrEmail" cssClass="invalid-text" element="div"/>
                    </div>
                    <div class="group">
                        <spring:message code="login.password" var="loginPassword"/>
                        <springForm:password path="pass" class="input" placeholder="${loginPassword}" cssErrorClass="invalid"/>
                        <springForm:errors path="pass" cssClass="invalid-text" element="div"/>
                    </div>
                    <div class="group">
                    </div>
                    <div class="group">
                        <spring:message code="login.default" var="login"/>
                        <input id="first" type="submit" class="button" value="${login}">
                    </div>
                </div>
            </springForm:form>


            <springForm:form method="POST" modelAttribute="registerUser" action="/register">
                <div class="sign-up-htm">
                    <div class="group">
                        <spring:message code="registration.username" var="registerUsername"/>
                        <springForm:input path="username" class="input" placeholder="${registerUsername}" cssErrorClass="invalid"/>
                        <springForm:errors path="username" cssClass="invalid-text" element="div"/>
                    </div>
                    <div class="group">
                        <spring:message code="registration.first.name" var="registerFirstname"/>
                        <springForm:input path="firstName" class="input" placeholder="${registerFirstname}" cssErrorClass="invalid"/>
                        <springForm:errors path="firstName" cssClass="invalid-text" element="div"/>
                    </div>
                    <div class="group">
                        <spring:message code="registration.last.name" var="registerLastname"/>
                        <springForm:input path="lastName" class="input" placeholder="${registerLastname}" cssErrorClass="invalid"/>
                        <springForm:errors path="lastName" cssClass="invalid-text" element="div"/>
                    </div>
                    <div class="group">
                        <spring:message code="registration.email" var="registerEmail"/>
                        <springForm:input path="email" class="input" placeholder="${registerEmail}" cssErrorClass="invalid"/>
                        <springForm:errors path="email" cssClass="invalid-text" element="div"/>
                    </div>
                    <div class="group">
                        <spring:message code="registration.phone" var="registerPhone"/>
                        <springForm:input path="phone" class="input" placeholder="${registerPhone}" cssErrorClass="invalid"/>
                        <springForm:errors path="phone" cssClass="invalid-text" element="div"/>
                    </div>
                    <div class="group">
                        <spring:message code="registration.password" var="registerPassword"/>
                        <springForm:password path="password" class="input" placeholder="${registerPassword}" cssErrorClass="invalid"/>
                        <springForm:errors path="password" cssClass="invalid-text" element="div"/>
                    </div>
                    <div class="group">
                        <spring:message code="registration.confirm" var="registerConfirmPassword"/>
                        <springForm:password path="confirmPassword" class="input" placeholder="${registerConfirmPassword}" cssErrorClass="invalid"/>
                        <springForm:errors path="confirmPassword" cssClass="invalid-text" element="div"/>
                    </div>
                    <div class="group">
                        <spring:message code="registration" var="register"/>
                        <input type="submit" class="button" value="${register}">
                    </div>
                </div>
            </springForm:form>


        </div>
    </div>
</div>
<script>
    $(document).ready(
        function () {
            $('.selected').removeClass('selected');

            $('#tab-1').on('change', function () {
                if (window.location.pathname === '/login') {
                    $('.login-html').animate({height: '50%'})
                } else {
                    $('.login-html').animate({height: '43%'})
                }
            });

            $('#tab-1').on('change', function () {
                if (window.location.pathname === '/authorization') {
                    $('.login-html').animate({height: '43%'})
                }
            });

            $('#tab-2').on('change', function () {
                if (window.location.pathname === '/register') {
                    $('.login-html').animate({height: '100%'})
                } else {
                    $('.login-html').animate({height: '80%'})
                }
            })

            let url = new URL(window.location)

            if (url.searchParams.get('type') === 'registration') {
                $('#tab-2').click()
            }

            if (window.location.pathname === '/register') {
                $('#tab-2').click()
            }

            if (window.location.pathname === '/login') {
                $('.login-html').animate({height: '50%'})
            }
        }
    )
</script>
</body>
</html>
