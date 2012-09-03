<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>YAJSM - Professors</title>
	<script src="<c:url value="/resources/js/jquery-1.8.1.js" />"></script>
		<script src="<c:url value="/resources/js/drexelexp.js" />"></script>
		<link href="<c:url value="/resources/css/drexelexp.css" />" rel="stylesheet" type="text/css" media="screen" />

</head>
<body>
	 <div class="header">
		<%@ include file="/WEB-INF/views/header.jsp" %>
	</div>
	<div id="page">
		<div id ="text">
			<h1>Professors</h1>
			<table>
			<tr>
			<td width="50">Id</td>
			<td width="150">Name</td>
			</tr>
			<c:forEach items="${professors}" var="professor">
			<tr>
			
			<td><c:out value="${professor.getId()}" /></td>
			<td><a href="show/${professor.getId()}"><c:out value="${professor.getName()}" /></a></td>
			
			</tr>
			</c:forEach>
			</table>
		</div>
	</div>
 
</body>
</html>