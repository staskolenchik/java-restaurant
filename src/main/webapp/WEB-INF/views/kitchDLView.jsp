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
    <jsp:include page="_kitchenMenu.jsp"></jsp:include>

    <h3>Dish List</h3>

    <p style="color: red;">${errorString}</p>

    <form method="POST" action="${pageContext.request.contextPath}/kitchen">
        <table border="1" cellpadding="5" cellspacing="1" >
           <tr>
              <th>Name</th>
              <th>Description</th>
              <th>Dish Type</th>
              <th>Quantity</th>
              <th>Status</th>
              <th></th>
           </tr>
           <c:forEach items="${kitchenDishList}" var="kitchen" >
           <input type="hidden" name="id" value="${kitchen.kitchenId}" />
              <tr>
                 <td>${kitchen.kitchenName}</td>
                 <td>${kitchen.kitchenDescription}</td>
                 <td>${kitchen.kitchenType}</td>
                 <td>${kitchen.kitchenQuantity}</td>
                 <td>${kitchen.kitchenStatus}</td>
                 <td>
                    <input type="submit" value="Ready" />
                 </td>
              </tr>
           </c:forEach>
        </table>
    </form>

    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
</html>