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
    <c:if test="${not empty adminList}">
        <table border="1" cellpadding="5" cellspacing="1" >
            <tr>
                <th>Order Id</th>
                <th>Client Name</th>
                <th>Dish Name</th>
                <th>Dish Price</th>
                <th>Order Time</th>
                <th>Dish Quantity</th>
                <th>Total Cost</th>
                <th>Order Status</th>
                <th>Bill Order</th>
                <th>Cook Dish</th>
            </tr>
            <c:forEach items="${adminList}" var="admin" >
                <tr>
                    <td>${admin.orderId}</td>
                    <td>${admin.userAccountName}</td>
                    <td>${admin.dishName}</td>
                    <td>${admin.dishPrice}</td>
                    <td>${admin.orderDate}</td>
                    <td>${admin.orderQuantity}</td>
                    <td>${admin.orderTotalCost}</td>
                    <td>${admin.orderStatus}</td>
                    <td>
                    <c:if test="${admin.orderStatus == 'ready'}">
                        <a href="orders?id=${admin.orderId}&status=${admin.orderStatus}">Make a bill</a>
                    </c:if>
                    </td>
                    <td>
                    <c:if test="${admin.orderStatus == 'queueing up'}">
                        <a href="orders?id=${admin.orderId}&status=${admin.orderStatus}">Send to the kitchen</a>
                    </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
</html>