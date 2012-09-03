<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>DrexelEXP - Ingest</title>
<script src="<c:url value="/resources/js/jquery-1.8.1.js" />"></script>
<script src="<c:url value="/resources/js/drexelexp.js" />"></script>
<link href="<c:url value="/resources/css/drexelexp.css" />"	rel="stylesheet" type="text/css" media="screen" />
<style>
.subject{
	width: 1em;
	height: 1em;
	
    -webkit-border-radius: 50%;
    -moz-border-radius: 50%;
    border-radius: 50%;
}
.subject.notstarted{
	background-color: red;
}
.subject.started{
	background-color: yellow;
}
.subject.finished{
	background-color: green;
}
</style>
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
									<span><c:out value="${subject.getName()}" /></span>
									<div class="subject"
										data-college="<c:out value="${college.getCode()}"/>"
										data-subject="<c:out value="${subject.getCode()}"/>">
									</div>
									<a href="<c:url value="/ingest/${college.getCode()}/${subject.getCode()}" />">
										<c:url value="/ingest/${college.getCode()}/${subject.getCode()}" />
									</a>
								</li>
							</c:forEach>
						</ul>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$('.subject').each(function(index,value){
				$(value).addClass("notstarted");
				console.log(value);
			});
		});
	</script>
</body>
</html>
