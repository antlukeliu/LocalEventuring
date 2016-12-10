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
				<li><a href="">Home</a></li>
				<li><a href="accountpage">My Account</a></li>
			</ul>
		</div>
	</div>
	</div>

	<h1 id="resultsh1">Search Results</h1>
	<div class="left clearfix">
	<h3>Categories</h3>
		<form name="form1" action="results" method="GET" class="form-control">
			<fieldset id="group1">
				
				<p>Price</p>
				<input type="radio" name="price" value=""> 
				<br> 
				
				<input	type="radio" name="price" value=" (price < 50) "> $0 -$49.99 
				<br> <input type="radio" name="price" value=" (price < 100 and price >= 50) "> $50 - $99.99 <br>
				<input type="radio" name="price" value=" (price < 150 and price >= 100 ) "> $100 - $149.99 <br>
				<input type="radio" name="price" value=" (price < 200 and price >= 150 ) "> $150 - $199.99 <br>
				<input type="radio" name="price" value=" (price > 199.99 ) ">$200+
			</fieldset>

			<fieldset id="group2">
				<br>
				<p>Capacity</p>
				<input type="radio" name="people" value=""> <br> <input
					type="radio" name="people" value=" (capacity <= 15 ) "> 0 -
				15 people <br> <input type="radio" name="people"
					value=" (capacity <= 30 and capacity > 15 ) "> 16 - 30
				people <br> <input type="radio" name="people"
					value=" (capacity <= 45 and capacity > 30 ) "> 31 - 45
				people <br> <input type="radio" name="people"
					value=" (capacity <= 60 and capacity > 45 ) "> 46 - 60
				people <br> <input type="radio" name="people"
					value=" (capacity >= 60 ) "> 60+ people
			</fieldset>
			<br>
			<input type="submit" value="Search">

		</form>
	</div>
	<div class="container clearfix">

		<c:forEach items="${venueList }" var="venue">

			<h2>
				<a href="<c:url value='/profile?venueId=${venue.venueId}' />">${venue.venueName}</a>
			</h2>
			<hr>
		</c:forEach>
	</div>

	<p></p>
	<div class="footerresults">
		Local Eventuring <a href="aboutus">Developers</a>
	</div>
</body>
</html>