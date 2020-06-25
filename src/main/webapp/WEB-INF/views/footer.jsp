<%--
  Created by IntelliJ IDEA.
  User: Jozroker
  Date: 10.06.2020
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<footer class="page-footer font-small mdb-color lighten-3 pt-4">

    <div class="container text-center text-md-left">

        <div class="row">

            <div class="col-md-4 col-lg-3 mr-auto my-md-4 my-0 mt-4 mb-1">

                <h5 class="font-weight-bold text-uppercase mb-4 colored-text"><spring:message code="footer.about"/></h5>
                <p><spring:message code="footer.info"/></p>
            </div>

            <hr class="clearfix w-100 d-md-none">

            <div class="col-md-2 col-lg-3 mx-auto my-md-4 my-0 mt-4 mb-1">

                <h5 class="font-weight-bold text-uppercase mb-4 colored-text"><spring:message code="footer.contacts"/></h5>

                <ul class="list-unstyled">
                    <li>
                        <p>
                            <img class="icon" src="${contextPath}/resources/image/mail.png" alt="letter image">
                            <a id="mail"
                               href="https://mail.google.com/mail/u/0/#inbox?compose=CllgCJvpbJcRWTsGrTvvJtbnHKbBHjZhnxkvFsghKKbsXqgdzdzvhrlMdpmQwSzCmhGbvXjTGkL">info@point.com</a>
                        </p>
                    </li>
                    <li>
                        <p>
                            <img class="icon" src="${contextPath}/resources/image/phone.png" alt="phone image">
                            <a>+1 016-627-2318</a>
                        </p>
                    </li>
                </ul>

            </div>

            <hr class="clearfix w-100 d-md-none">

            <div class="col-md-4 col-lg-3 mx-auto my-md-4 my-0 mt-4 mb-1">

                <h5 class="font-weight-bold text-uppercase mb-4 colored-text"><spring:message code="footer.location"/></h5>

                <ul class="list-unstyled">
                    <ul class="list-unstyled"><spring:message code="city1"/>
                        <li class="place">
                            <i class="fas fa-home"></i><spring:message code="city1.place1"/>
                        </li>
                    </ul>
                    <br/>
                    <ul class="list-unstyled"><spring:message code="city2"/>
                        <li class="place">
                            <i class="fas fa-envelope"></i><spring:message code="city2.place1"/>
                        </li>
                        <li class="place">
                            <i class="fas fa-phone"></i><spring:message code="city2.place2"/>
                        </li>
                    </ul>
                </ul>

            </div>

            <hr class="clearfix w-100 d-md-none">

            <div class="col-md-2 col-lg-2 text-center mx-auto my-4">

                <h5 class="font-weight-bold text-uppercase mb-4 colored-text"><spring:message code="footer.follow"/></h5>

                <a type="button" class="btn-floating">
                    <img id="test" class="social img" src="${contextPath}/resources/image/facebook.png" alt="facebook icon">
                </a>
                <a type="button" class="btn-floating">
                    <img class="social img" src="${contextPath}/resources/image/instagram.png" alt="instagram icon">
                </a>
                <a type="button" class="btn-floating">
                    <img class="social img" src="${contextPath}/resources/image/youtube.png" alt="youtube icon">
                </a>
                <div id="app">
                    <h5 class="font-weight-bold text-uppercase colored-text"><spring:message code="footer.app"/></h5>
                    <a type="button" class="btn-floating">
                        <img class="social-2 img" src="${contextPath}/resources/image/playmarket.png" alt="play market icon">
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-copyright text-center py-3 colored-text">Copyright Point Cinema © 2020:
        <span id="text"><spring:message code="footer.rights"/></span>
    </div>
</footer>
