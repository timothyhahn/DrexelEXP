<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>DrexelEXP - Login</title>
	<script src="<c:url value="/resources/js/jquery-1.8.1.js" />"></script>
		<script src="<c:url value="/resources/js/drexelexp.js" />"></script>
		<link href="<c:url value="/resources/css/drexelexp.css" />" rel="stylesheet" type="text/css" media="screen" />

</head>
<body onload='document.f.j_username.focus();'>
	 <div class="header">
		<%@ include file="/WEB-INF/views/header.jsp" %>
	</div>
	<div id="page">
		<div id ="text">
	<h3>Login with Username and Password</h3>
	<c:if test="${not empty error}">
	<hr />
		<div class="error">
			Your login was unsuccessful. Please try again.<br />
			Reason: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			
		</div>
		<hr />
	</c:if>
 
	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>
 
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Submit" /><input name="reset" type="reset" />
				</td>
			</tr>
		</table>
 
	</form>
		</div>
	</div>
 
</body>
</html>