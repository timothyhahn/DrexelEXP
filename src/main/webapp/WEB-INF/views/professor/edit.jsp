<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 
<h1> ${professor.name }</h1><p>
 <form:form method="post" action="">

	<table>
	<tr>
		<td><form:label path="name">Name</form:label></td>
		<td><form:input path="name" value="${professor.name }" /></td>
	</tr>

	<tr>
		<td colspan="2">
			<input type="submit" value="Update"/>
		</td>
	</tr>
</table>	
	
</form:form>
</body>
</html>