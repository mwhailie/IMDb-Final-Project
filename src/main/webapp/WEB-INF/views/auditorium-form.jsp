<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a new auditorium</title>
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/main.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/theme.css' />" />
<script type="text/JavaScript">

	function validate()
	{	
		var year=document.audiForm.seat.value;   
		if(isNaN(year)||isNaN(time))
		{
			alert("Please enter 0-9 in the year and time Field");
		}
	}
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
		<h2>
			Add a new auditorium of: <b><c:out value="${theater.theaterName}" /></b>
		</h2>
		<br>
		<form:form action="${contextPath}/auditorium/add.htm" method="post" name="audiForm"  onsubmit="return validate();" 
			commandName="auditorium">
		
			<div class="form-group">
				<label for="number">Auditorium Name:</label> <form:input type="text"
					size="30" class="form-control" id="number" path="auditoriumName"  required="required"
					 />
			</div>
			<div class="form-group">
				<label for="seat">Seat Number:</label> <form:input size="30" name="seat" required="required"
				 class="form-control" id="seat" path = "seat"  type="number" 
					placeholder="seat" />
			</div>
			<input type="hidden" path="inTheater" name="theaterID" value="${theater.theaterID}" />
			<input type="submit" class="btn btn-primary" value="Add a new auditorium" />
		</form:form>
	</div>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<script src="<c:url value='/resources/js/jquery-3.2.1.min.js'/>"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value='/resources/js/bootstrap.min.js' /> "></script>

</body>
</html>