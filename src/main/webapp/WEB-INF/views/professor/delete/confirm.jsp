<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 
<h1> ${professor.name }</h1><p>
 <form:form method="post" action="">

	<table>
	<tr>
		<td>Are you sure you want to delete ${professor.name }</td>
		<td></td>
	</tr>

	<tr>
		<td colspan="2">
			<input type="submit" value="Delete"/>
		</td>
	</tr>
</table>	
	
</form:form>
</body>
</html>