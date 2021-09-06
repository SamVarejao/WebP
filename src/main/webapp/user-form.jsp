
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>CRUD App</title>
    </head>
    <body>
        <div 
            <div>
            <a href="<%=request.getContextPath()%>/list"
               >Return</a>
        </div>

        <div>
            <c:if test="${user != null}">
                <form action="update" method="post">
                </c:if>

                <c:if test="${user == null}">
                    <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <c:if test="${user != null}">
                            <h1>Edit User </h1>
                        </c:if>

                        <c:if test="${user == null}">
                            <h1>Add New User </h1> 
                        </c:if>
                    </caption>

                    <c:if test="${user != null}">
                        <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                    </c:if>

                    <fieldset >
                        <label>User Name</label> <input type="text"
                                                        value="<c:out value='${user.name}' />" class="form-control"
                                                        name="name" required="required">
                    </fieldset>

                    <fieldset >
                        <label>User Email</label> <input type="text"
                                                         value="<c:out value='${user.email}' />" class="form-control"
                                                         name="email">
                    </fieldset>

                    <fieldset >
                        <label>User Country</label> <input type="text"
                                                           value="<c:out value='${user.country}' />" class="form-control"
                                                           name="country">
                    </fieldset>

                    <button type="submit">Save</button>
                </form>
        </div>

    </body>
</html>