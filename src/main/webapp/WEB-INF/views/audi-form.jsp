<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add A movie Schedule in</title>
<!--  jQuery -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<!-- Bootstrap Date-Picker Plugin -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/clockpicker/0.0.7/bootstrap-clockpicker.css" />

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
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/main.css"/>" />
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/clockpicker/0.0.7/bootstrap-clockpicker.min.js"></script>
<script type="text/javascript">
	$('.clockpicker').clockpicker();
</script>
<script>
	$(document).ready(
			function() {
				var date_input = $('input[name="date"]'); //our date input has the name "date"
				var container = $('.bootstrap-iso form').length > 0 ? $(
						'.bootstrap-iso form').parent() : "body";
				var options = {
					format : 'mm/dd/yyyy',
					container : container,
					todayHighlight : true,
					autoclose : true,
				};
				date_input.datepicker(options);
			})
</script>

</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div class="center-block">
		<h2>Add a New Schedule</h2>
		<form:form action="${contextPath}/schedule/add" method="post"
			commandName="schedule">
			<div class="form-group">
				<label for="auditorium">Auditorium:</label> <input type="text"
					size="30" disabled="true"
					value="${schedule.auditorium.auditoriumName}" name="auditorium"
					class="form-control" id="auditorium" />
			</div>
			<form:hidden path="inAuditorium"
				value="${schedule.auditorium.auditoriumID}" />

			<div class="form-group">
				<label for="movie">Movie:</label>
				<form:select path="onMovie" items="${movies}" required="required" 
					class="form-control" id="movie" />
				<font color="red"><form:errors path="onMovie" /></font>
			</div>
			<div class="form-group">
				<label for="date">Date:</label>
				<font color="red"><form:errors path="date" /></font>-->
				<input  size="30" required="required" placeholder="MM/DD/YYY" name="date" class="form-control" id="date" />
			</div>
			<div class="form-group">
				<label for="startTime">Time:</label>
				<font color="red"><form:errors path="date" /></font>-->
				<input  size="30" required="required" placeholder="hh:mm" name="startTime" class="form-control" id="startTime" />
			</div>
			<input type="submit" value="Create Schedule" class="btn btn-primary" />
		</form:form>
	</div>
</body>
</html>