<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
      <meta charset="UTF-8">
      <title>Dish Order</title>
    </head>

    <body>

        <jsp:include page="_header.jsp"></jsp:include>
        <jsp:include page="_menu.jsp"></jsp:include>

        <h3>Dish Order</h3>

        <p style="color: red;">${errorString}</p>

        <c:if test="${not empty dish}">
             <form method="POST" action="${pageContext.request.contextPath}/dish-order">
                 <input type="hidden" name="id" value="${dish.id}" />
                 <input type="hidden" name="totalCost" value="${dish.dishPrice}" />
                 <table border="1" cellpadding="5" cellspacing="1">
                     <tr>
                         <th>Name</th>
                         <th>Description</th>
                         <th>Dish Type</th>
                         <th>Dish Price</th>
                         <th>Quantity</th>
                         <th>Total Cost</th>
                         <th></th>
                         <th></th>
                     </tr>
                     <tr>
                         <td>${dish.name}</td>
                         <td>${dish.description}</td>
                         <td>${dish.dishType}</td>
                         <td>${dish.dishPrice}</td>
                         <td>
                             <select name="quantity" size="1" multiple>
                                 <option selected value="1">1</option>
                             </select>
                         </td>
                         <td>${dish.dishPrice}</td>
                         <td><input type="submit" value"Order"/>
                         <td><a href="${pageContext.request.contextPath}/dishes">Cancel</a></td>
                 </table>
             </form>
        </c:if>

        <jsp:include page="_footer.jsp"></jsp:include>

    </body>
</html>