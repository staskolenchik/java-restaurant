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

    <h3>Dish List</h3>

    <p style="color: red;">${errorString}</p>

    <a href="createDish" >Create Dish</a>

    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Name</th>
          <th>Description</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
       <c:forEach items="${dishList}" var="dish" >
          <tr>
             <td>${dish.name}</td>
             <td>${dish.description}</td>
             <td>
                <a href="editDish?id=${dish.id}">Edit</a>
             </td>
             <td>
                <a href="deleteDish?id=${dish.id}">Delete</a>
             </td>
          </tr>
       </c:forEach>
    </table>



    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
</html>