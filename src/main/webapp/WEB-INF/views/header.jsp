<%--
  Created by IntelliJ IDEA.
  User: Jozroker
  Date: 08.06.2020
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${contextPath}/resources/css/header.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/header.js"></script>
    <script>
        let contextPath = '<c:out value="${contextPath}"/>';
    </script>
</head>
<header>
    <a class="navbar-brand" href="${contextPath}/home">
        <img id="logo" src="${contextPath}/resources/image/logo.png"
             alt="logo"/>
    </a>
    <nav class="navbar navbar-expand-md navbar-dark mb-4" role="navigation">
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarCollapse" aria-controls="navbarCollapse"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-1">
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/schedule">
                        <span id="schedule-link"><spring:message code="navbar.schedule"/></span> <!-- selected -->
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/movies">
                        <span id="movies-link"><spring:message code="navbar.movies"/></span>
                    </a>
                </li>
                <sec:authorize access="hasAuthority('ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link" href="${contextPath}/admin/create/movie">
                            <span id="create-movie-link"><spring:message code="create.movie"/></span>
                        </a>
                    </li>
                </sec:authorize>
            </ul>
            <form id="search-bar" class="form-inline mt-2 mt-md-0" autocomplete="off">
                <spring:message code="navbar.search.button" var="search"/>
                <!-- todo create page from search result -->
                <input id="search-line" class="form-control mr-sm-2" type="text" placeholder="${search}"
                       aria-label="Search" autocomplete="false">
                <ul class="list-group" id="movies"></ul>
                <button id="search-button" class="btn btn-outline-success my-2 my-sm-0" type="submit">
                    ${search}
                </button>
            </form>
            <ul id="right" class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link lang" href="?language=en">EN</a>
                </li>
                <li>
                    <span id="vertical-line"></span>
                </li>
                <li>
                    <a class="nav-link lang" href="?language=ua">UA</a>
                </li>
                <li id="sign-in">
                    <sec:authorize access="isAuthenticated()">
                        <sec:authentication var="user" property="principal"/>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="user"
                       data-toggle="dropdown" aria-haspopup="true"
                       aria-expanded="false">${user.username}</a>
                    <ul id="user-dropdown" class="dropdown-menu" aria-labelledby="dropdown1">
                        <a class="link" href="${contextPath}/cabinet">
                            <li class="dropdown-item">
                                <spring:message code="cabinet.default"/>
                            </li>
                        </a>
                        <a class="link" href="${contextPath}/cabinet#tickets-point">
                            <li class="dropdown-item">
                                <spring:message code="cabinet.tickets"/>
                            </li>
                        </a>
                        <a class="link" href="${contextPath}/logout">
                            <li id="logout" class="dropdown-item">
                                <spring:message code="navbar.signout"/>
                            </li>
                        </a>
                    </ul>
                </li>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <button id="sign-in-button" class="btn btn-outline-success my-2 my-sm-0" type="button">
                        <spring:message code="navbar.signin"/>
                    </button>
                </sec:authorize>
                </li>
            </ul>
        </div>
    </nav>
</header>