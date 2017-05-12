<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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

<title>Movie Detail</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<jsp:include page="navbar.jsp" />

	<div class="container">
		<br> <br> <br> <br>
		<h2>Movie: ${movie.title}</h2>
		<table cellpadding="5" cellspacing="5" class="table">
			<tr>
				<td><img height="200" width="150" src="${movie.filename}" /></td>
				<td>
					<h4>${movie.director},${movie.actor},${movie.actress}</h4>
					<h4>
						<c:forEach var="genre" items="${movie.genres}"> ${genre},  </c:forEach>
					</h4>
					<h4>${movie.year}</h4>
				</td>
				<td><br /> <a
					href="${contextPath}/theater/list.htm?title=${movie.title}"
					type="button" class="btn btn-primary btn-lg">find in theater</a> <br />
					<a type="button" class="btn btn-primary btn-lg" data-toggle="modal"
					data-target="#reviewModel"> add review</a> <br /></td>
			</tr>
		</table>
	</div>
	<br />
	<!-- Modal -->
	<div class="modal fade" id="reviewModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add Comment on
						${movie}</h4>
				</div>
				<div class="modal-body">
					<form:form action="${contextPath}/review/add" method="post"
						commandName="review">
						<input type="hidden" name="userID"
							value="${sessionScope.user.personID}" />
						<input type="hidden" name="title" value="${movie.title}" />
						<div class="form-group">
							<label for="comment">Star:</label>
							<form:select path="star" class="form-control">
								<form:option value="1" class="form-control">1</form:option>
								<form:option value="2" class="form-control">2</form:option>
								<form:option value="3" class="form-control">3</form:option>
								<form:option value="4" class="form-control">4</form:option>
								<form:option value="5" class="form-control">5</form:option>
							</form:select>
						</div>
						<div class="form-group">
							<label for="comment">Comment:</label>
							<form:input type="text" path="comment" size="30"
								required="required" class="form-control" id="comment" />
						</div>

						<tr>
							<td colspan="2"><input type="submit" class="btn btn-primary"
								value="Save Review" /></td>
						</tr>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Reviews of ${movie}:</h3>
			</div>
			<div class="panel-body">
				<c:forEach items="${movie.reviews}" var="review">
					<div class="media">

						<!--<div class="media-left">
					<a href="#"> <img class="media-object" src="..." alt="...">
					</a>
				</div>-->

						<div class="media-body">
							<h4 class="media-heading">
								<b>${review.postByUser.username}</b> said: ${review.star}
								star(s)
							</h4>
							${review.comment}
						</div>
					</div>
					<hr>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>