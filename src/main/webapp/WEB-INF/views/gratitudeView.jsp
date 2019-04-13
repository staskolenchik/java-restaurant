<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
        <head>
            <meta http-equiv="refresh" content="3;url=http://localhost:8080/java-restaurant/home">
            <title>Gratitude page</title>
        </head>
        <body>

            <jsp:include page="_header.jsp"></jsp:include>
            <jsp:include page="_menu.jsp"></jsp:include>

            <h1>Thank you for payment! Redirecting in 3 seconds...</h1>


            <jsp:include page="_footer.jsp"></jsp:include>
        </body>
</html>