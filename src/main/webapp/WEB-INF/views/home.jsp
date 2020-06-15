<%--
  Created by IntelliJ IDEA.
  User: Jozroker
  Date: 08.06.2020
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/home.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/home.js"></script>
</head>
<jsp:include page="header.jsp"/>
<body id="table">
<div>
    <div class="row section">
        <div class="col-sm">
            <%--                            <input type="button" value="Prev" id="prev">--%>
            <span id="prev"><span id="triangle-left"></span></span>
        </div>
        <div class="col-sm">
            <section>
                <ul class="carousel">
                    <c:forEach var="movie" items="${movies}" varStatus="loop">
                        <c:choose>
                            <c:when test="${loop.first}">
                                <li class="items main-pos" id="1"><img src="data:image/jpeg;base64,${movie.pictureString}" alt="movie image"/></li>
                            </c:when>
                            <c:when test="${loop.last}">
                                <li class="items left-pos" id="${loop.count}"><img src="data:image/jpeg;base64,${movie.pictureString}" alt="movie image"/></li>
                            </c:when>
                            <c:when test="${loop.index == 1}">
                                <li class="items right-pos" id="2"><img src="data:image/jpeg;base64,${movie.pictureString}" alt="movie image"/></li>
                            </c:when>
                            <c:otherwise>
                                <li class="items back-pos" id="${loop.index + 1}"><img src="data:image/jpeg;base64,${movie.pictureString}" alt="movie image"/></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </section>
        </div>
        <div class="col-sm">
            <%--                            <input type="button" value="Next" id="next">--%>
            <span id="next"><span id="triangle-right"></span></span>
        </div>
    </div>
    <div class="row buy-button">
        <button id="buy"><spring:message code="home.buy.ticket"/></button>
    </div>
    <div class="row days">
        <div class="col-sm"></div>
        <c:forEach var="day" items="${days}" varStatus="loop">
            <c:choose>
                <c:when test="${loop.first}">
                    <div class="col-sm">
                        <span id="day${loop.index}" class="active day">${day.name()}</span>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="col-sm">
                        <span id="day${loop.index}" class="day">${day.name()}</span>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <div class="col-sm"></div>
        <div class="col-sm"></div>
        <div class="col-sm"></div>
        <div class="col-sm"></div>
        <div class="col-sm"></div>
        <div class="col-sm"></div>
    </div>
    <div id="schedule">
        <ul class="list-group">
            <c:forEach var="seance" items="${schedule}" varStatus="loop">
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div></div>
                    <div>
                        <span class="seance${loop.index + 1}">${loop.index + 1}</span>
                    </div>
                    <div>
                        <span class="seance${loop.index + 1}">${seance.movie.name}</span>
                    </div>
                    <div>
                        <span class="seance${loop.index + 1}">${seance.movieBeginTime}</span>
                    </div>
                    <div>
                        <span class="seance${loop.index + 1}">${seance.hall.id}</span>
                    </div>
                    <div>
                        <span class="seance${loop.index + 1}">${seance.hall.type.name().substring(1)}</span>
                    </div>
                    <div>
                        <span class="seance${loop.index + 1}">${seance.ticketPrice} UAH</span>
                    </div>
                    <span class="badge badge-primary badge-pill"><spring:message code="ticket.buy"/></span>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
<jsp:include page="footer.jsp"/>
</html>
