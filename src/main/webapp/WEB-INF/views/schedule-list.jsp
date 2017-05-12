<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<h2>Movie: ${movie.title} in ${theater}</h2>
	<h2>Schedule List</h2>
	   <table cellpadding="5" cellspacing="5"  class="table table-hover">
	              
                <tr>
                    <th>Theater Name</th>
                    <th>Theater Address</th>
                    <th>Get Showtimes Tickets</th>
                </tr>
            <c:forEach var="schedule" items="${schedules}">
                <tr>
                	<td>${schedule.date}</td>
                    <td>${schedule.startTime}</td>
                    
                    <td><a href="${contextPath}/schedule/list.htm?theaterName=${theater.theaterName}&title= ${movie.title}">Select</a></td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>