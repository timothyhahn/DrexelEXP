<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Register page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
	<h3>Create with Username and Password (Custom Page)</h3>

<form:form method="post" action="create_user">

	<table>
	<tr>
		<td><form:label path="email">Email</form:label></td>
		<td><form:input path="email" /></td> 
	</tr>
	<tr>
		<td><form:label path="password">Password</form:label></td>
		<td><form:password path="password" /></td>
	</tr>
	<tr>
		<td><form:label path="name">Name</form:label></td>
		<td><form:input path="name" /></td>
	</tr>

	<tr>
		<td colspan="2">
			<input type="submit" value="Create"/>
		</td>
	</tr>
</table>	
	
</form:form>
</body>
</html>