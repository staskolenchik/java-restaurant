<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
     <meta charset="UTF-8">
     <title>Home Page</title>
  </head>
  <body>

     <jsp:include page="_header.jsp"></jsp:include>
     <jsp:include page="_menu.jsp"></jsp:include>

      <h3>Online Cafe X</h3>

      Welcome to Online Cafe <br><br>
      <b>It includes the following functions:</b>
      <ul>
         <li>Login</li>
         <li>Storing user information in cookies</li>
         <li>Register new user</li>
         <li>Control access system, where customer can only watch dishes and admin
         have rights to CRUD functions</li>
         <li>administrator login: admin; password: admin</li>
         <a href="admin">admin page</a>
         <li>kitchen login: kitchen; password: kitchen</li>
         <a href="kitchen">kitchen page</a>
      </ul>

     <jsp:include page="_footer.jsp"></jsp:include>

  </body>
</html>