<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Edit Product</title>
   </head>
   <body>

      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>

      <h3>Edit Product</h3>

      <p style="color: red;">${errorString}</p>

      <c:if test="${not empty dish}">
         <form method="POST" action="${pageContext.request.contextPath}/editDish">
            <input type="hidden" name="id" value="${dish.id}" />
            <table border="0">
                <td>Name</td>
                  <td><input type="text" name="name" value="${dish.name}" /></td>
               </tr>
               <tr>
                  <td>Description</td>
                  <td><input type="text" name="description" value="${dish.description}" /></td>
               </tr>
               <tr>
                   <td>Dish Type</td>
                   <td><select name="dishType" size="3" multiple>
                        <option selected value="null">--select dish type--</option>
                        <option value="Soups">Soups</option>
                        <option value="Hot Dishes">Hot Dishes</option>
                        <option value="Cold Dishes">Cold Dishes</option>
                        <option value="Drinks">Drinks</option>
                       </select>
                   </td>
               </tr>
               <tr>
                    <td>Price</td>
                     <td><input type="text" name="dishPrice" value="${dish.dishPrice}" /></td>
                </tr>
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Submit" />
                      <a href="${pageContext.request.contextPath}/admin">Cancel</a>
                  </td>
               </tr>
            </table>
         </form>
      </c:if>

      <jsp:include page="_footer.jsp"></jsp:include>

   </body>
</html>