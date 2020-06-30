<%--
  Created by IntelliJ IDEA.
  User: Jozroker
  Date: 12.06.2020
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Create Movie</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/create-movie.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/create-actor.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/duration-picker.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/create-actor.js"></script>
    <script src="${contextPath}/resources/js/create-movie.js"></script>
    <script>
        let contextPath = '<c:out value="${contextPath}"/>';
        let hourChar = '<spring:message code="create.hour.character"/>'
        let minuteChar = '<spring:message code="create.minute.character"/>'
    </script>
</head>
<body id="body">
<div>
    <jsp:include page="header.jsp"/>
</div>
<div id="alert">
    <div id="text"><p></p></div>
    <div id="button"><a>OK</a></div>
</div>
<div class="main">
    <div class="image-holder">
        <div id="image">
            <img id="image-container" alt="movie poster" src="">
            <div id="uploadLabel">
                <label for="fileToUpload"><spring:message code="create.upload"/></label>
            </div>
            <input name="file" id="fileToUpload" type="file"/>
        </div>
        <!-- todo custom button -->

    </div>
    <div id="form">
        <form method="POST" action="${contextPath}/admin/create/movie" autocomplete="off">
            <span class="text"><spring:message code="create.name"/></span>
            <div>
                <input name="name" id="name" type="text">
            </div>
            <span class="text"><spring:message code="create.description"/></span>
            <div>
                <textarea name="description" id="description" rows="4" cols="50"></textarea>
            </div>
            <span class="text"><spring:message code="create.duration"/></span>
            <div>
                <input id="time">
            </div>
            <div>
                <span class="text"><spring:message code="create.actors"/></span>
                <ul id="current-actors">
                    <div class="actors">
                    </div>
                </ul>
                <spring:message code="create.actor" var="createActor"/>
                <div>
                    <input id="search-actor" class="form-control mr-sm-2" type="text" placeholder="${createActor}"
                           aria-label="Search">
                    <div id="actor-creating"></div>
                </div>
                <ul class="list-group" id="all-actors"></ul>
            </div>
            <div>
                <spring:message code="create.default" var="create"/>
                <input id="createMovieBtn" type="submit" class="button" value="${create}" onclick="return false">
            </div>
        </form>
    </div>
</div>
<div>
    <jsp:include page="footer.jsp"/>
</div>
<script src="${contextPath}/resources/js/duration-picker.js"></script>
</body>
</html>
