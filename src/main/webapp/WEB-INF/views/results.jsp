<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Results</title>

<link rel="stylesheet" href="resources/style.css"> 
</head>
<body>
<h1>Search Results</h1>
<form name="form1" action="results" method="GET"> 
<fieldset id="group1">
<p>Price</p>
<input type="radio" name="price" value="">
<br><input type="radio" name="price" value=" (price < 50) "> $0 - $49.99
<br><input type="radio" name="price" value=" (price < 100 and price >= 50) "> $50 - $99.99
<br><input type="radio" name="price" value=" (price < 150 and price >= 100 ) ">  $100 - $149.99
<br><input type="radio" name="price" value=" (price < 200 and price >= 150 ) ">  $150 - $199.99
<br><input type="radio" name="price" value=" (price > 199.99 ) ">  $200+
</fieldset>

<fieldset id="group2">
<br>
<p>Capacity</p>
<input type="radio" name="people" value="">
<br><input type="radio" name="people" value=" (capacity <= 15 ) "> 0 - 15 people
<br><input type="radio" name="people" value=" (capacity <= 30 and capacity > 15 ) "> 16 - 30 people
<br><input type="radio" name="people" value=" (capacity <= 45 and capacity > 30 ) "> 31 - 45 people
<br><input type="radio" name="people" value=" (capacity <= 60 and capacity > 45 ) "> 46 - 60 people
<br><input type="radio" name="people" value=" (capacity >= 60 ) ">  60+ people
</fieldset>

<input type="submit" value="submit">

</form>

<p>String: ${string}</p>

<c:forEach items="${venueList }" var="venue">

<h2><a href="<c:url value='/profile?venueId=${venue.venueId}' />">${venue.venueName}</a></h2>
</c:forEach>


<a href="accountpage2">My Account</a>

</body>
</html>