<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Place Order</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
<script
	src=" https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js "></script>
<script src="<c:url value='/resources/js/order.js'/>"></script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<jsp:include page="navbar.jsp" />
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
				<li><a href="${contextPath}/user/userhome.htm" class="cd-signin">Welcome, ${sessionScope.user.username}!</a></li>
				<li><a href="${contextPath}/user/logout.htm" class="cd-signup" >Logout</a></li>
			</ul>
			</c:if>
			<c:if test="${sessionScope.user==null}">
			<ul class="nav navbar-nav navbar-right">
				<li><a class="cd-signin" data-toggle="modal"
					data-target="#myModal">Sign in</a></li>
				<li><a href="${contextPath}/admin.htm" class="cd-signup" >Admin Login</a></li>
				</ul>
			</c:if>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	
	
	<div class="container-fluid">

		<h1>Place Order</h1>

		<div class="panel panel-default">
			<div class="panel-heading">

				<h2 class="panel-title">Movie: ${title} in ${theaterName}</h2>

			</div>
			<div class="panel-body">

				<div id="time">
					<h3>1.Choose Time</h3>
					<ul class="list-group">
						<c:forEach items="${schedules}" var="schedule">
							<li class="list-group-item" value="${schedule.scheduleID}">

								<a data-toggle="modal" data-target="#orderModal"
								onclick="showModal(this)" id="${schedule.scheduleID}"> <span
									id="schedule${schedule.scheduleID}">${schedule.startTime}, ${schedule.date}</span>
									<span>&#36; </span><span id="price${schedule.scheduleID}"> ${schedule.ticketprice}</span>
							</a>
							</li>
						</c:forEach>
					</ul>
				</div>

				<!-- add item Modal -->
				<div class="modal fade" id="orderModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog  modal-sm" role="document">
						<div class="modal-content">
							<!-- modal header -->
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<input type="hidden" id="scheduleID" name="scheduleID" value="">
								<h3>2.Number of Tickets</h3>
							</div>
							<!-- modal body -->
							<div class="modal-body">
								<h4 class="modal-title" id="myModalLabel"></h4>
								<div class="perTicket"></div>
							</div>
							<!-- modal footer -->
							<div class="modal-footer">
								<div class="input-group number-spinner">
									<span class="input-group-btn">
										<button class="btn btn-default" data-dir="dwn">
											<span class="glyphicon glyphicon-minus"></span>
										</button>
									</span> <input id="quantityinput" type="text"
										class="form-control text-center" value="1"> <span
										class="input-group-btn">
										<button class="btn btn-default" data-dir="up">
											<span class="glyphicon glyphicon-plus"></span>
										</button>
									</span>
								</div>
								<button type="button" class="btnSave btn-primary btn-addtocart"
									onclick="addtobag()" data-dismiss="modal">
									Add to Bag <span class="badge" id="myModalPrice"></span>
								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- Modal End -->
				<div id="pay">
					<h3>3.Payment</h3>
					<table class="table">
						<thead>
							<td>Ticket Number</td>
							<td>Schedule ID</td>
							<td>Schedule Name</td>
							<td>Total Price</td>
						</thead>
						<tbody>
						</tbody>
					</table>
					<p>
						Total Price: $<span id="totalprice">0</span>
					</p>
					<form action="${contextPath}/order/add.htm" method="POST">
						<input type="hidden" id="user" name="user" value="${sessionScope.user.username} }">
						<input type="hidden" id="totalpricehiddenfield" name="totalprice" value="">
						<input type="hidden" id="scheduleIDhiddenfield" name="scheduleID" value="">
						<input type="hidden" id="ticketNumhiddenfield" name="number" value="">
						<input type="submit" class="btn btn-default" value="Place Order">
					</form>
					
				</div>
			</div>
		</div>
	</div>
	<script>
	 	$("#pay").hide();

	</script>
</body>
</html>