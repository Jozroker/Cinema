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
    <script>
        let buy = '<spring:message code="ticket.buy"/>';
    </script>
</head>
<jsp:include page="header.jsp"/>
<body id="table">
<div id="content">
    <div class="row section">
        <div class="col-sm">
            <span id="prev"><span id="triangle-left"></span></span>
        </div>
        <div class="col-sm">
            <!-- todo set blur selected image to background section -->
            <section>
                <ul class="carousel">
                    <c:forEach var="movie" items="${movies}" varStatus="loop">
                        <c:choose>
                            <c:when test="${loop.first}">
                                <li class="${movie.id} items main-pos" id="1"><img src="data:image/jpeg;base64,${movie.pictureString}" alt="movie image"/></li>
                            </c:when>
                            <c:when test="${loop.last}">
                                <li class="${movie.id} items left-pos"
                                    id="${loop.count}"><img src="data:image/jpeg;base64,${movie.pictureString}" alt="movie image"/></li>
                            </c:when>
                            <c:when test="${loop.index == 1}">
                                <li class="${movie.id} items right-pos" id="2"><img src="data:image/jpeg;base64,${movie.pictureString}" alt="movie image"/></li>
                            </c:when>
                            <c:otherwise>
                                <li class="${movie.id} items back-pos" id="${loop.index + 1}"><img src="data:image/jpeg;base64,${movie.pictureString}" alt="movie image"/>
                                </li>
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
            <c:set var="item" value="day.${day.name().toLowerCase()}"/>
            <c:choose>
                <c:when test="${loop.first}">
                    <div class="column">
                        <span id="day${loop.index}" class="${day.name().toLowerCase()} active day"><spring:message code="${item}"/></span>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="column">
                        <span id="day${loop.index}" class="${day.name().toLowerCase()} day"><spring:message code="${item}"/></span>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <%--        <c:forEach var="day" items="${days}" varStatus="loop">--%>
        <%--            <c:set var="item" value="day.${day.name().toLowerCase()}"/>--%>
        <%--            <c:choose>--%>
        <%--                <c:when test="${loop.first}">--%>
        <%--                    <div class="column">--%>
        <%--                        <span id="day${loop.index}" class="active day"><spring:message code="${item}"/></span>--%>
        <%--                    </div>--%>
        <%--                </c:when>--%>
        <%--                <c:otherwise>--%>
        <%--                    <div class="column">--%>
        <%--                        <span id="day${loop.index}" class="day"><spring:message code="${item}"/></span>--%>
        <%--                    </div>--%>
        <%--                </c:otherwise>--%>
        <%--            </c:choose>--%>
        <%--        </c:forEach>--%>
        <div class="col-sm"></div>
        <div class="col-sm"></div>
        <div class="col-sm"></div>
        <div class="col-sm"></div>
        <div class="col-sm"></div>
        <div class="col-sm"></div>
    </div>
    <div id="schedule">
        <table class="table">
            <thead>
            <tr id="title">
                <th scope="col" class="first"><spring:message code="number.default"/></th>
                <th scope="col" id="movie"><spring:message code="movie.name"/></th>
                <th scope="col"><spring:message code="schedules.start.time"/></th>
                <th scope="col" colspan="2"><spring:message code="schedules.hall"/></th>
                <th scope="col"><spring:message code="schedules.price"/></th>
                <th scope="col" class="last"></th>
            </tr>
            </thead>
            <tbody id="schedule-body">
            <%--            <c:forEach var="seance" items="${schedule}" varStatus="loop">--%>
            <%--                <tr>--%>
            <%--                    <th scope="row" class="spacing first seance${seance.id}">${loop.index + 1}</th>--%>
            <%--                    <td class="movie spacing seance${seance.id}">${seance.movie.name}</td>--%>
            <%--                    <td class="spacing seance${seance.id}">${seance.beginTimeToString()}</td>--%>
            <%--                    <td class="spacing seance${seance.id}">${seance.hall.id}</td>--%>
            <%--                    <td class="spacing seance${seance.id}">${seance.hall.type.type}</td>--%>
            <%--                    <td class="spacing seance${seance.id}">${seance.ticketPrice} UAH</td>--%>
            <%--                    <td--%>
            <%--                            class="spacing last seance${seance.id}"><a class="pill"--%>
            <%--                                                                       href="${contextPath}/seance/order?seanceId=${seance.id}"><spring:message code="ticket.buy"/></a>--%>
            <%--                    </td>--%>
            <%--                </tr>--%>
            <%--            </c:forEach>--%>
            </tbody>
        </table>
    </div>
</div>
</body>
<jsp:include page="footer.jsp"/>
</html>
