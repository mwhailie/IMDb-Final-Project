<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<style>
div.center-block {
	padding: 100px 200px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Movie</title>
</head>
<body>
<h2>Update Movie</h2>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<div class="center-block">

		<form:form  method="post" action="${contextPath}/movie/update"  commandName="movie">
			<form:hidden path="movieID"/>
			<div class="form-group">
				<label for="title">Movie Title:</label>
				<form:input type="text" path="title" size="30" required="required"
					class="form-control" id="title" />
			</div>
			<div class="form-group">
				<label for="year">Year:</label>
				<form:input type="text" path="year" size="30" required="required"
					class="form-control" id="year" />
			</div>
			<div class="form-group">
				<label for="director">Director:</label>
				<form:input type="text" path="director" size="30" required="required"
					class="form-control" id="director" />
			</div>
			<div class="form-group">
				<label for="actor">Actor:</label>
				<form:input type="text" path="actor" size="30" required="required"
					class="form-control" id="actor" />
			</div>
			<div class="form-group">
				<label for="actress">Actress:</label>
				<form:input type="text" path="actress" size="30" required="required"
					class="form-control" id="actress" />
			</div>
			<div class="form-group">
				<label for="time">Time:</label>
				<form:input type="text" path="time" size="30" required="required"
					class="form-control" id="time" />
			</div>
			<div class="form-group">
				<label for="filename">Filename:</label>
				<form:input type="text" path="filename" name="filename" size="30"
					required="required" class="form-control" id="filename" />
			</div>
			
			<input type="submit" class="btn btn-primary" value="Update Movie" />
		</form:form>
	</div>
</body>
</html>