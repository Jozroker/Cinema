<%--
  Created by IntelliJ IDEA.
  User: Jozroker
  Date: 21.06.2020
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" href="${contextPath}/resources/css/create-actor.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/create-actor.js"></script>
</head>
<body>

<springForm:form method="POST" modelAttribute="actor" action="${contextPath}/admin/create/actor" enctype="multipart/form-data">
    <div id="avatar-image">
        <img id="avatar-image-container" class="avatar" src="${contextPath}/resources/image/default-avatar.png" alt="actor avatar"/>
        <label for="file">IMG</label>
        <input name="file" id="file" type="file"/>
    </div>
    <div class="group">
        <spring:message code="create.actor.first.name" var="firstName"/>
        <springForm:input path="firstName" class="input" placeholder="${firstName}" cssErrorClass="invalid"/>
    </div>
    <div class="group">
        <spring:message code="create.actor.last.name" var="lastName"/>
        <springForm:input path="lastName" class="input" placeholder="${lastName}" cssErrorClass="invalid"/>
    </div>
    <div class="create">
        <spring:message code="create.actor" var="create"/>
        <input id="createBtn" type="submit" class="button" value="${create}" onclick="return false">
    </div>
</springForm:form>

</body>
</html>
