<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html >   

    <head>
        <title>CRUD App</title>

    </head>
    <body>
        <div>
            <div>
                CRUD App
            </div>

            <div>
                <a href="<%=request.getContextPath()%>/new">Add
                    New User</a>
            </div>
        </div>

        <c:forEach var="user" items="${listUser}">
            <div>
                <c:out value="${user.id}" />
                <c:out value="${user.name}" />
                <c:out value="${user.email}" />
                <c:out value="${user.country}" />
                <a href="edit?id=<c:out value='${user.id}' />">Edit</a>
                <a
                    href="delete?id=<c:out value='${user.id}' />">Delete</a>

                <br>
            </div>


        </c:forEach>


    </body>


</html>