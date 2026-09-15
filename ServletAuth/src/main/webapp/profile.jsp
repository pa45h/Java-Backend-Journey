<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>

<%
	String password = "";

	Cookie [] cookies = request.getCookies();
	for(Cookie c : cookies){
		if(c.getName().equals("password")){
			password = c.getValue(); 
			break;
		}
	}
%>

<h1>Hello, ${sessionScope.username.toUpperCase()}</h1>
<h1>Pass : <%= password %></h1>

<form action="logout" method="post">
    <a href="login.html" style="display:inline-block;padding:8px 16px;background:#007bff;color:#fff;text-decoration:none;border-radius:4px;">
    LogIn</a>

    <button type="submit" style="display:inline-block;padding:8px 16px;background:#007bff;color:#fff;text-decoration:none;border-radius:4px;" >Logout</button>
</form>

</body>
</html>
