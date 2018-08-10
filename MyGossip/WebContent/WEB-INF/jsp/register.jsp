<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<h1>會員申請</h1>
	
	<ul style="color:red">
		<c:forEach var="error" items="${errorList}">
			<li>${error}</li>
		</c:forEach>
	</ul>
	
	<form method="post" action="Register">
		<table style="background-color:lightgray">
			<tr>
				<td>Email信箱</td>
				<td><input type="email" name="email" value="${param.email}" maxlength="100"></td>
			</tr>
			<tr>
				<td>名稱(最大16字元):</td>
				<td><input type="text" name="username" value="${param.username}" maxlength="16"></td>
			</tr>
			<tr>
				<td>密碼(8~16字元)</td>
				<td><input type="password" name="password" maxlength="16"></td>
			</tr>
			<tr>
				<td>確認密碼:</td>
				<td><input type="password" name="passwordconfirm" maxlength="16"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" name="register" value="註冊"></td>
			</tr>
		</table>
	</form>
	
	<a href="/MyGossip">回首頁</a>
</body>
</html>