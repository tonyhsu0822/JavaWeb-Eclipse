<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyGossip</title>
</head>
<body>
	
<div style="width:300px; float:left">
	<img src="images/血小板.jpg" alt="血小板.jpg" height="200" width="200"/>
	
	<ul style="color:red">
		<c:forEach var="error" items="${errorList}">
			<li>${error}</li>
		</c:forEach>
	</ul>
	
	<form method="post" action="Login">
		<table style="background-color:lightgray">
			<tr>
				<td colspan="2">會員登入</td>
			</tr>
			<tr>
				<td>名稱:</td>
				<td><input type="text" name="username" value="${param.username}"></td>
			</tr>
			<tr>
				<td>密碼:</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" name="login" value="登入"></td>
			</tr>
			<tr>
				<td><a href="forgot.html">忘記密碼?</a></td>
				<td align="right"><a href="Register">會員註冊</a></td>
			</tr>
		</table>
	</form>
</div>
	
	<h1>Gossip...XD</h1>
	<ul>
		<li>談天說地不奇怪</li>
		<li>分享訊息也可以</li>
		<li>隨意寫寫表心情</li>
	</ul>
	
	<table border='0'>
    	<thead>
	        <tr><th><hr></th></tr>
	    </thead>
	    <tbody>
	    
	    <c:forEach var="message" items="${requestScope.latest}">
	    	<tr>
	    		<td style='vertical-align: top;'>
	    			${message.username}<br>
	    			${message.content}<br>
	    			${message.localDateTime}<br>
	    			<hr>
	    		</td>
	    	</tr>
	    </c:forEach>
	    
	    </tbody>
	</table>
	
	
</body>
</html>