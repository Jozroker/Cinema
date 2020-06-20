<%--
  Created by IntelliJ IDEA.
  User: Jozroker
  Date: 10.06.2020
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Movie</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/movie.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/movie.js"></script>
</head>
<jsp:include page="header.jsp"/>
<body id="body">
<div class="movie-card">
    <div class="background">
        <img src="data:image/jpeg;base64,${movie.pictureString}" alt="movie image"/>
    </div>
    <div id="content" class="container">


        <a><img class="cover" src="data:image/jpeg;base64,${movie.pictureString}" alt="movie poster"/></a>

        <div class="hero">

            <div class="details">

                <div class="title1">${movie.name}</div>

                <!-- todo rating -->
                <%--                <fieldset class="rating">--%>
                <%--                    <input type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" title="Awesome - 5 stars"></label>--%>
                <%--                    <input type="radio" id="star4half" name="rating" value="4 and a half" /><label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>--%>
                <%--                    <input type="radio" id="star4" name="rating" value="4" checked /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>--%>
                <%--                    <input type="radio" id="star3half" name="rating" value="3 and a half" /><label class="half" for="star3half" title="Meh - 3.5 stars"></label>--%>
                <%--                    <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>--%>
                <%--                    <input type="radio" id="star2half" name="rating" value="2 and a half" /><label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>--%>
                <%--                    <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>--%>
                <%--                    <input type="radio" id="star1half" name="rating" value="1 and a half" /><label class="half" for="star1half" title="Meh - 1.5 stars"></label>--%>
                <%--                    <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>--%>
                <%--                    <input type="radio" id="starhalf" name="rating" value="half" /><label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>--%>
                <%--                </fieldset>--%>

            </div> <!-- end details -->

            <div class="description">

                <!--todo tags -->
                <%--            <div class="column1">--%>
                <%--                <span class="tag">action</span>--%>
                <%--                <span class="tag">fantasy</span>--%>
                <%--                <span class="tag">adventure</span>--%>
                <%--            </div> <!-- end column1 -->--%>

                <div class="column2">

                    <p>${movie.description}</p>

                    <div class="avatars">
                        <c:forEach var="actor" items="${actors}">
                            <a data-tooltip="Person 1" data-placement="top">
                                <img class="avatar" src="data:image/jpeg;base64,${actor.pictureString}" alt="actor${actor.id}" title="${actor.firstName}
                                ${actor.lastName}"/>
                            </a>
                        </c:forEach>

                    </div> <!-- end avatars -->


                </div> <!-- end column2 -->
            </div> <!-- end description -->
        </div> <!-- end hero -->
        <div class="schedule">
            <div class="row days">
                <div class="col-sm"></div>
                <c:forEach var="day" items="${days}" varStatus="loop">
                    <c:set var="item" value="day.${day.name().toLowerCase()}"/>
                    <c:choose>
                        <c:when test="${loop.first}">
                            <div class="col-sm-1">
                                <span id="day${loop.index}" class="active day"><spring:message code="${item}"/></span>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-sm-1">
                                <span id="day${loop.index}" class="day"><spring:message code="${item}"/></span>
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
                    <tbody>
                    <c:forEach var="seance" items="${schedule}" varStatus="loop">
                        <tr>
                            <th scope="row" class="spacing first seance${seance.id}">${loop.index + 1}</th>
                            <td class="movie spacing seance${seance.id}">${seance.movie.name}</td>
                            <td class="spacing seance${seance.id}">${seance.movieBeginTime}</td>
                            <td class="spacing seance${seance.id}">${seance.hall.id}</td>
                            <td class="spacing seance${seance.id}">${seance.hall.type.type}</td>
                            <td class="spacing seance${seance.id}">${seance.ticketPrice} UAH</td>
                            <td class="spacing last seance${seance.id}"><a class="pill" href="${contextPath}/seance/order?seanceId=${seance.id}"><spring:message
                                    code="ticket.buy"/></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>


    </div> <!-- end container -->
</div> <!-- end movie-card -->
</body>
<jsp:include page="footer.jsp"/>
</html>
