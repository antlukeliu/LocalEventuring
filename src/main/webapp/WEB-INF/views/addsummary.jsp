<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/style.css">
<title>Venue Added!</title>
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
					<li><a href="http://sample-env.dzxmm3mvps.us-east-2.elasticbeanstalk.com">Home</a></li>
					<li><a href="searchresults">Event Spaces</a></li>
					<li><a href="accountpage">My Account</a></li>
					<li><a href="login">Login/Logout</a></li>				</ul>
			</div>
		</div>
	</nav>


<h1>Venue Added</h1>

<h1><c:out value="${venueName }"/></h1><br>
<ul>
<li><b>Room Size (sq ft.):</b> <c:out value="${roomSize }"/></li><br>
<b><li>Capacity:</b> <c:out value="${capacity }"/></li><br>
<b><li>Price Per Hour: </b> <c:out value="${price }"/></li><br>
<li><img src = "${photoLink}"></li><br>
<b><li>Venue Description: </b> <c:out value="${description }"/></li><br> 
</ul>
<<<<<<< Updated upstream
=======
<div>
>>>>>>> Stashed changes
<img src ="${calendarLink }"/>
</div>
</body>
</html>