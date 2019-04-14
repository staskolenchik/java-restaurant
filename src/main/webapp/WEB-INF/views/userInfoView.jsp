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
                <th>Dish Name</th>
                <th>Dish Description</th>
                <th>Dish Type</th>
                <th>Dish Price</th>
                <th>Order Quantity</th>
                <th>Total Cost</th>
                <th>Order Status</th>
            </tr>
            <form method="POST" action="${pageContext.request.contextPath}/userInfo">
                <c:forEach items="${orderedDishList}" var="user" >
                    <tr>
                        <td>${user.dishName}</td>
                        <td>${user.dishDescription}</td>
                        <td>${user.dishType}</td>
                        <td>${user.dishPrice}</td>
                        <td>${user.orderQuantity}</td>
                        <td>${user.orderTotalCost}</td>
                        <td>${user.orderStatus}</td>
                    </tr>
                </c:forEach>
            <tr>
                <td>
                    <c:if test="${not empty orderTotalCost}">
                        <p>Total Cost = ${orderTotalCost}</p>
                    </c:if>
                </td>
                <td>
                    <c:if test="${pay == true}">
                        <input type="submit" value="PAY">
                    </c:if>
                </td>
            </tr>
            </form>
        </table>

    </c:if>

    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
</html>