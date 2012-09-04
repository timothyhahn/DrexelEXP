<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>DrexelEXP - Professors</title>
	<script src="<c:url value="/resources/js/jquery-1.8.1.js" />"></script>
	<script src="<c:url value="/resources/js/drexelexp.js" />"></script>
	<link href="<c:url value="/resources/css/drexelexp.css" />" rel="stylesheet" type="text/css" media="screen" />
<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro' rel='stylesheet' type='text/css'>
</head>
<body>
	 <div class="header">
		<%@ include file="/WEB-INF/views/header.jsp" %>
	</div>
	<div id="page">
		<div id ="text">
			<a href="<c:url value="/subject/search"/>">Search</a>
			<h1>Subjects</h1>
			<table>
			<tr>
			<td width="50">Id</td>
			<td width="50">Code</td>
			<td width="100">Name</td>
			</tr>
			<c:forEach items="${subjects}" var="subject">
			<tr>
			
			<td><c:out value="${subject.getId()}" /></td>
			<td><c:out value="${subject.getCode()}" /></td>
			<td><c:out value="${subject.getName()}" /></td>
			
			</tr>
			</c:forEach>
			</table>
		</div>
	</div>
 
</body>
</html>