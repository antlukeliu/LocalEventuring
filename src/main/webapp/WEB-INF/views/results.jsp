<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Results</title>
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
<p>Capcity</p>
<input type="radio" name="people" value="">
<br><input type="radio" name="people" value=" (capcity <= 15 ) "> 0 - 15 people
<br><input type="radio" name="people" value=" (capcity <= 30 and capcity > 15 ) "> 16 - 30 people
<br><input type="radio" name="people" value=" (capcity <= 45 and capcity > 30 ) "> 31 - 45 people
<br><input type="radio" name="people" value=" (capcity <= 60 and capcity > 45 ) "> 46 - 60 people
<br><input type="radio" name="people" value=" (capcity >= 60 ) ">  60+ people
</fieldset>

<input type="submit" value="submit">

</form>

<c:forEach items= "${test}" var="String">

<p>${String}<p>
</c:forEach>
<!-- <c:forEach items="${venueList }" var="venue">
	<tr>
		<td>${venue.id }</td>
		<td>${venue.size }</td>
		<td><a href="<c:url value='/viewVenue?rank=${venue.id}' />">${venue.name }</a></td>
		<td>${venue. }: ${venue. }</td>
		<td>${venue. }</td>
		
	</tr>
</c:forEach> -->


<a href="accountpage2">My Account</a>

</body>
</html>