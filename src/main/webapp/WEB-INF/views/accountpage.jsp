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
				<li><a href="login">Login/Logout</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<h1>Account Page</h1>
	<h2>
		Hi
		<c:out value="${fullname}" />
		!
	</h2>
	
	<a href="resources/addform.html">Add New Venue</a>

	<h2>My Venues</h2>
	<div class="clearfix">
	<div class="form-group">
			<c:forEach items="${venuesOwned }" var="venue">


					<div class="col-lg-8">
						<h3>
							<a href="<c:url value='/updateinfo?venueId=${venue.venueId}' />">${venue.venueName}</a>
						</h3>
					</div>
					<div class="col-lg-2">
						<form action="updateinfo?venueId=${venue.venueId}" method="post">
							<button type="submit" class="btn btn-primary">Update</button>
						</form>
					</div>
					<div class="col-lg-2">
						<form action="deletevenue?venueId=${venue.venueId}" method="post"
						onSubmit="return confirm('Are you sure you want to delete this venue?)">
							<button type="submit" class="btn btn-default">Delete</button>
						</form>
					</div>
					<hr>
			</c:forEach>
	</div>
	</div>

	<div class="footerprofile">
		Local Eventuring <a href="aboutus">Developers</a>
	</div>

</body>
</html>