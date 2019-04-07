<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Dish List</title>
 </head>
 <body>

    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_adminMenu.jsp"></jsp:include>

    <h3>Order List</h3>

    <p style="color: red;">${errorString}</p>
    <c:if test="${not empty orderList}">
        <table border="1" cellpadding="5" cellspacing="1" >
            <tr>
                <th>Order Id</th>
                <th>Client Name</th>
                <th>Dish Name</th>
                <th>Dish Quantity</th>
                <th>Order Time</th>
                <th>Total Cost</th>
            </tr>
            <c:forEach items="${orderList}" var="order" >
            <tr>
                <td>${order.orderId}</td>
                <td>${order.orderUserName}</td>
                <td>${order.orderDishName}</td>
                <td>${order.orderQuantity}</td>
                <td>${order.orderDate}</td>
                <td>${order.orderTotalCost}</td>
            </tr>
            </c:forEach>
        </table>
    </c:if>

    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
</html>