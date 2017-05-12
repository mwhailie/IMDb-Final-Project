<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Successful!</title>
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
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="${contextPath }/movie/list.htm">Movie
						List<span class="sr-only">(current)</span>
				</a></li>
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
			<form class="navbar-form navbar-left" action="movie/search.htm"
				method="post">
				<div class="form-group">
					<input name="title" type="text" class="form-control"
						placeholder="Find Movies...">
				</div>
				<button type="submit" class="btn btn-default">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
				</button>
			</form>
			<c:if test="${sessionScope.user!=null}">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${contextPath}/user/userhome.htm"
						class="cd-signin">Welcome, ${sessionScope.user.username}!</a></li>
					<li><a href="${contextPath}/user/logout.htm" class="cd-signup">Logout</a></li>
				</ul>
			</c:if>
			<c:if test="${sessionScope.user==null}">
				<ul class="nav navbar-nav navbar-right">
					<li><a class="cd-signin" data-toggle="modal"
						data-target="#myModal">Sign in</a></li>
					<li><a href="${contextPath}/admin.htm" class="cd-signup">Admin
							Login</a></li>
				</ul>
			</c:if>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<br>
	<br>
	<br>
	<br>
	<h1>Order Successful!</h1>
	<h3>Congratulation! ${sessionScope.user.username} , your order
		${order} is successfully placed.</h3>
	<p>The total price is: ${order.total} dollars</p>
	<table class="table">
		<tr>
			<td>ticket ID</td>
			<th>Movie</th>
			<th>auditorium</th>
			<th>seat</th>
			<th>price</th>
			<th>time</th>
		</tr>
		<c:forEach items="${tickets}" var="ticket">
			<tr>
				<td>${ticket.ticketID}</td>
				<td>${movie}</td>
				<td>${auditorium}</td>
				<td>${ticket.seat}</td>
				<td>${ticket.price}</td>
				<td>${schedule.startTime}</td>
			</tr>
		</c:forEach>
	</table>

	<input type="hidden" id="ticketNum" value="${ticketNum}" />
	<input type="hidden" id="orderID" value="${order.orderID}" />
	<input type="hidden" id="auditorium" value="${auditorium}" />
	<input type="hidden" id="scheduleID" value="${schedule.scheduleID}" />
	<input type="hidden" id="movie" value="${movie}" />
	<button class="btnSend btn btn-primary" type="submit">email me
		my order</button>
	<br>
	<div id="success" class="alert alert-success" role="alert">Your
		order is sent to your email!</div>
	<div id="fail" class="alert alert-danger" role="alert">Email
		fail! Try again.</div>
	<form action="${contextPath}/order/pdfticket.pdf" method="get">
		<input type="hidden" name="ticketNum" value="${ticketNum}" /> <input
			type="hidden" name="orderID" value="${order.orderID}" /> <input
			type="hidden" name="auditorium" value="${auditorium}" /> <input
			type="hidden" name="scheduleID" value="${schedule.scheduleID}" /> <input
			type="hidden" name="movie" value="${movie}" />
		<button class="btn btn-default" type="submit">print my order</button>
	</form>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<script src="<c:url value='/resources/js/jquery-3.2.1.min.js'/>"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value='/resources/js/bootstrap.min.js' /> "></script>
	<script>
		$("#success").hide();
		$("#fail").hide();

		$(".btnSend").click(function() {
			var ticketNum = $("#ticketNum").val();
			var orderID = $("#orderID").val();
			var scheduleID = $("#scheduleID").val();
			var auditorium = $("#auditorium").val();
			var movie = $("#movie").val();
			if (orderID == "") {
				alert("Empty input detected!");
				return false;
			}
			alert("!");
			$.post("${contextPath}/order/email.htm", {
				"ticketNum" : ticketNum,
				"orderID" : orderID,
				"scheduleID" : scheduleID,
				"auditorium" : auditorium,
				"movie" : movie
			}, function(data) {
				var json = JSON.parse(data);
				if (json.flag) {
					$("#success").show();
				} else {
					$("#fail").show();
				}
			});
		});

	</script>
</body>
</html>