<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>查詢</title>
	<style>
	table {
		width: 400px;
	}
	table, td {
    	border: 1px solid black;
	}
	</style>
</head>
<body>
	<form method="POST" action="query">
		<table>
			<tr>
				<td width="150">使用者姓名:</td>
				<td><input type="text" name="username" value="${requestScope.username }" size="15"></td>
			</tr>
			<tr>
				<td>使用者電話:</td>
				<td><input type="text" name="phone" value="${requestScope.phone }" size="15"></td>
			</tr>
			<tr>
				<td>性別:</td>
				<td>
					<select name="sex">
						<option value="M" ${requestScope.sex == 'M'? 'selected':''}>男</option>
						<option value="F" ${requestScope.sex == 'F'? 'selected':''}>女</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="查詢"></td>
			</tr>
		</table>
	</form>
	<br>
	
<c:if test="${requestScope.showResult == true}">	
	<table>
		<tr>
			<td>編號</td>
			<td>姓名</td>
			<td>性別</td>
			<td>電話</td>
			<td>地址</td>
		</tr>
	<c:forEach var="user" items="${requestScope.matchedUsers }">
		<tr>
			<td>${user.id }</td>
			<td>${user.username }</td>
			<td>${user.sex }</td>
			<td>${user.phone }</td>
			<td>${user.address }</td>
		</tr>
	</c:forEach>
	</table>
</c:if>

<c:if test="${requestScope.errorOccured == true}">
	<h3 style="color:red">An error occured</h3>
	<h3 style="color:red">${requestScope.errorReason}</h3>
	<%= exception.getClass().getName() %>
</c:if>

</body>
</html>