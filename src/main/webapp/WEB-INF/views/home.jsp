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
		
		<c:choose>
		<c:when test="${username ==  ''}"> <!-- If the user is not validated -->
			<p>
				DrexelEXP is a bla bla bla.
				If you want more information, try the following steps
				<ul>
					<li>Register above for more features!</li>
				</ul>
			</p>
		</c:when>
		<c:otherwise>
			<div id ="main-table">
				<table>
					<tr>
						<td width="350"><h2><a href="/drexelexp/professor">Professors</a></h2></td>
						<td width="350"><h2><a href="/drexelexp/course">Courses</a></h2></td>
					</tr>
					<tr>
						<td><h3>Search for a professor: </h3> <%@ include file="/WEB-INF/views/professor/searchbar.jsp" %></td>
						<td><h3>Search for a course:</h3><%@ include file="/WEB-INF/views/course/searchbar.jsp" %></td>
					</tr>
					<tr>
						
						<td><h2>Recent professor reviews:</h2></td>
						<td><h2>Recent course reviews: </h2></td>
					</tr>
				</table>
			</div>
		</c:otherwise>
	</c:choose>
		</div>
	</div>

</body>
</html>
