<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>DrexelEXP - ${course.name }</title>
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
			<h1><span class="rating">${course.ratingString}</span> <strong>${course.name}</strong></h1>
			<p>${course.description}</p>
			<hr />
			<h3>Professors Teaching</h3>
			<table>
				<tr><td width="100">Name</td></tr>
				<c:forEach items="${course.professors}" var="professor">
					<tr>
						<td id="listItem">
							<a href="<c:url value="/professor/show/${professor.id}"/>">
								${professor.name}
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<hr />
			
			<c:if test="${('' ne username)}">
				<form:form method="post" action="${course.id}" commandName="newReview">
					<table>
					
					<tr>
						<td><form:label path="professorId">Professor</form:label></td>
						<td>
							<form:select path="professorId">
								<c:forEach items="${course.professors}" var="professor">
									<form:option value="${professor.id}" label="${professor.name}"/>								
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
						<td><form:label path="content">Text</form:label></td>
						<td><form:textarea rows="5" cols="30"  path="content" /></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="Submit Review"/>
						</td>
					</tr>
				</table>	
					
				</form:form>
				<hr />
			</c:if>
						
			<c:choose>
				<c:when test="${not empty course.reviews}">
					<table>
						<tr>
							<td width="50">Rating</td>
							<td width="400">Text</td>
							<td width="200">Professor</td>
						</tr>
						<c:forEach items="${course.reviews}" var="review">
								<tr>
									<td><c:out value="${review.ratingString}" /></td>
									<td><c:out value="${review.content}" /></td>
									<td>${review.professor.name}</td>
									<td>
										<c:if test="${user != null  && user.moderator }">
											<a href="<c:url value="/review/delete/${review.id}"/>">
												DELETE
											</a>
										</c:if>
									</td>
								</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<p>No reviews have been submitted for this Course</p>
				</c:otherwise>
			</c:choose>
		 </div>
	</div>
</body>
</html>