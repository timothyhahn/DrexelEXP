<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 
<h1>Courses</h1><table>
<tr>
<td width="50">Subject</td>
<td width="50">Number</td>
<td width="150">Name</td>

</tr>
<c:forEach items="${courses}" var="course">
<tr>
<td><c:out value="${course.getSubject().getCode()}" /></td>
<td><c:out value="${course.getNumber()}" /></td>
<td><c:out value="${course.getName()}" /></td>
</tr>  

</c:forEach>
</table></body>
</html>