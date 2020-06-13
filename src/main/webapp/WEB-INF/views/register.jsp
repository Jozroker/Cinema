<%--
  Created by IntelliJ IDEA.
  User: Jozroker
  Date: 08.06.2020
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <springForm:form method="POST" modelAttribute="user" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>
        <div class="form-group">
            <springForm:input path="username" class="form-control" placeholder="Username"
                              autofocus="true" cssErrorClass="form-control is-invalid"/>
            <springForm:errors path="username" cssClass="has-error"/>
        </div>

        <div class="form-group">
            <springForm:input path="email" class="form-control" placeholder="email"
                              cssErrorClass="form-control is-invalid"/>
            <springForm:errors path="email" cssClass="has-error"/>
        </div>

        <div class="form-group">
            <springForm:input type="text" path="firstName" class="form-control" placeholder="First Name"
                              cssErrorClass="form-control is-invalid"/>
            <springForm:errors path="firstName" cssClass="has-error"/>
        </div>

        <div class="form-group">
            <springForm:input type="text" path="lastName" class="form-control" placeholder="Last Name"
                              cssErrorClass="form-control is-invalid"/>
            <springForm:errors path="lastName" cssClass="has-error"/>
        </div>

        <div class="form-group">
            <springForm:input type="password" path="password" class="form-control" placeholder="Password"
                              cssErrorClass="form-control is-invalid"/>
            <springForm:errors path="password" cssClass="has-error"/>
        </div>

        <div class="form-group">
            <springForm:input type="password" path="passwordConfirm" class="form-control"
                              placeholder="Confirm your password"
                              cssErrorClass="form-control is-invalid"/>
            <springForm:errors path="passwordConfirm" cssClass="has-error"/>
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </springForm:form>

</div>
</body>
</html>
