<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="post" action="<c:url value="/course/search"/>" commandName="courseQuery">
	<table>
	<tr>
	
		<td><form:label path="query">Course:</form:label></td>
		<td><form:input path="query" /></td>
		<td colspan="2">
			<input type="submit" value="Search"/>
		</td>
	</tr>

</table>	
	
</form:form>