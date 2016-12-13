<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
					<li><a href="">Home</a></li>
					<li><a href="searchresults">Check Out Venues</a></li>
					<li><a href="accountpage">My Account</a></li>
					<li><a href="login">Login/Logout</a></li>				</ul>
			</div>
		</div>
	</nav>

<h1>
	LocalEventuring!  
</h1>

<div class="container">
  <br>
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="http://res.cloudinary.com/localeventuring/image/upload/v1481665357/rmnbobrpo1myvutunkjj.png" alt="Chania" width="700" height="700">
      </div>

      <div class="item">
        <img src="http://res.cloudinary.com/localeventuring/image/upload/v1481658969/ktyfrgipgfbmvb9qh5jy.jpg" alt="Chania" width="700" height="700">
      </div>
    
      <div class="item">
        <img src="http://res.cloudinary.com/localeventuring/image/upload/v1481667309/impsxecwfwbgiu9qb4rv.png" alt="Flower" width="700" height="700">
      </div>

      <div class="item">
        <img src="http://res.cloudinary.com/localeventuring/image/upload/v1481571450/sii1uwnhbh57y2i6ezhg.png" alt="Flower" width="700" height="700">
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>




<footer class="footer">Local Eventuring <a href="aboutus" >Developers</a></footer>
</body>
</html>
