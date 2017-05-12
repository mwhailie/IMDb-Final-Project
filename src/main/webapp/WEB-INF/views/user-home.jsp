<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

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

</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<jsp:include page="navbar.jsp" />
	<br>
	<br>
	<br>
	<br>
	
	<div class="container">
	<h1>Welcome, ${sessionScope.user.username}!</h1>
	<div>
		<div class="step-header">
			<h2>How it works:</h2>
		</div>
		<div class="row" id="row4">
			<section
				class="col-xs-offset-3 col-xs-6 col-md-offset-0 col-md-4 col-lg-4">
			<a href="${contextPath}/user/update.htm"> <img class="icon"
				src="<c:url value="/resources/imgs/icon-order.svg" />" alt="Icon">
				<h3>update my accout</h3>
				<p>You can update your information here.</p>
			</a> </section>
			<section
				class="col-xs-offset-3 col-xs-6 col-md-offset-0 col-md-4 col-lg-4">
			<a href="${contextPath}/movie/search.htm"> <img class="icon"
				src="<c:url value="/resources/imgs/icon-search.svg" />" alt="Icon">
				<h3>Browse movie</h3>
				<p>Browse our database and search for your favorite. Leave your
					review!</p>
			</a> </section>
			<section
				class="col-xs-offset-3 col-xs-6 col-md-offset-0 col-md-4 col-lg-4">
			<a href="theater/list.htm"> <img class="icon"
				src="<c:url value="/resources/imgs/icon-store.svg" />" alt="Icon">
				<h3>List Theater</h3>
				<p>See our theaters and get tickets!</p>
			</a> </section>
		</div>
	</div>
	
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">My Review:</h3>
				</div>
				<div class="panel-body">
					<table class="table">
						<tbody>
							<tr>
								<th>movie</th>
								<th>Star</th>
								<th>Comment</th>
							</tr>
							<c:forEach var="review" items="${reviews}">
								<tr>
									<td>${review.movie}</td>
									<td>${review.star}</td>
									<td>${review.comment}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">My Orders:</h3>
				</div>
				<div class="panel-body">
					<table class="table">
						<tbody>
							<tr>
								<th>Order ID</th>
								<th>Total Price</th>
							</tr>
							<c:forEach var="order" items="${orders}">
								<tr>
									<td>${order.orderID}</td>
									<td>${order.total}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

</body>
</html>