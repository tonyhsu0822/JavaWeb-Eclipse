<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="example.shawn.q2.Cart"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>cart</title>
</head>
<%
	Cart cart = (Cart)session.getAttribute("cart");
%>
<body>
	<h1>已採購<%= cart.getTotal() %>本書籍</h1>
	<table border="1">
		<tr>
			<td><img src="images/javase9.jpg" height="200" width="200" alt="Java SE 9"></td>
			<td align="center" width="150">共 <%= cart.getJavase9() %> 本</td>
		</tr>
		<tr>
			<td><img src="images/jquery.jpg" height="200" width="200" alt="JQuery"></td>
			<td align="center">共 <%= cart.getJquery() %> 本</td>
		</tr>
		<tr>
			<td><img src="images/python35.jpg" height="200" width="200" alt="Python3.5"></td>
			<td align="center">共 <%= cart.getPython35() %> 本</td>
		</tr>
	</table>
	<br>
	<a href="shopping.jsp">繼續採購</a>
</body>
</html>