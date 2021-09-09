<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<metadata
    >

<html >   

    <head>
        <title>CRUD App</title>
    </head>
    <body>
        <div>
            <div>
                <h1>Java CRUD App</h1>
            </div>

            <div>
                <form action="<%=request.getContextPath()%>/new">
                    <input type="submit" value="New user" />
                </form>
            </div>
        </div>

        <c:forEach var="user" items="${listUser}">
            <div style="border-style: double; padding: 10px;">
                <p><span> Id: <c:out value="${user.id}"/>|
                        Name: <c:out value="${user.name}" />|
                        Email: <c:out value="${user.email}" />|
                        Country: <c:out value="${user.country}" />|
                    </span>
                </p>
                <a href="edit?id=<c:out value='${user.id}' />">Edit</a>
                <a
                    href="delete?id=<c:out value='${user.id}' />">Delete</a>

                <br>
            </div>


        </c:forEach>


    </body>


</html>