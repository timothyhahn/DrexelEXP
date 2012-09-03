<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
	<div id="logo">DrexelEXP</div>
	 <div id="option">
	<c:choose>
		<c:when test="${username ==  ''}"><a href="<c:url value="/login" />">Login</a><div id="login">hi</div> <a href="<c:url value="/register" />">Register</a></c:when>
		<c:otherwise>${username} <a href="<c:url value="j_spring_security_logout" />">Logout</a></c:otherwise>
	</c:choose>
	
	 	
	 </div>