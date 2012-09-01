<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Professor List</title>
</head>
<body>
 
<h1>Select a Professor to Delete</h1>
<table>
<tr>
<td width="50">Id</td>
<td width="150">Name</td>
</tr>
<c:forEach items="${professors}" var="professor">
<tr>
<td><a href ="delete/${professor.getId()} "><c:out value="${professor.getId()}" /></a></td>
<td><c:out value="${professor.getName()}" /></td>
</tr>
</c:forEach>
</table></body>
</html>