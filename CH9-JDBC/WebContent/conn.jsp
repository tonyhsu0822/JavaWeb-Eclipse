<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="db" class="example.shawn.DbBean"/>
<%-- 
<jsp:setProperty name="db" property="jdbcUri" value="jdbc:h2:tcp://localhost/c:/javaweb/eclipse-workspace/CH9-JDBC/demo"/>
<jsp:setProperty name="db" property="username" value="shawn"/>
<jsp:setProperty name="db" property="password" value="12345678"/>
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${db.connectedOK }">
		Connected OK
	</c:when>
	<c:otherwise>
		Connected Failed
	</c:otherwise>
</c:choose>

</body>
</html>