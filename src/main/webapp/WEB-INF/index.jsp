<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <title>Tacos</title>
    <style>
        .container{
            padding: 15px 32px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            /*align-items: center;*/
        }
        .header{
            text-align: center;
        }
        .row{
            display: flex;
            justify-content: space-around;
        }
        .col{
            margin: 10px 10px;
            width: auto;
        }
        .form-group{
            padding: 7px 0px;
        }
        .btn{
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1  class="header">Login and Registration</h1>
        <div class="row">
            <div class="col">
                <h2>Register</h2>
                <div class = "form-group">
                    <form:form action="/register" method="post" modelAttribute="newUser">
                    <div class="form-group">
                        <form:label path="userName">Username</form:label>
                        <form:input  class="form-control" path="userName"/>
                        <form:errors path="userName"/>
                    </div>
                    <div class="form-group">
                        <form:label path="email">Email</form:label>
                        <form:input class="form-control" path="email"/>
                        <form:errors path="email"/>
                    </div>
                    <div class="form-group">
                        <form:label path="password">Password</form:label>
                        <form:input type = "password" class="form-control" path="password"/>
                        <form:errors path="password"/>
                    </div>
                    <div class="form-group">
                        <form:label path="confirm">Confirm password</form:label>
                        <form:input type = "password" class="form-control" path="confirm"/>
                        <form:errors path="confirm"/>
                    </div>
                </div>
                <input class = "btn btn-sm btn-light btn-outline-dark" type="submit" value="Register">
                </form:form>
            </div>
            <div class="col">
                <h2>Login</h2>
                <div class="form-group">
                    <form:form action="/login" method="post" modelAttribute="newLogin">
                        <div class="form-group">
                            <form:label path="email">Email</form:label>
                            <form:input class="form-control" path="email"/>
                            <form:errors path="email"/>
                        </div>
                        <div class="form-group">
                            <form:label path="password">Password</form:label>
                            <form:input type = "password" class="form-control" path="password"/>
                            <form:errors path="password"/>
                        </div>
                        <input class = "btn btn-sm btn-light btn-outline-dark" type="submit" value="Login">
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

