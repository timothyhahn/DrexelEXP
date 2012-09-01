<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Courses Ingest</title>
</head>
<body>
<h1>
	Courses Ingest
</h1>

<ul>
<c:forEach items="${courses}" var="course">
<li>
	<h4><c:out value="${course.getNumber()}"/> <c:out value="${course.getName()}"/></h4>
	<p><c:out value="${course.getDesc()}"/></p>
</li>
</c:forEach>
</ul>

</body>
</html>
