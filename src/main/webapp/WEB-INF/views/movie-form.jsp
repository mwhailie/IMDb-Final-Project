<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add a New Movie</title>
</head>
<body>

<h2>Add a New Movie</h2>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<form:form action="${contextPath}/movie/add" method="post"	commandName="movie">
		<table>
			<tr>
				<td>Genre:</td>
				<td><form:select path="genres" items="${genres}" multiple="true" required="required" /></td>
			</tr>
			<tr>
				<td>Movie Title:</td>
				<td><form:input type="text" path="title" size="30" required="required"/></td>
			</tr>
			<tr>
				<td>Year:</td>
				<td><form:input type="text" path="year" size="30" required="required"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add Movie" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>