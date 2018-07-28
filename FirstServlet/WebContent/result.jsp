<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	boolean result = false;
	
	String show(){
		if(result){
			return "Login success!";
		}
		else {
			return "Login failed.";
		}
	}
%>
<%
	String account = request.getParameter("account");
	String password = request.getParameter("password");
	
	if(account.equals("caterpillar") && password.equals("123456")){
		result = true;
	}
	else {
		result = false;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=show()%></title>
</head>
<body>
	<h1><%=show()%></h1>
	<%
		if(!result){
			out.print("<a href=practice2>back</a>");
		}
	%>
</body>
</html>