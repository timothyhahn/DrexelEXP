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
			
			<span id="autoingest">Click me to ingest everything</span>
			
			<ul>
				<c:forEach items="${colleges}" var="college">
					<li>
						<h4><c:out value="${college.getName()}" /></h4>
						<ul>
							<c:forEach items="${college.getSubjects()}" var="subject">
								<li>
									<div class="subject"
										data-college="<c:out value="${college.getCode()}"/>"
										data-subject="<c:out value="${subject.getCode()}"/>">
										<span><c:out value="${subject.getName()}" /></span>
									</div>
								</li>
							</c:forEach>
						</ul>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#autoingest").click(function(){
			alert("hi");
			$(".subject").each(function(index,value){
				var subject = $(value).attr('data-subject');
				var college = $(value).attr('data-college');
				
				var courseUrl = '<c:url value="/ingest/courses/" />'+college+'/'+subject;
				var professorUrl = '<c:url value="/ingest/professors/" />'+college+'/'+subject;
				
				$.get(courseUrl,function(){
					$.get(professorUrl);
				});
			});
		});
	});
</script>
</html>
