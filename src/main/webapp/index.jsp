<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Login</title>
</head>
<body>
<form method="POST" action="login">
<p>User Name: <input type="text" name="userName"></p>
<p>Password: <input type="text" name="password"></p>
<%
if(request.getAttribute("error")!=null){
out.print("<p>Invalid!</p>");
}
%>
<input type="submit" value="Login">
</form>
<p>Dont have an account?<a href="register.jsp">Register</a></p>
</body>
</html>
