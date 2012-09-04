<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<title>DrexelEXP</title>
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
		
		<c:if test="${username eq  ''}"> <!-- If the user is not validated -->
			<p class="visitor-prompt">
				DrexelEXP is a user driven website to rate, review, and research your professors and courses at Drexel before you take them.
				If you would like to contribute your own ratings and reviews, click the link above to register.
			</p>
		</c:if>
		<div id ="main-table">
			<table>
				<tr>
					<td width="350"><h2><a href="<c:url value="/professor/" />">Professors</a></h2></td>
					<td width="350"><h2><a href="<c:url value="/course/ "/>" >Courses</a></h2></td>
				</tr>
				<tr>
					<td><h3>Search for a professor: </h3> <%@ include file="/WEB-INF/views/professor/searchbar.jsp" %></td>
					<td><h3>Search for a course:</h3><%@ include file="/WEB-INF/views/course/searchbar.jsp" %></td>
				</tr>
				<tr>
					<td><h2>Recent professor reviews:</h2></td>
					<td><h2>Recent course reviews: </h2></td>
				</tr>
				<tr>
					<td>
						<c:forEach items="${reviews}" var="review">
							<c:out value="${review.ratingString}"/>
							<a href="<c:url value="/professor/show/${review.professor.id }"/>">
								<c:out value="${review.professor.name}"/>
							</a>
							<hr/>
						</c:forEach>
					</td>
					<td>
						<c:forEach items="${reviews}" var="review">
							<c:out value="${review.ratingString}"/>
							<a href="<c:url value="/course/show/${review.course.id }"/>">
								<c:out value="${review.course.name}"/>
							</a>
							<hr/>
						</c:forEach>
					</td>
				</tr>
			</table>
		</div>
		</div>
	</div>

</body>
</html>
