<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Theaters</title>
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/main.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/main.css"/>" />
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
	<br><br><br><br>
	<h2>Manage Theaters</h2>
	<table cellpadding="5" cellspacing="5" class="table table-hover">
		<tr>
			<th>Theater Name</th>
			<th>Street</th>
			<th>City</th>
			<th>State</th>
			<th>Auditorium Number</th>
			<th>Add an Auditorium</th>
			<th>Add an Schedule</th>
			<th>Update</th>
			<th>Delete</th>
		</tr>
		<c:forEach var="theater" items="${theaters}">

			<tr>
				<td>${theater.theaterName}</td>
				<td>${theater.street}</td>
				<td>${theater.city}</td>
				<td>${theater.state}</td>
				<td>${theater.audiNum}</td>
				<td><a class="btn btn-default"
					href="${contextPath}/auditorium/add.htm?title=${theater.theaterName}&num=${theater.audiNum}">add
						auditorium</a></td>
				<td><a class="btn btn-primary"
					href="${contextPath}/schedule/add.htm?title=${theater.theaterName}">add
						schedule</a></td>
				<td><a class="btn btn-default" role="button" data-toggle="collapse" href="#row${theater.theaterID}"> Update</a></td>
				<td>
					<form action="${contextPath}/theater/delete.htm" method="post">
						<input type="submit" value="Delete"
							class="deleteBtn btn btn-primary" /> <input type="hidden"
							name="title" value="${theater.theaterName}" />
					</form>
				</td>
			</tr>
			<tr class="collapse" id="row${theater.theaterID}">
				<td>
					<div class="form-group">
						<label for="theaterName">Theater Name:</label> <input type="text"
							size="30" required="required" class="form-control"
							id="theaterName" value="${theater.theaterName}" />
					</div>
				</td>
				<td>
					<div class="form-group">
						<label for="street">street:</label> <input type="text" size="30"
							required="required" class="form-control" id="street"
							value="${theater.street}" />
					</div>
				</td>
				<td>
					<div class="form-group">
						<label for="city">city:</label> <input type="text" size="30"
							required="required" class="form-control" id="city"
							value="${theater.city}" />
					</div>
				</td>
				<td>
					<div class="form-group">
						<label for="state">state:</label> <input type="text" size="30"
							required="required" class="form-control" id="state"
							value="${theater.state}" />
					</div>
				</td>
				<td><div class="success" class="alert alert-success" role="alert">You
						successfully update this theater!</div></td>
				<td>
					<div class="fail" class="alert alert-danger" role="alert">Update
						fail! Try again.</div>
				</td>
				<td><input type="hidden" name="theaterID"
					value="${theater.theaterID}" />
					<button class="btnUpdate btn btn-primary" type="submit">Save</button>
				</td>


			</tr>
		</c:forEach>
	</table>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<script src="<c:url value='/resources/js/jquery-3.2.1.min.js'/>"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value='/resources/js/bootstrap.min.js' /> "></script>
	<script>
		$(".success").hide();
		$(".fail").hide();
		$(".btnUpdate").click(
				function() {
					var theaterID = $(this).prev().val();
					var theaterName = $("#theaterName").val();
					var street = $("#street").val();
					var city = $("#city").val();
					var state = $("#state").val();
					if (theaterName == "" || street == "" || city == ""
							|| state == "") {
						alert("Empty input detected!");
						return false;
					}

					$.post("${contextPath}/theater/update.htm", {
						"theaterID" : theaterID,
						"theaterName" : theaterName,
						"street" : street,
							"city" : city,
							"state" : state
					}, function(data) {
						var json = JSON.parse(data);
						if (json.flag) {
							$(".success").show();
						} else {
							$(".fail").show();
						}
					});
				});
	</script>
	<script type="text/javascript">
		$(".deleteBtn").on("click", function(event) {
			event.preventDefault();
			var x = confirm("Are you sure to delete?");
			if (x) {
				$(this).parent().submit();
			}
		});
	</script>
</body>
</html>