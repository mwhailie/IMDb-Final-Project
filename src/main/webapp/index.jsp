<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

  <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<title>Movie Database</title>
</head>


<body>
  
<nav class="navbar navbar-inverse navbar-fixed-top">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="index.htm">IMDb</a>

	    </div>

	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li class="active"><a href="index.html">Movie<span class="sr-only">(current)</span></a></li>
	        <li><a href="#">TV</a></li>
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">I need... <span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li ><a href="#">Delivery</a></li>
	            <li><a href="#">Pick up</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a href="#">Laundry</a></li>
	            <li><a href="#">Alcohol</a></li>
                <li><a href="#">Grocery</a></li>
	          </ul>
	        </li>
	      </ul>
	      <form class="navbar-form navbar-left">
	        <div class="form-group">
	          <input type="text" class="form-control" placeholder="Find Movies, Musics, Books and more...">
	        </div>
	        <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
	      </form>
	      <ul class="nav navbar-nav navbar-right">
			<li><a class="cd-signin" data-toggle="modal" data-target="#myModal">Sign in</a></li>
       		<li><a class="cd-signup" data-toggle="modal" data-target="#myModal">Sign up</a></li>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
	      </div>
	      <div class="modal-body">
	      	  <ul class="nav nav-tabs" role="tablist">
			    <li role="presentation" class="active"><a href="#login" aria-controls="login" role="tab" data-toggle="tab">Sign in</a></li>
			    <li role="presentation"><a href="#register" aria-controls="register" role="tab" data-toggle="tab">Sign up</a></li>
			  </ul>
	  <!-- Tab panes -->
	  <div class="tab-content">
	    <div role="tabpanel" class="tab-pane active" id="login">
	    	<h2>Login</h2>
			<form action="user/login" method="post">	
				<table>
				<tr>
				    <td>User Name:</td>
				    <td><input name="username" size="30" required="required" /></td>
				</tr>		
				<tr>
				    <td>Password:</td>
				    <td><input type="password" name="password" size="30" required="required"/></td>
				</tr>		
				<tr>
				    <td colspan="2"><input type="submit" value="Login" /></td>
				</tr>				
				</table>
			</form>
		</div>
	    <div role="tabpanel" class="tab-pane" id="register"><a href="user/register.htm">Register a new User</a><br/></div>
  </div>			
	      </div>
	    </div>
	  </div>
	</div>
	<div class = "test">
	<br/>
	<br/>
	<br/>
	<br/>
		<a href="genre/add.htm"> add genre</a><br/>
		<a href="genre/list.htm"> list genre</a><br/>
		<a href="movie/add.htm"> add movie</a><br/>
		<a href="movie/search.htm"> search movie</a>
	</div>
</body>
</html>