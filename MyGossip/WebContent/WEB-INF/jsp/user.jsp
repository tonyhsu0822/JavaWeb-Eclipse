<%@page import="java.time.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Gossip</title>
	<link rel="stylesheet" href="../css/member.css" type="text/css">
</head>
<body>
	<div class='leftPanel'>
		<img src='../images/血小板.jpg' alt='血小板' height="200" width="200"/><br><br>
		${requestScope.username} 的網誌
	</div>
	
	<table border="0">
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



