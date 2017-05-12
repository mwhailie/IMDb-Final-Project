<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/main.css"/>" />
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


<title>Add a New Movie</title>


<script type="text/JavaScript">

	function btnSubmit_onclick()
	{	
		var year=document.movieForm.year.value;  
		var time=document.movieForm.time.value;  
		if(isNaN(year)||isNaN(time))
		{
			alert("Please enter 0-9 in the year and time Field");
			  return false;  
		}else{  
		  return true;  
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
	
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<div class="center-block">
	<h2>Add a New Movie</h2>
		<form:form method="post" commandName="movie" name="movieForm" id="movieForm"
			enctype="multipart/form-data">


			<div class="form-group">
				<label for="genre">Genre:</label>
				<form:select path="genres" items="${genres}" multiple="true"
					required="required" class="form-control" id="genre" />
			</div>
			<div class="form-group">
				<label for="title">Movie Title:</label>
				<form:input type="text" path="title" size="30" required="required"
					class="form-control" id="title" />
			</div>
			<div class="form-group">
				<label for="year">Year:</label>
				<form:input type="number" path="year" size="30" required="required" name="year"
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
				<label for="time">Time (mins):</label>
				<form:input type="number" path="time" size="30" required="required"  name="time"
					class="form-control" id="time" />
			</div>
			<div class="form-group">
				<label for="filename">Filename:</label>
				<form:input type="text" path="filename" name="filename" size="30"
					required="required" class="form-control" id="filename" />
			</div>
			<div class="form-group">
				<label for="poster">Select poster:</label> <input type="file"
					name="poster" class="form-control" id="poster" />
			</div>
			<input onclick="btnSubmit_onclick()" type="submit" class="btn btn-primary" value="Add Movie" />
		</form:form>
	</div>
</body>
</html>