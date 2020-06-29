<%--
  Created by IntelliJ IDEA.
  User: Jozroker
  Date: 12.06.2020
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<sec:authentication var="user" property="principal"/>
<html>
<head>
    <title>Buy ticket</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/ticket-order.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/ticket-order.js"></script>
    <script>
        let movieId = '<c:out value="${movie.id}"/>';
    </script>
</head>
<body id="body">
<div id="header">
    <jsp:include page="header.jsp"/>
</div>
<div id="alert">
    <div id="alertText"><p></p></div>
    <div id="alertBtn"><a>OK</a></div>
</div>
<div class="register-photo">
    <div class="form-container">
        <div id="image" class="image-holder">
            <img src="data:image/jpeg;base64,${movie.pictureString}" alt="movie poster"/>
        </div>
        <div style="display: flex">
            <sec:authorize access="hasAuthority('USER') || hasAuthority('ADMIN')">
            <form id="order" method="POST" action="${contextPath}/order/user/ticket">
                </sec:authorize>
                <sec:authorize access="hasAuthority('WORKER')">
                <form id="order" method="POST" action="${contextPath}/order/ticket">
                    </sec:authorize>
                    <div id="form">
                        <h3 class="text-center"><strong><spring:message code="home.buy.ticket"/></strong></h3>
                        <h2><strong>${movie.name}</strong></h2>
                        <div id="info">
                            <div id="date" class="form-group">
                                <div class="text">
                                    <spring:message code="schedules.date"/>:
                                </div>
                                <div id="calendar">
                                    <jsp:include page="calendar.jsp"/>
                                </div>
                                <input id="seanceDate" class="input">
                            </div>
                            <div id="time" class="form-group">
                                <div class="text">
                                    <spring:message code="schedules.start.time"/>:
                                </div>
                                <div id="time-list">
                                    <div id="time-container" class="list-group">
                                        <p class="list-group-item list-group-item-action"><spring:message code="state.date.not.match"/></p>
                                    </div>
                                </div>
                                <input id="seanceTime" class="input">
                            </div>
                        </div>
                        <div id="places">
                            <div id="screen"></div>
                            <div id="messagePanel" class="messagePanel"></div>
                            <div id="button">
                                <button id="buy" class="btn btn-primary btn-block" type="submit" onclick="return false"><spring:message code="ticket.buy"/></button>
                            </div>
                        </div>
                    </div>
                </form>
        </div>
    </div>
</div>
<div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
