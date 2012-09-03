<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>DrexelEXP - Ingest</title>
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
	Ingest
</h1>

<ul>
<c:forEach items="${colleges}" var="college">
<li>
	<h4><c:out value="${college.getName()}"/></h4>
	<c:forEach items="${college.getSubjects()}" var="subject">
		<h5><c:out value="${subject.getName()}"/></h5>
		<div class="subject"
			data-college="<c:out value="${college.getCode()}"/>"
			data-subject="<c:out value="${subject.getCode()}"/>">
		</div>
		<a href="<c:url value="/ingest/${college.getCode()}/${subject.getCode()}" />">
			<c:url value="/ingest/${college.getCode()}/${subject.getCode()}" />
		</a>
	</c:forEach>
</li>
</c:forEach>
</ul>
</div>
	</div>
 
</body>
</html>
