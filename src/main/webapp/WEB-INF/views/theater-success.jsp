<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Theater Added Successfully</title>
    </head>
    <body>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    
	<a href="${contextPath}/admin.htm">Home</a><br/>
    <a href="${contextPath}/theater/add.htm">add another one</a>
        New Movie Added Successfully: ${theater.theaterName}<br>
    </body>
</html>