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