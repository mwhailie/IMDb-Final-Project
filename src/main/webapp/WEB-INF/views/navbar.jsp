<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
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
				<li class="active"><a href="${contextPath}/movie/list.htm">Movie
						List<span class="sr-only">(current)</span>
				</a></li>
				<li><a href="${contextPath}/theater/list.htm">Theater List</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">I need... <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${contextPath}/movie/recent.htm">Recent</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="${contextPath}/theater/search.htm">Near By Theater</a></li>
					</ul></li>
			</ul>
			<form class="navbar-form navbar-left" action="${contextPath}/movie/search.htm"
				method="post">
				<div class="form-group">
					<input name="title" type="text" class="form-control"
						placeholder="Find Movies...">
						<input type="hidden"  name="director" value=""><input type="hidden"  name="actor" value=""><input type="hidden"  name="actress" value=""><input type="hidden" value="">
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
	<!-- /.container-fluid --> </nav><!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">

				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#login"
							aria-controls="login" role="tab" data-toggle="tab">Sign in</a></li>
						<li role="presentation"><a href="#register"
							aria-controls="register" role="tab" data-toggle="tab">Sign up</a></li>
					</ul>
					<!-- Tab panes -->
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="login">
							<h2>Login</h2>
							<form action="${contextPath}/user/login" method="post">
								<div class="form-group">
									<label for="username">Username</label> <input name="username"
										type="text" class="form-control" id="username"
										placeholder="username">
								</div>
								<div class="form-group">
									<label for="password">Password</label> <input name="password"
										type="password" class="form-control" id="password"
										placeholder="password">
								</div>
								<button type="submit" class="btn btn-default">Login</button>
							</form>
						</div>
						<div role="tabpanel" class="tab-pane" id="register">
							<a href="${contextPath}/user/register.htm">Register a new User</a><br />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->