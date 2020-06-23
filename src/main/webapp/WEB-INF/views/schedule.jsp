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
        $(document).ready(function () {
            $(document).on('click', '.table-row', function () {
                $(this).removeClass('table-row').addClass('change-row');
                let form =
                    '<th scope="row" class="' + $(this).find('th').attr('class').split(/\s+/)[0] + ' spacing first seance">' +
                    '<input id="seance-id" name="id" type="hidden" value="' + $(this).find('th').attr('class').split(/\s+/)[0] + '">' + $(this).find('th').text() +
                    '</th>' +
                    '<td class="movie spacing"><input class="' + $($(this).find('td')[0]).attr('class').split(/\s+/)[0] + '" name="movieName" id="movie-input" type="text" value="' +
                    $(this).find('td')[0].innerText + '"></td>' +
                    '<td class="spacing"><input name="time" type="time" id="timepicker" value="' + $(this).find('td')[1].innerText + '"></td>' +
                    '<td class="spacing" colspan="2"><input name="hallId" id="hall-input" type="number" min="1" max="7" value="' + $(this).find('td')[2].innerText +
                    '"></td>' +
                    '<td class="spacing"><input name="dateFrom" type="date" id="date-from" value="' + $(this).find('td')[4].innerText + '"></td>' +
                    '<td class="spacing"><input name="dateTo" type="date" id="date-to" value="' + $(this).find('td')[5].innerText + '"></td>' +
                    '<td class="spacing"><input name="price" id="price-input" type="number" step="0.01" value="' + $(this).find('td')[6].innerText.slice(0, -4) +
                    '"> UAH</td>' +
                    '<td class="spacing last change"><button id="change"><spring:message code="create.change"/></button></td>';
                $(this).html(form);
                $('#schedule').css({'margin-right': '35%'})
                $('.change').css({'width': '0'})
            })
        })

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
            <tr id="title">
                <th scope="col" class="first"><spring:message code="number.default"/></th>
                <th scope="col" id="movie"><spring:message code="movie.name"/></th>
                <th scope="col"><spring:message code="schedules.start.time"/></th>
                <th scope="col" colspan="2"><spring:message code="schedules.hall"/></th>
                <th scope="col"><spring:message code="schedules.date.from"/></th>
                <th scope="col"><spring:message code="schedules.date.to"/></th>
                <th scope="col" class="last"><spring:message code="schedules.price"/></th>
            </tr>
            </thead>
            <tbody id="table-body">
            <%--                <c:forEach var="seance" items="${schedule}" varStatus="loop">--%>
            <%--                    <tr>--%>
            <%--                        <th scope="row" class="spacing first seance${seance.id}">${loop.index + 1}</th>--%>
            <%--                        <td class="movie spacing seance${seance.id}">${seance.movie.name}</td>--%>
            <%--                        <td class="spacing seance${seance.id}">${seance.movieBeginTime}</td>--%>
            <%--                        <td class="spacing seance${seance.id}">${seance.hall.id}</td>--%>
            <%--                        <td class="spacing seance${seance.id}">${seance.hall.type.type}</td>--%>
            <%--                        <td class="spacing seance${seance.id}">${seance.ticketPrice} UAH</td>--%>
            <%--                        <td--%>
            <%--                                class="spacing last seance${seance.id}"><a class="pill"--%>
            <%--                                                                           href="${contextPath}/seance/order?seanceId=${seance.id}"><spring:message code="ticket.buy"/></a>--%>
            <%--                        </td>--%>
            <%--                    </tr>--%>
            <%--                </c:forEach>--%>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
