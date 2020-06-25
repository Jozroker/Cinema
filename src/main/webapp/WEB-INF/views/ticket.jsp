<%--
  Created by IntelliJ IDEA.
  User: Jozroker
  Date: 08.06.2020
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Ticket</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/ticket.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jsbarcode@3.11.0/dist/JsBarcode.all.min.js"></script>
    <script>
        $(document).ready(function () {

            let value = "${ticket.row}" + "." + "${ticket.column}" +
                "." + "${ticket.seanceDate}" + "." + "${seance.timeToString()}";

            JsBarcode("#barcode", value.toString(), {
                width: 2,
                height: 80,
                background: "rgba(0,0,0,0)",
                displayValue: false
            });
        })
    </script>
</head>
<jsp:include page="header.jsp"/>
<body>
<sec:authorize access="isAuthenticated()">
    <div id="space"></div>
    <div id="box">
        <div class="inner">
            <h1>${movie.name}</h1>
            <img id="image" src="data:image/jpeg;base64,${movie.pictureString}" alt="movie image"/>
            <div class='info clearfix'>
                <div class='wp'><spring:message code="ticket.count"/><h2>1</h2></div>
                <div class='wp'><spring:message code="ticket.row"/><h2>${ticket.row}</h2></div>
                <div class='wp'><spring:message code="ticket.column"/><h2>${ticket.column}</h2></div>
                <div class='wp-2'><spring:message code="schedules.hall"/><h2>${hall.type.type}: ${hall.id}</h2></div>
            </div>
            <div class='info clearfix'>
                <div class='wp-3'><spring:message code="schedules.date"/><h2>${ticket.seanceDate}</h2></div>
                <div class='wp-2'><spring:message code="schedules.start.time"/><h2>${seance.timeToString()}</h2></div>
            </div>
            <div class='total clearfix'>
                <h2><spring:message code="ticket.price"/> : <p>${seance.ticketPrice} UAH</p></h2>
            </div>
            <svg id="barcode"></svg>
        </div>
    </div>
</sec:authorize>
</body>
<jsp:include page="footer.jsp"/>

</html>