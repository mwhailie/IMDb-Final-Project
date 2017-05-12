<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <title>Movie List</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<jsp:include page="navbar.jsp" />
	
	<br>	<br>	<br>	<br>
	<div class="container"><h2>Movie List</h2>
	   <table cellpadding="5" cellspacing="5"  class="table table-hover">
            <c:forEach var="movie" items="${movies}">
                <tr>
                	<td  width="150"><a href="${contextPath}/movie/detail.htm?title=${movie.title}"><img height="200" width="150" src="${movie.filename}" /></a></td>
                    <td><h4><a href="${contextPath}/movie/detail.htm?title=${movie.title}">${movie.title}</h4><br>
                    ${movie.director}, ${movie.actor}, ${movie.actress} <br>
                    <c:forEach var="genre" items="${movie.genres}"> ${genre},  </c:forEach><br>
	                ${movie.year} 
	                </a>
	                </td>    
                </tr>
            </c:forEach>
        </table></div>
	
</body>
</html>