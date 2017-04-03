<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Browse Movie</title>
        <script>
            function validateForm() {
                var query = document.forms["form"]["query"].value;
                if (query == "") {
                    alert("Pleae fill all the request");
                    return false;
                }
            }
        </script>
</head>
<body>
	<h1>Browse Movie</h1>
        <div>
         <h2>Please enter the keyword:</h2>
            <br /> 
            <form id='form' onsubmit="return validateForm()"  action="search.htm" method="POST" >                                
                Search Query <input type="text" name="query"/> <br/><br/>
                <input type="radio" name="searchType" value="actor" checked/> Search By Actor<br/>
                <input type="radio" name="searchType" value="actress"/> Search By Actress<br/>
                 <input type="radio" name="searchType" value="title"/> Search By Title<br/>
                <input type="radio" name="searchType" value="genre"/> Search By Genre<br/>
                <input type="radio" name="searchType" value="year"/> Search By Year<br/>
                <br/><input type="submit"  value="Search Movie"/>
            </form>
        </div>
</body>
</html>