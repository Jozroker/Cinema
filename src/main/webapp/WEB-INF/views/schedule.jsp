<%--
  Created by IntelliJ IDEA.
  User: Jozroker
  Date: 08.06.2020
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Schedule</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/schedule.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/schedule.js"></script>
    <script>

        let create = '<spring:message code="create.default"/>';
        let button = '<spring:message code="create.change"/>';
        let monday = '<spring:message code="day.monday"/>'
        let tuesday = '<spring:message code="day.tuesday"/>'
        let wednesday = '<spring:message code="day.wednesday"/>'
        let thursday = '<spring:message code="day.thursday"/>'
        let friday = '<spring:message code="day.friday"/>'
        let saturday = '<spring:message code="day.saturday"/>'
        let sunday = '<spring:message code="day.sunday"/>'

    </script>
</head>
<body>
<div id="container">
    <div id="date">
        <spring:message code="schedules.date"/>:
        <div id="calendar">
            <jsp:include page="calendar.jsp"/>
        </div>
        <input id="seanceDate" class="input">
    </div>
    <div id="schedule">
        <table class="table">
            <thead>
            <tr class="title">
                <th scope="col" class="first"><spring:message code="number.default"/></th>
                <th scope="col" id="movie"><spring:message code="movie.name"/></th>
                <th scope="col"><spring:message code="schedules.start.time"/></th>
                <th scope="col" colspan="2"><spring:message code="schedules.hall"/></th>
                <th scope="col"><spring:message code="schedules.date.from"/></th>
                <th scope="col"><spring:message code="schedules.date.to"/></th>
                <th scope="col"><spring:message code="schedules.day"/></th>
                <th scope="col" class="last"><spring:message code="schedules.price"/></th>
            </tr>
            </thead>
            <tbody id="table-body"></tbody>
        </table>
    </div>
    <div id="create-seance">
        <table id="create-container">
            <thead>
            <tr class="title">
                <th scope="col" class="first"><spring:message code="movie.name"/></th>
                <th scope="col"><spring:message code="schedules.start.time"/></th>
                <th scope="col" colspan="2"><spring:message code="schedules.hall"/></th>
                <th scope="col"><spring:message code="schedules.date.from"/></th>
                <th scope="col"><spring:message code="schedules.date.to"/></th>
                <th scope="col"><spring:message code="schedules.day"/></th>
                <th scope="col" class="last"><spring:message code="schedules.price"/></th>
            </tr>
            </thead>
            <tbody id="creating-table-body"></tbody>
        </table>
        <button id="create"><spring:message code="create.default"/></button>
    </div>
</div>
</body>
</html>
