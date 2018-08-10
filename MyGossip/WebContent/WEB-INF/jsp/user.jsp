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
		${requestScope.username} 的網誌<br>
		<a href="/MyGossip">回首頁</a>
	</div>
	
	<table border="0">
    	<thead>
	        <tr><th><hr></th></tr>
	    </thead>
	    <tbody>
	    
    <c:choose>
    	<c:when test="${requestScope.errorList != null}">
    		<ul>
	   		<c:forEach var="error" items="${requestScope.errorList }">
	   			<li>${error}</li>
	   		</c:forEach>
	   		</ul>
    	</c:when>
	   	<c:otherwise>
	    	<c:forEach var="message" items="${requestScope.messages}">
	    	<tr>
	    		<td style='vertical-align: top;'>
	    			${message.username}<br>
	    			${message.content}<br>
	    			${message.localDateTime}
	    			<hr>
	    		</td>
	    	</tr>
	    	</c:forEach>
	    </c:otherwise>
	</c:choose>
		
	    </tbody>
	</table>
</body>
</html>



