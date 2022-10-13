<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <title>one Book</title>
    <style>
        .container{
            padding: 15px 32px;
        }
        .header{
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }
        .botones{
            display: flex;
            align-items: center;
        }
        .btn{
            margin: 10px 7px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1  class="heading" >${user.getGreeting()}, ${user.userName} </h1>
        <div class = "header">
            <h2>${book.title}</h2>
            <a class="float-end" href="/books">Bookshelf</a>
        </div>
        <c:choose>
            <c:when test="${user.userName == book.user.getUserName()}">
                <p>You read ${book.title} by ${book.author}.</p>
            </c:when>
            <c:otherwise>
                <p>${book.user.getUserName()} read ${book.title} by ${book.author}</p>
            </c:otherwise>
        </c:choose>
        <p>${book.myThoughts}</p>
        <div class="botones">
            <c:if test="${user.getId() == book.user.getId()}">
                <a class = "btn btn-sm btn-light btn-outline-dark" href="/books/edit/${book.id}">Edit</a>
                <%--    <a href="/books/destroy/${book.id}">Delete</a>--%>
                <form action="/books/destroy/${book.id}" method = "POST">
                    <input type="hidden" name = "_method" value = "delete">
                    <input class = "btn btn-sm btn-light btn-outline-dark" type="submit" value = "Delete">
                </form>
            </c:if>
        </div>
    </div>
    </body>
</html>