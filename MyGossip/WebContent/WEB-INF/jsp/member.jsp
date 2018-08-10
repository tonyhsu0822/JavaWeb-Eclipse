<%@page import="java.time.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Gossip</title>
	<link rel="stylesheet" href="css/member.css" type="text/css">
</head>
<body>
	<div class='leftPanel'>
		<img src='images/血小板.jpg' alt='血小板' height="200" width="200"/><br><br>
		<a href="Logout">登出${sessionScope.logined }</a>
	</div>
	<form method='post' action='NewMessage'>
		分享新鮮事...<br>
		
    	<c:if test="${not empty param.content}">
			訊息要 140 字以內<br>
    	</c:if>
    	
        <textarea cols='60' rows='4' name='content'>${param.content}</textarea><br>
        <button type='submit'>送出</button>
	</form>
	<table border='0'>
    	<thead>
	        <tr><th><hr></th></tr>
	    </thead>
	    <tbody>
	    
	    <c:forEach var="message" items="${requestScope.messages}">
	    	<tr>
	    		<td style='vertical-align: top;'>
	    			${message.username}<br>
	    			${message.content}<br>
	    			${message.localDateTime}
	    			<form method="post" action="DelMessage">
	    				<input type="hidden" name="millis" value="${message.millis}">
	    				<button type="submit">刪除</button>
	    			</form>
	    		</td>
	    	</tr>
	    </c:forEach>
	    
	    </tbody>
	</table>
</body>
</html>



