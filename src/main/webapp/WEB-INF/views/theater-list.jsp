<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<title>Theater List</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<jsp:include page="navbar.jsp" />
	<div class="container">
		<br><br><br><br>
	<h2>Theater List</h2>
	   <table cellpadding="5" cellspacing="5"  class="table table-hover">
	              
                <tr>
                    <th>Theater Name</th>
                    <th>Theater Address</th>
                    <th>Get Showtimes Tickets</th>
                </tr>
            <c:forEach var="theater" items="${theaters}">
                <tr>
                    <td>${theater.theaterName}</td>
                    <td>${theater.street}, ${theater.city}, ${theater.state}</td>
                    <td><a href="${contextPath}/schedule/list.htm?theaterName=${theater.theaterName}&title=${movie.title}">Get Tickets</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>