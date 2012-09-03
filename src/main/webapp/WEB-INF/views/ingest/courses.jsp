<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>DrexelEXP - Courses Ingest</title>
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
<h1>
	Courses Ingest
</h1>

<a href="<c:url value="/ingest/professors/${college.getCode()}/${subject.getCode()}" />">
	Ingest Professors
</a>

<ul>
<c:forEach items="${courses}" var="course">
<li>
	<h4><c:out value="${course.getNumber()}"/> <c:out value="${course.getName()}"/></h4>
	<p><c:out value="${course.getDesc()}"/></p>
</li>
</c:forEach>
</ul>
	</div>
	</div>
</body>
</html>
