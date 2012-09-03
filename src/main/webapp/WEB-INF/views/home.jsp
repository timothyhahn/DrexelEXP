<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<title>DrexelEXP</title>
	<script src="<c:url value="/resources/js/jquery-1.8.1.js" />"></script>
	<script src="<c:url value="/resources/js/drexelexp.js" />"></script>
	<link href="<c:url value="/resources/css/drexelexp.css" />" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<div id="navbar">DrexelEXP <div id="option">Login Register</div></div>
	<div id="page">
		<p>
			DrexelEXP is a bla bla bla. If you want more information, try the following steps
			<ul>
				<li>If you don't want to register, make sure to select your university above</li>
				<li>Search for a Professor below</li>
				<li>Search for a Course below</li>
				<li>Or register above for more features!</li>
			</ul>
		</p>
		<div id ="profSearch" class="search"><input type="text" name="fname" /><br /></div>
		<div id ="courseSearch" class="search"><input type="text" name="fname" /><br /></div>
		</div>

</body>
</html>
