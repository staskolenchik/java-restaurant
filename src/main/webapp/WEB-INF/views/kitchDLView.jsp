<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Kitchen</title>
 </head>
 <body>

    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_kitchenMenu.jsp"></jsp:include>

    <h3>Dish List</h3>

        <table border="1" cellpadding="5" cellspacing="1" >
           <tr>
              <th>Dish Name</th>
              <th>Dish Description</th>
              <th>Dish Type</th>
              <th>Order Quantity</th>
              <th>Order Status</th>
              <th>Send Order to Administrator</th>
           </tr>
           <c:forEach items="${kitchenDishList}" var="kitchen" >
              <tr>
                 <td>${kitchen.dishName}</td>
                 <td>${kitchen.dishDescription}</td>
                 <td>${kitchen.dishType}</td>
                 <td>${kitchen.orderQuantity}</td>
                 <td>${kitchen.orderStatus}</td>
                 <td>
                    <a href="kitchen?id=${kitchen.orderId}">Dish is Ready</a>
                 </td>
              </tr>
           </c:forEach>
        </table>

    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
</html>