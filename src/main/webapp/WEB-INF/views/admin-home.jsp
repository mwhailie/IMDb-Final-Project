<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/main.css"/>" >
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
	<h1> Welcome!</h1>
	<!-- Standard button -->

		<a href="genre/add.htm" class="btn btn-default btn-lg">  add genre  </a><br />
		<a href="genre/manage.htm"  class="btn btn-primary btn-lg"> manage genre</a><br />
		<a href="movie/add.htm" class="btn btn-default btn-lg"> add movie</a><br /> 
		<a href="movie/manage.htm"  class="btn btn-primary btn-lg"> manage movie</a><br /> 
		<a href="theater/add.htm" class="btn btn-default btn-lg"> add theater</a><br />
		<a href="theater/manage.htm" class="btn btn-primary btn-lg"> manage theater</a><br />

		<a href="ticket/add.htm" class="btn btn-default btn-lg"> add ticket</a><br />
		</div>
</body>
</html>