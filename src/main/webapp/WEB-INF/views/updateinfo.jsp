<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Information</title>

<script>
	function myFunction() {
		document.getElementById("myP").contentEditable = true;
	}
</script>
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
				<li><a href="">Home</a></li>
				<li><a href="accountpage">My Account</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<h1>
		<c:out value="{venue.venueId}" />
	</h1>
	<div class="container">
		<form name="updateform" action="../updateform" method="post"
			class="form-horizontal">
			<fieldset>
				<legend>Enter the Required Information</legend>
				<div class="form-group">
					<label for="venueName" class="col-lg-2 control-label">Venue
						Name:</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="venuename"
							placeholder="Venue Name">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 control-label">Room Size:</label>
					<div class="col-lg-4">
						<input type="text" class="form-control" name="roomsize"
							placeholder="In SQ. FT">
					</div>
					<label class="col-lg-2 control-label">Capacity:</label>
					<div class="col-lg-4">
						<input type="text" class="form-control" name="capacity"
							placeholder="# Of People">
					</div>
				</div>

				<div class="form-group">
					<label class="col-lg-2 control-label">Price:</label>
					<div class="col-lg-4">
						<input type="text" class="form-control" name="price"
							placeholder="$ per hour">
					</div>
				</div>

				<div class="form-group">
					<a href=""></a> <label class="col-lg-2 control-label">Image
					</label>
					<div class="col-lg-10">
						<input type="file" class="form-control" name="file"
							placeholder="Upload File">
					</div>
				</div>
				<label class="col-lg-2 control-label"></label> <a href=""
					class="col-lg-10">Directions on getting Google Calendar iframe</a>
				<br>
				<div class="form-group">

					<label class="col-lg-2 control-label">Calendar Link:</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="calendarlink"
							placeholder="Calendar Iframe">
					</div>
				</div>


				<div class="form-group">
					<label for="textArea" class="col-lg-2 control-label">Description:</label>
					<div class="col-lg-10">
						<textarea class="form-control" rows="5" id="textArea"
							name="description"></textarea>
						<span class="help-block">Add additional information about
							your venue.</span>
					</div>
				</div>
				<legend>Contact Information</legend>

				<div class="form-group">
					<label for="email" class="col-lg-2 control-label">E-Mail:</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="email" placeholder="${email}">
					</div>
				</div>

				<div class="form-group">
					<label for="Streetaddress" class="col-lg-2 control-label">Street
						Address:</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="street"
							placeholder="${street}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 control-label">City:</label>
					<div class="col-lg-4">
						<input type="text" class="form-control" name="city"
							placeholder="${city}">
					</div>
					<label class="col-lg-1 control-label">State:</label>
					<div class="col-lg-1">
						<input type="text" class="form-control" name="state"
							placeholder="${city}">
					</div>

					<label class="col-lg-1 control-label">Zip Code:</label>
					<div class="col-lg-2">
						<input type="text" class="form-control" name="zipcode"
							placeholder="${zipcode}">
					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<button type="reset" class="btn btn-default">Clear Form</button>
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</div>

			</fieldset>
		</form>
	</div>

</body>
</html>