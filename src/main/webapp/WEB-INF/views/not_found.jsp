<%--
  Created by IntelliJ IDEA.
  User: Jozroker
  Date: 08.06.2020
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/not-found.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body id="main">
<div class="container">
    <div class="row text-center">
        <div class="col-lg-6 offset-lg-3 col-sm-6 offset-sm-3 col-12 p-3 error-main">
            <div class="row">
                <div class="col-lg-8 col-12 col-sm-10 offset-lg-2 offset-sm-1">
                    <h1 class="m-0">404</h1>
                    <c:choose>
                        <c:when test="${message == null}">
                            <h6><spring:message code="state.page.not.found"/></h6>
                        </c:when>
                        <c:otherwise>
                            <h6><spring:message code="state.resource.not.found"/></h6>
                        </c:otherwise>
                    </c:choose>
                    <p>${message}</p>
                    <a id="home" href="${contextPath}/home"><spring:message code="navbar.home"/></a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
