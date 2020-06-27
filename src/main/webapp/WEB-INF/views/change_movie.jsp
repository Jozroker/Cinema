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
    <link rel="stylesheet" href="${contextPath}/resources/css/change-movie.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/create-actor.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/change-movie.js"></script>
    <script src="${contextPath}/resources/js/create-actor.js"></script>
    <script>
        let contextPath = '<c:out value="${contextPath}"/>';
    </script>
</head>
<body>

<div class="main">
    <div class="image-holder">
        <div id="image">
            <img id="image-container" alt="movie poster" src="${movie.pictureString}">
            <label for="fileToUpload">IMG</label>
            <input name="file" id="fileToUpload" type="file"/>
        </div>
        <!-- todo custom button -->

    </div>
    <div id="form">
        <form method="POST" action="${contextPath}/admin/change/movie">
            <spring:message code="create.name"/>
            <div>
                <input name="name" id="name" type="text" value="${movie.name}">
            </div>
            <spring:message code="create.description"/>
            <div>
                <textarea name="description" id="description" rows="4" cols="50">${movie.description}</textarea>
            </div>
            <spring:message code="create.duration"/>
            <div>
                <input id="time" value="${movie.getDurationTime}">
            </div>
            <div>
                <spring:message code="create.actors"/>
                <ul class="actors">
                    <c:forEach var="actor" items="${actors}">
                        <li class="list-group-item avatar-selected">
                            <img class="${actor.id} avatar" src="data:image/jpeg;base64,${actor.pictureString}" alt="actor${actor.id}"/>
                                ${actor.firstName} ${actor.lastName}
                        </li>
                    </c:forEach>
                </ul>
                <spring:message code="navbar.search.button" var="search"/>
                <div>
                    <input id="search-line" class="form-control mr-sm-2" type="text" placeholder="${search}"
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

</body>
</html>