<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>DrexelEXP - Register</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
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
	<h3>DrexelEXP - Create User</h3>
		<p>
		Fill out the form below to create a DrexelEXP user. Please use a <strong><em>valid</em></strong> university e-mail address as it will be used to determine your university.
		</p>
		<c:if test="${not empty errorMessage}">
		<hr />
			<div class="error">
				${errorMessage}
			</div>
		<hr />
		</c:if>
		<form:form method="post" action="create_user">
		
			<table>
			<tr>
				<td><form:label path="email">E-mail Address: </form:label></td>
				<td><form:input path="email" /></td> 
			</tr>
			<tr>
				<td><form:label path="password">Password: </form:label></td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td><form:label path="confPassword">Confirm Password: </form:label></td>
				<td><form:password path="confPassword" /></td>
			</tr>
			<tr>
				<td><form:label path="name">Name (Optional): </form:label></td>
				<td><form:input path="name" /></td>
			</tr>
		
			<tr>
				<td colspan="2">
					<input type="submit" value="Create"/>
				</td>
			</tr>
		</table>	
			
		</form:form>
		</div>
	</div>
</body>
</html>