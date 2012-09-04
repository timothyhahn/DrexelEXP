<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>DrexelEXP - ${professor.getName() }</title>
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
			<h1><strong>${professor.getName() }</strong></h1>
			<hr />
			<table>
			<tr>
			<td width="50">Rating</td>
			<td width="150">Text</td>
			</tr>
			<c:forEach items="${reviews}" var="review">
			<tr>
			
			<td><c:out value="${review.getRating()}" /></td>
			<td><c:out value="${review.getText()}" /></td>
			
			</tr>
			</c:forEach>
			
			</table>
		 </div>
		 
	</div>
</body>
</html>