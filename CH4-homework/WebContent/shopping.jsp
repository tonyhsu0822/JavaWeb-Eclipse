<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="example.shawn.q2.Cart" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>shopping</title>
</head>
<body>
	<table>
		<tr>
			<td><img src="images/cart.png" height="200" width="200" alt="購物車"></td>
			<td><a href="cart.jsp">已採購<%= ((Cart)session.getAttribute("cart")).getTotal() %>本書籍</a></td>
		</tr>
	</table>
	<br>
	<table>
		<tr>
			<td><img src="images/javase9.jpg" height="200" width="200" alt="Java SE 9"></td>
			<td><img src="images/jquery.jpg" height="200" width="200" alt="JQuery"></td>
			<td><img src="images/python35.jpg" height="200" width="200" alt="Python3.5"></td>
		</tr>
		<tr>
			<td align="center"><a href="Shopping?book=javase9">採購此書</a></td>
			<td align="center"><a href="Shopping?book=jquery">採購此書</a></td>
			<td align="center"><a href="Shopping?book=python35">採購此書</a></td>
		</tr>
	</table>
</body>
</html>