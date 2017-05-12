<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<style>
div.register{
	padding: 100px 200px;
}
</style>
<title>Register New User</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}">Go Back</a><br/>
	<div class="register">
	<h2>Register a New User</h2>
	<form:form action="${contextPath}/user/register" commandName="user"	method="post">
		 <div class="form-group">
				 <label for="firstname">First Name:</label>
				<form:input path="firstName" size="30" required="required"  class="form-control" id="firstname" placeholder="firstname" />
					<font color="red"><form:errors path="firstName" /></font>
		</div>
 		<div class="form-group">
				<label for="lastname">Last Name:</label>
				<form:input path="lastName" size="30" required="required" class="form-control" id="lastname" placeholder="lastname"/>
					<font color="red"><form:errors path="lastName" /></font>
		</div>
 		<div class="form-group">
				<label for="username">User Name:</label>
				<form:input path="username" size="30" required="required" class="form-control" id="username" placeholder="username" />
					<font color="red"><form:errors path="username" /></font>
		</div>
		<div class="form-group">
				<label for="password">Password:</label>
				<form:password path="password" size="30" required="required" class="form-control" id="password" placeholder="password" /> 
				<font color="red"><form:errors path="password" /></font>
		</div>
 		<div class="form-group">
				<label for="email">Email:</label>
				<form:input path="email.emailAddress" size="30"
						type="email" required="required" class="form-control" id="email" placeholder="email"/> 
						<font color="red"><form:errors path="email.emailAddress" /></font>
		</div>
		<input  class="btn btn-primary" type="submit" value="Register User" />
	</form:form>
	</div>


	
</body>
</html>