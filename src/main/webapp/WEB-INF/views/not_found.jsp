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
    <style>
        /*#home-button {*/
        /*    border-color: #303235;*/
        /*}*/

        /*#home-button:hover {*/
        /*    background-color: #303235;*/
        /*}*/

        #home {
            text-decoration: none;
            color: #303235;
            padding: 2% 15% 2% 15%;
            border: 1px solid #303235;
            border-radius: 5px;
        }

        #home:hover {
            background-color: #303235;
            color: #9A9DA0;
        }

        .text-center {
            margin-top: 10%;
        }

        /*#home:hover {*/
        /*    color: #9A9DA0;*/
        /*}*/

        /*.home:hover {*/
        /*    background-color: #303235;*/
        /*    color: #9A9DA0;*/
        /*}*/

        #main {
            background-color: #303235;
        }

        body {
            margin-top: 150px;
            /*background-color: #8B8C82;*/
        }

        .container {
            margin-top: 50px;
        }

        .error-main {
            /*background-color: #4E5053;*/
            background-color: #9A9DA0;
            box-shadow: 0px 0px 15px 15px #9A9DA0;
        }

        .error-main h1 {
            font-weight: bold;
            color: #303235;
            font-size: 200px;
            text-shadow: 2px 4px 5px #000000;
        }

        .error-main h6 {
            font-size: 30px;
            color: #303235;
        }

        .error-main p {
            color: #303235;
            font-size: 20px;
        }
    </style>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<%--<jsp:include page="header.jsp"/>--%>
<body id="main">
<div class="container">
    <div class="row text-center">
        <div class="col-lg-6 offset-lg-3 col-sm-6 offset-sm-3 col-12 p-3 error-main">
            <div class="row">
                <div class="col-lg-8 col-12 col-sm-10 offset-lg-2 offset-sm-1">
                    <h1 class="m-0">404</h1>
                    <c:choose>
                        <c:when test="${message == null}">
                            <h6>Page not found</h6>
                        </c:when>
                        <c:otherwise>
                            <h6>Resource not found</h6>
                        </c:otherwise>
                    </c:choose>
                    <p>${message}</p>
                    <%--                    <button id="home-button" class="btn home" type="submit">--%>
                    <%--                        <a id="home" class="home" href="${contextPath}/home"><spring:message code="navbar.home"/></a>--%>
                    <%--                    </button>--%>
                    <a id="home" href="${contextPath}/home"><spring:message code="navbar.home"/></a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
