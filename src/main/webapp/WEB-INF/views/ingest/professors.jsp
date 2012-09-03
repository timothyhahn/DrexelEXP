<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>DrexelEXP - Courses Ingest</title>
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
<h1>
	Professors Ingest
</h1>

<ul>
<c:forEach items="${sections}" var="section">
<li>
	<span><c:out value="${section.getSubjectCode()}"/> <c:out value="${section.getCourseNumber()}"/></span>
	<p><c:out value="${section.getProfessorName()}"/></p>
</li>
</c:forEach>
</ul>
	</div>
	</div>
</body>
</html>
