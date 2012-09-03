<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>DrexelEXP - Ingest</title>
<script src="<c:url value="/resources/js/jquery-1.8.1.js" />"></script>
<script src="<c:url value="/resources/js/drexelexp.js" />"></script>
<link href="<c:url value="/resources/css/drexelexp.css" />"	rel="stylesheet" type="text/css" media="screen" />
<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro' rel='stylesheet' type='text/css'>
</head>
<body>
	<div class="header">
		<%@ include file="/WEB-INF/views/header.jsp"%>
	</div>
	<div id="page">
		<div id="text">
			<h1>Ingest</h1>

			<ul>
				<c:forEach items="${colleges}" var="college">
					<li>
						<h4><c:out value="${college.getName()}" /></h4>
						<ul>
							<c:forEach items="${college.getSubjects()}" var="subject">
								<li>
									<a href="<c:url value="/ingest/courses/${college.getCode()}/${subject.getCode()}" />">
										Ingest <c:out value="${subject.getName()}" />
									</a>
								</li>
							</c:forEach>
						</ul>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>
