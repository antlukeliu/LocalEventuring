<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Results</title>

<link rel="stylesheet" href="resources/style.css">
<style>
 .inline{
 	display: flex;
 	justify-content: space-around;
 }
</style>
</head>
<body>
	<div class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Local Eventuring</a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">

			</ul>
			<!-- 				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
 -->
			<ul class="nav navbar-nav navbar-right">
					<li><a href="http://localhost:8080/localeventuring">Home</a></li>
					<li><a href="results">Venues</a></li>
					<li><a href="accountpage">My Account</a></li>
					<li><a href="login">Login/Logout</a></li>							</ul>
		</div>
	</div>
	</div>
<div id="results">
	<h1>Search Results</h1>
</div>	
	<div class="left clearfix">
	<h3>Categories</h3>
		<form name="form1" action="results" method="GET" class="form-control">
			<fieldset id="group1">
				
				<p>Price</p>
				<input type="radio" name="price" value=""> 
				<br> 
				
				<input	type="radio" name="price" value="1" onClick="results"> $0 -$49.99 
				<br> <input type="radio" name="price" value="2"> $50 - $99.99 <br>
				<input type="radio" name="price" value="3"> $100 - $149.99 <br>
				<input type="radio" name="price" value="4"> $150 - $199.99 <br>
				<input type="radio" name="price" value="5">$200+
			</fieldset>

			<fieldset id="group2">
				<br>
				<p>Capacity</p>
				<input type="radio" name="people" value=""> <br> <input
					type="radio" name="people" value="6"> 0 -
				15 people <br> <input type="radio" name="people"
					value="7"> 16 - 30
				people <br> <input type="radio" name="people"
					value="8"> 31 - 45
				people <br> <input type="radio" name="people"
					value="9"> 46 - 60
				people <br> <input type="radio" name="people"
					value="10"> 60+ people
			</fieldset>
			
			<fieldset id="group3">
				<br>
				<p>Category</p>
				<input type="radio" name="category" value=""> 
				<br> 
				<input type="radio" name="category" value="11"> Meetings 
				<br> <input type="radio" name="category" value="12"> Meetups 
				<br> <input type="radio" name="category"
					value="13"> Trade Show
				people <br> <input type="radio" name="category"
					value="14"> Parties All Ages
				people <br> <input type="radio" name="category"
					value="15"> Parties 21+
			</fieldset>
			<br>
			<button type="submit" class="btn btn-primary">Search</button>

		</form>
	</div>
	<div class="containerresults clearfix">
		<p>${num}</p>
		<c:forEach items="${venueList }" var="venue">
			<div class="col-lg-12 inline">
			<h2>
				<img src="${venue.photoLink}" class="thumbnail" height="100px" width="200px">
				<a href="<c:url value='/profile?venueId=${venue.venueId}' />">${venue.venueName}</a>
			</h2>
			<table>
				<tr><td>Category: ${venue.category}</td></tr>
				<tr><td><br></td></tr>
				<tr><td>Price:$${venue.price} per hour</td></tr>
				<tr><td><br></td></tr>
				<tr><td>Capacity: ${venue.capacity} people</td></tr>
			</table>
			
			</div>
			
			<hr style="height:1px;border:none;color:#333;background-color:#333;" />
		</c:forEach>
	

	</div>
</body>
</html>