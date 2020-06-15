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
    <div class="row">
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
                                <li class="items main-pos"><img src="data:image/jpeg;base64,${movie}" alt="movie image"/></li>
                            </c:when>
                            <c:when test="${loop.last}">
                                <li class="items left-pos"><img src="data:image/jpeg;base64,${movie}" alt="movie image"/></li>
                            </c:when>
                            <c:when test="${loop.index == 1}">
                                <li class="items right-pos"><img src="data:image/jpeg;base64,${movie}" alt="movie image"/></li>
                            </c:when>
                            <c:otherwise>
                                <li class="items back-pos"><img src="data:image/jpeg;base64,${movie}" alt="movie image"/></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <%--                    <li class="items main-pos" id="1"><p>1</p></li>--%>
                    <%--                    <li class="items right-pos" id="2">--%>
                    <%--                        <img src="http://farm9.staticflickr.com/8337/8234123289_2b23aeaf06.jpg"/>--%>
                    <%--                    </li>--%>
                    <%--                    <li class="items back-pos" id="3">--%>
                    <%--                        <img src="http://farm9.staticflickr.com/8337/8234711202_831b23a2b7.jpg"/>--%>
                    <%--                    </li>--%>
                    <%--                    <li class="items back-pos" id="4">--%>
                    <%--                        <iframe width="500" height="281" src="https://www.youtube.com/embed/szIEr2F61DU" frameborder="0" allowfullscreen></iframe>--%>
                    <%--                    </li>--%>
                    <%--                    <li class="items back-pos" id="5">--%>
                    <%--                        <iframe src="https://player.vimeo.com/video/19464611" width="500" height="281" frameborder="0" webkitAllowFullScreen mozallowfullscreen--%>
                    <%--                                allowFullScreen></iframe>--%>
                    <%--                    </li>--%>
                    <%--                    <li class="items back-pos" id="6">--%>
                    <%--                        <img src="http://woofie2.pixiq.com/files/cache/20030323_img_7465_3072_x_2048_619x413.jpg"/>--%>
                    <%--                    </li>--%>
                    <%--                    <li class="items back-pos" id="7"><p>3</p></li>--%>
                    <%--                    <li class="items left-pos" id="8"><img src="http://www.mishes.com/wp-content/uploads/2011/12/FlickrMonday07.jpg"/>--%>
                    <%--                    </li>--%>
                </ul>
            </section>
        </div>
        <div class="col-sm">
            <%--                            <input type="button" value="Next" id="next">--%>
            <span id="next"><span id="triangle-right"></span></span>
        </div>
    </div>
    <div class="row">
        <a id="buy" href="${contextPath}/buy/ticket"><spring:message code="home.buy.ticket"/></a>
    </div>
    <div id="schedule">

    </div>
</div>
</body>
<jsp:include page="footer.jsp"/>
</html>
