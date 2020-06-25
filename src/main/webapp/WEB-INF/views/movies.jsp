<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jozroker
  Date: 12.06.2020
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Movies</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/movies.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<jsp:include page="header.jsp"/>
<body id="body">
<div id="list">
    <div class="row">
        <c:forEach var="movie" items="${movies}" varStatus="loop">
        <c:if test="${loop.index % 6 == 0}">
    </div>
    <div class="row">
        </c:if>
        <div style="padding-right: 0" class="col" id="container">
            <a href="${contextPath}/movie/${movie.id}">
                <img src="data:image/jpeg;base64,${movie.pictureString}" alt="movie poster"/>
                <div class="overlay">
                    <span>${movie.name}</span>
                </div>
            </a>
        </div>
        </c:forEach>
        <c:set var="count" value="${movies.size()}"/>
        <c:if test="${count / 6 > 1}">
            <c:forEach var="i" begin="1" end="${6 - (count % 6)}">
                <div style="padding-right: 0" class="col"></div>
            </c:forEach>
        </c:if>
    </div>
</div>
</body>
<jsp:include page="footer.jsp"/>
</html>
