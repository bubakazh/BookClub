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
    <title>Bookses</title>
    <style>
        .container{
            padding: 15px 32px;
        }
        .header{
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .botones{
            margin: 15px 0px;
            display: flex;
            justify-content: flex-end;
            align-items: center;
        }
        .btn-sm{
            margin-right: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1  class="heading" >${user.getGreeting()}, ${user.userName} </h1>
    <div class="header">
        <h2>Books</h2>
        <div class="botones">
            <a class="btn-sm btn-light btn-outline-dark" href="/newbook">Add Book</a>
            <a class="btn-sm btn-light btn-outline-dark" href="/logout">Logout</a>
        </div>
    </div>
    <div class="row">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Author</th>
                <th>Posted By</th>
                <%-- <th>Actions</th> --%>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td><c:out value="${book.id}"></c:out></td>
                    <td>
                        <a href="/books/${book.id}">
                            <c:out value="${book.title}"></c:out></a>
                    </td>
                    <td><c:out value="${book.author}"></c:out></td>
                    <c:choose>
                        <c:when test="${user.userName == book.user.getUserName()}">
                            <td><p style = "font-weight: bold; font-style: italic; color: deeppink;"><c:out value="${book.user.getUserName()}"></c:out></p></td>
                        </c:when>
                        <c:otherwise>
                            <td><c:out value="${book.user.getUserName()}"></c:out></p></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>