<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello!</title>
<link rel="stylesheet" href="resources/style.css">
</head>
<body>
	<nav class="navbar navbar-default">
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

			<ul class="nav navbar-nav navbar-right">
				<li><a href="http://localhost:8080/localeventuring">Home</a></li>
				<li><a href="searchresults">Check Out Venues</a></li>
				<li><a href="resources/addform.html">Add New Venue</a></li>
				<li><a href="accountpage">My Account</a></li>
				<li><a href="aboutus">Developers</a></li>
				<li><a href="login">Login/Logout</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<h1>Account Page</h1>
	<h1>
		Hi
		<c:out value="${fullName}" />
		!
	</h1>
	
	<h4 style="text-align: center"><a href="resources/addform.html">Add New Venue</a></h4>
	
	<h1 style="text-align: center margin-right: 20px">My Venues</h1>
	<div class="container clearfix">
			
		<br>
			<c:forEach items="${venuesOwned }" var="venue">
				<div class="form-group">

					<div class="col-lg-8">
						<h3>
							<a href="<c:url value='/venueprofile?venueId=${venue.venueId}' />">${venue.venueName}</a>
						</h3>
						<hr>
					</div>
					<div class="col-lg-2">
						<form action="updateinfo?venueId=${venue.venueId}" method="post"
						onSubmit="return confirm('Are you sure you want to update ${venue.venueName}?');">
							<button type="submit" class="btn btn-primary">Update</button>
						</form>
						
					</div>
					<div class="col-lg-2">
						<form action="deletevenue?venueId=${venue.venueId}" method="post"
						onSubmit="return confirm('Are you sure you want to delete ${venue.venueName}?');">
							<button type="submit" class="btn btn-default">Delete</button>
						</form>
					
					</div>
				</div>		
			</c:forEach>
	</div>

</body>
</html>