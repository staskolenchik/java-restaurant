<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Registration</title>
        </head>
        <body>

            <jsp:include page="_header.jsp"></jsp:include>
            <jsp:include page="_menu.jsp"></jsp:include>

            <h3>Registration Page</h3>
                 <p style="color: red;">${errorString}</p>

            <form method="POST" action="${pageContext.request.contextPath}/registration">
                <table border= "0">
                <tr>
                    <td>User Name</td>
                    <td><input type="text" name="userName" value="${user.userName}"/> </td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="text" name="password" value="${user.password}"/> </td>
                </tr>
                <tr>
                    <td>Phone Number</td>
                    <td><input type="text" name="phone" value="${user.phone}"/> </td>
                </tr>
                <tr>
                    <td colspan ="2">
                    <input type="submit" value= "Register" />
                    <a href="${pageContext.request.contextPath}/">Cancel</a>
                    </td>
                </tr>
                </table>
             </form>

            <jsp:include page="_footer.jsp"></jsp:include>
        </body>
        </html>