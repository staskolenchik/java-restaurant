<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>User Info</title>
 </head>
 <body>

    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>

    <h3>Hello: ${user.userName}</h3>
    <p>User Name: ${user.userName}</p>
    <p>Phone Number: ${user.phone } </p><br>
    <p style="color: red;">${errorString}</p>

    <c:if test="${not empty orderedDishList}">
    <p>Ordered dishes</p>
        <table border="1" cellpadding="5" cellspacing="1">
                         <tr>
                             <th>Name</th>
                             <th>Description</th>
                             <th>Dish Type</th>
                             <th>Dish Price</th>
                         </tr>
                         <c:forEach items="${orderedDishList}" var="dish" >
                            <tr>
                                 <td>${dish.name}</td>
                                 <td>${dish.description}</td>
                                 <td>${dish.dishType}</td>
                                 <td>${dish.dishPrice}</td>
                            </tr>
                         </c:forEach>
        </table>
        <c:if test="${not empty orderTotalCost}">
                <p>Total Cost = ${orderTotalCost}</p>
        </c:if>
    </c:if>

    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
</html>