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
    <title>Change Movie</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/change-movie.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/create-actor.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/duration-picker.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/change-movie.js"></script>
    <script src="${contextPath}/resources/js/create-actor.js"></script>
    <script>
        let contextPath = '<c:out value="${contextPath}"/>';
        let durationTime = '<c:out value="${movie.duration}"/>';
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
            <img id="image-container" alt="movie poster" src="data:image/jpeg;base64,${movie.pictureString}">
            <div id="uploadLabel">
                <label for="fileToUpload">IMG</label>
            </div>
            <input name="file" id="fileToUpload" type="file"/>
        </div>
        <!-- todo custom button -->

    </div>
    <div id="form">
        <form class="${movie.id}" method="POST" action="${contextPath}/admin/change/movie" autocomplete="off">
            <span class="text"><spring:message code="create.name"/></span>
            <div>
                <input name="name" id="name" type="text" value="${movie.name}">
            </div>
            <span class="text"><spring:message code="create.description"/></span>
            <div>
                <textarea name="description" id="description" rows="4" cols="50">${movie.description}</textarea>
            </div>
            <span class="text"><spring:message code="create.duration"/></span>
            <div>
                <input id="time">
            </div>
            <div>
                <span class="text"><spring:message code="create.actors"/></span>
                <ul id="current-actors">
                    <div class="actors">
                        <c:forEach var="actor" items="${actors}">
                            <li class="current-actor avatar-selected">
                                <img class="${actor.id} avatar" src="data:image/jpeg;base64,${actor.pictureString}" alt="actor${actor.id}"/>
                                    ${actor.firstName} ${actor.lastName}
                                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd"
                                          d="M14 1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
                                    <path fill-rule="evenodd" d="M11.854 4.146a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708-.708l7-7a.5.5 0 0 1 .708 0z"/>
                                    <path fill-rule="evenodd" d="M4.146 4.146a.5.5 0 0 0 0 .708l7 7a.5.5 0 0 0 .708-.708l-7-7a.5.5 0 0 0-.708 0z"/>
                                </svg>
                            </li>
                        </c:forEach>
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
                <spring:message code="create.change" var="change"/>
                <input id="changeMovieBtn" type="submit" class="button" value="${change}" onclick="return false">
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
