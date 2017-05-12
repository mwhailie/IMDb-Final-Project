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
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${contextPath}">IMDb</a>

		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<!--<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="${contextPath }/movie/list.htm">Movie List<span
						class="sr-only">(current)</span></a></li>
				<li><a href="${contextPath }/theater/list.htm">Theater List</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">I need... <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Recent</a></li>
						<li><a href="#">Rank</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Ticket</a></li>
					</ul></li>
			</ul>
			<form class="navbar-form navbar-left" action="movie/search.htm" method="post">
				<div class="form-group">
					<input name="title" type="text" class="form-control"
						placeholder="Find Movies...">
				</div>
				<button type="submit" class="btn btn-default">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
				</button>
			</form>-->
			<ul class="nav navbar-nav navbar-right">
				<li><a class="cd-signin" >Welcome, admin</a></li>
				<li><a href="${contextPath}/admin.htm" class="cd-signup" >Back to admin home</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<div class="center-block">
		<h2>Add a New Schedule</h2>
		<form:form action="${contextPath}/schedule/add" method="post" 
			commandName="schedule">
			<div class="form-group">
				<label for="auditorium">Auditorium:</label> 
				<form:select path="inAuditorium" class="form-control" id="schedule">
					<c:forEach items="${auditoriums}" var="auditorium">
						<option value="${auditorium.auditoriumID}" >${auditorium}</option>
					</c:forEach>
				</form:select>
				<!--<form:select path="inAuditorium" items="${auditoriums}" required="required" 
					class="form-control" id="auditorium"  />
				<font color="red"><form:errors path="inAuditorium" /></font>-->
			</div>
			<!--<form:hidden path="inAuditorium"
				value="${schedule.auditorium.auditoriumID}" />-->

			<div class="form-group">
				<label for="movie">Movie:</label>
				<form:select path="onMovie" items="${movies}" required="required" 
					class="form-control" id="movie" />
				<font color="red"><form:errors path="onMovie" /></font>
			</div>
			<div class="form-group">
				<label for="date">Date:</label>
				<font color="red"><form:errors path="date" /></font>
				<input  size="30" required="required" placeholder="MM/DD/YYY" name="date" class="form-control" id="date" />
			</div>
			<div class="form-group">
				<label for="startTime">Time:</label>
				<font color="red"><form:errors path="date" /></font>
				<input  size="30" required="required" pattern="([01]?[0-9]|2[0-3])(:[0-5][0-9])" title="hh:mm (23:59)" placeholder="hh:mm" name="startTime" class="form-control" id="startTime" />
			</div>
			<input type="submit" value="Create Schedule" class="btn btn-primary" />
		</form:form>
	</div>
</body>
</html>