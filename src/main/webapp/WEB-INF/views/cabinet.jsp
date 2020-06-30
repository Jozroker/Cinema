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
<!-- todo add custom font to all pages -->
<!-- todo add update user information form -->
<head>
    <title>Personal Cabinet</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/cabinet.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/cabinet.js"></script>
</head>
<jsp:include page="header.jsp"/>
<body id="body">
<sec:authorize access="isAuthenticated()">
    <div id="container" class="container">
        <div class="row">
            <div class="col">
                <img class="rounded-circle" src="data:image/jpeg;base64,${user.pictureString}" alt="avatar" data-holder-rendered="true"/>
                <p><spring:message code="registration.first.name"/>:
                    <c:if test="${user.firstName.equals('')}">
                        <spring:message code="state.not.specified"/>
                    </c:if>
                    <c:if test="${!user.firstName.equals('')}">
                        ${user.firstName}
                    </c:if>
                </p>
                <p><spring:message code="registration.last.name"/>:
                    <c:if test="${user.lastName.equals('')}">
                        <spring:message code="state.not.specified"/>
                    </c:if>
                    <c:if test="${!user.lastName.equals('')}">
                        ${user.lastName}
                    </c:if>
                </p>
                <p>Email: ${user.email}</p>
                <p id="tickets-point"><spring:message code="registration.phone"/>: ${user.phone}</p>
            </div>
        </div>
    </div>
    <div id="tickets" class="empty">
        <h4><spring:message code="cabinet.tickets"/></h4>
        <c:forEach var="ticket" items="${tickets}" varStatus="loop">
            <table class="table">
                <tr class="title">
                    <th id="first" class="${ticket.id} first">#</th>
                    <th class="movie"><spring:message code="movie.name"/></th>
                    <th><spring:message code="ticket.row"/></th>
                    <th><spring:message code="ticket.column"/></th>
                    <th><spring:message code="schedules.date"/></th>
                    <th><spring:message code="schedules.start.time"/></th>
                    <th class=" last"><spring:message code="schedules.hall"/></th>
                </tr>
                <tr class="content">
                    <td class="first-td ticket${ticket.id}">${loop.index + 1}</td>
                    <td class="spacing ticket${ticket.id}">${movies[loop.index].name}</td>
                    <td class="spacing ticket${ticket.id}">${ticket.row}</td>
                    <td class="spacing ticket${ticket.id}">${ticket.column}</td>
                    <td class="spacing ticket${ticket.id}">${ticket.seanceDate}</td>
                    <td class="spacing ticket${ticket.id}">${seances[loop.index].timeToString()}</td>
                    <td class="last-td ticket${ticket.id}">${seances[loop.index].hallId}</td>
                </tr>
            </table>
        </c:forEach>
    </div>
</sec:authorize>
</body>
<jsp:include page="footer.jsp"/>
</html>