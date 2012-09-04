<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			<h3>Courses Taught</h3>
			<table>
				<tr><td width="50">Code</td><td width="">Name</td></tr>
				<c:forEach items="${professor.courses}" var="course">
				
					<tr>
						<td id="listItem">${course.subject.code} ${course.number}</td>
						<td id="listItem">${course.name}</td>
					</tr>
				
				</c:forEach>
			</table>
			<hr />
			
			<c:if test="${('' ne username)}">
				<form:form method="post" action="${professor.id}" commandName="newReview">
					<table>
					
					<tr>
						<td><form:label path="courseId">Course</form:label></td>
						<td>
							<form:select path="courseId">
								<c:forEach items="${professor.courses}" var="course">
									<form:option value="${course.id}" label="${course.subject.code} ${course.number} ${course.name}"/>								
								</c:forEach>
							</form:select>
						</td>
					</tr>				
					
					<tr>
						<td><form:label path="rating">Rating</form:label></td>
						<td>
							<form:select path="rating">
								<form:option value="${1.0}" label="1"/>
								<form:option value="${2.0}" label="2"/>
								<form:option value="${3.0}" label="3"/>
								<form:option value="${4.0}" label="4"/>
								<form:option value="${5.0}" label="5"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td><form:label path="text">Text</form:label></td>
						<td rowspan="5"><form:input type="textarea" rows="3"  path="text" /></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="Create"/>
						</td>
					</tr>
				</table>	
					
				</form:form>
				<hr />
			</c:if>
			
			<table>
			<tr>
			<td width="50">Rating</td>
			<td width="150">Text</td>
			</tr>
			<c:forEach items="${professor.reviews}" var="review">
			<tr>
			
			<td><c:out value="${review.ratingString}" /></td>
			<td><c:out value="${review.text}" /></td>
			
			</tr>
			</c:forEach>
			
			</table>
			
		 </div>
		 
	</div>
</body>
</html>