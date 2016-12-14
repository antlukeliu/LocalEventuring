<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload a Photo!</title>
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
					<li><a href="http://sample-env.dzxmm3mvps.us-east-2.elasticbeanstalk.com">Home</a></li>
					<li><a href="searchresults">Venues</a></li>
					<li><a href="accountpage">My Account</a></li>
					<li><a href="login">Login/Logout</a></li>				</ul>
			</div>
		</div>
	</nav>


<h1>Please upload a photo of your space here: </h1>

<img src = "${photoLink}">
<footer class="footer">Local Eventuring <a href="aboutus" >Developers</a></footer>
</body>


</html>