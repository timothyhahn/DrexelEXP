<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>DrexelEXP - ${professor.name }</title>
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
			<h1><strong>${professor.name}</strong></h1>
			<hr />
			<h3>Courses Taught</h3>
			<table>
				<tr><td width="50">Code</td><td width="">Name</td></tr>
				<c:forEach items="${courses}" var="course">
				
					<tr>
						<td id="listItem"><a href="<c:url value="/course/show/${course.id}" />">${course.subject.code} ${course.number}</a></td>
						<td id="listItem"><a href="<c:url value="/course/show/${course.id}" />">${course.name}</a></td>
					</tr>
				
				</c:forEach>
			</table>
			<hr />
				<form:form method="post" action="${professor.id}" commandName="newReview">
			
				<table>
				
				<tr>
					<td><form:label path="course">Course</form:label></td>
					<td>
						<form:select path="course" items="${professor.courses}"/>
					</td>
				</tr>				
				
				<tr>
					<td><form:label path="rating">Rating</form:label></td>
					<td><form:input path="rating" /></td>
				</tr>
				<tr>
					<td><form:label path="">Text</form:label></td>
					<td rowspan="5"><form:input type="textarea" rows="3"  path="content" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="Create"/>
					</td>
				</tr>
			</table>	
				
			</form:form>
			<hr />
			<table>
			<tr>
			<td width="50">Rating</td>
			<td width="150">Text</td>
			</tr>
			<c:forEach items="${reviews}" var="review">
			<tr>
			
			<td><c:out value="${review.rating}" /></td>
			<td><c:out value="${review.content}" /></td>
			
			</tr>
			</c:forEach>
			
			</table>
			
		 </div>
		 
	</div>
</body>
</html>