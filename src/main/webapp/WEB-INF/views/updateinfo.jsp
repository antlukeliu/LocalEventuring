<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Information</title>
<link rel="stylesheet" href="resources/style.css">
<script>
	function validateEmail(em) {
	    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    return re.test(em);
	}

function validate(){
	var em = document.forms["addform"]["venueName"].value;
	var rs = document.forms["addform"]["roomSize"].value;
	var cp = document.forms["addform"]["capacity"].value;
	var pr = document.forms["addform"]["price"].value;
	var fn = document.forms["addform"]["fullName"].value;
	var em = document.forms["addform"]["email"].value;
	var ct = document.forms["addform"]["city"].value;
	var st = document.forms["addform"]["state"].value;
	var zip = document.forms["addform"]["zipCode"].value;
	
	var reg = /^\d+$/;
	
	if (fn <= 2){
		alert("Name is too short");
		return false;
	}else if(reg.test(rs) === false){
		alert("Invalid Room Size Enter Digits Only");
		return false;
	}else if(reg.test(cp) === false){
		alert("Invalid Capacity Enter Digits Only");
		return false;
	}else if(reg.test(pr) === false){
		alert("Invalid Price Enter Digits Only");
		return false;
	}else if(validateEmail(em) === false){
		alert("Enter a email");
		return false;
	}else if (/^[0-9]{5}(?:-[0-9]{4})?$/.test(zip) === false ){
		alert("Invalid Zip Code");
		return false;
	else{
		return true;
	}
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
	
			<ul class="nav navbar-nav navbar-right">
					<li><a href="http://localhost:8080/localeventuring">Home</a></li>
					<li><a href="searchresults">Venues</a></li>
					<li><a href="accountpage">My Account</a></li>
					<li><a href="login">Login/Logout</a></li>			</ul>
		</div>
	</div>
	</nav>
	<h1>
		Venue Number: #<c:out value="${venueId}" />
	</h1>
	<div class="container">
		<form name="updateform" action="viewupdate" method="post"
		enctype="multipart/form-data"	class="form-horizontal" onSubmit="return validate()">
			<fieldset>
				<legend>All Fields Must Have Inputs Information</legend>
				<div class="form-group">
					<label for="venueName" class="col-lg-2 control-label">Venue
						Name:</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="venueName"
							value="${venueName}">
						<input type="hidden" name="venueId"
							value="${venueId}">
						<input type="hidden" name="contactId"
							value="${contactId}">	
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 control-label">Room Size:</label>
					<div class="col-lg-4">
						<input type="text" class="form-control" name="roomSize"
							value="${roomSize}">
					</div>

					<label class="col-lg-2 control-label">Capacity:</label>
					<div class="col-lg-4">
						<input type="text" class="form-control" name="capacity"
							value="${capacity}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-lg-2 control-label">Price:</label>
					<div class="col-lg-4">
						<input type="text" class="form-control" name="price"
							value="${price}">
					</div>
					<label for="select" class="col-lg-2 control-label">Category</label>
					<div class="col-lg-4">
						<select id="select" name="category" class="form-control">
							<option value="meetings">Meetings</option>
							<option value="meetups">Meetups</option>
							<option value="tradeshow">Trade Show</option>
							<option value="partiesallages">Parties All Ages</option>
							<option value="parties21">Parties 21+</option>
						</select> <br>
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
						<input type="text" class="form-control" name="calendarLink"
							value="${calendarLink}">
					</div>
				</div>


				<div class="form-group">
					<label for="textArea" class="col-lg-2 control-label">Description:</label>
					<div class="col-lg-10">
						<textarea class="form-control" rows="5" id="textArea"
							name="description" value="${description}"></textarea>
						<span class="help-block">Add additional information about
							your venue.</span>
					</div>
				</div>
				<legend>Contact Information</legend>

				<div class="form-group">
					<label for="email" class="col-lg-2 control-label">E-Mail:</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="email" value="${email}">
					</div>
				</div>

				<div class="form-group">
					<label for="Streetaddress" class="col-lg-2 control-label">Street
						Address:</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="street"
							value="${street}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 control-label">City:</label>
					<div class="col-lg-4">
						<input type="text" class="form-control" name="city"
							value="${city}">
					</div>
					<label class="col-lg-1 control-label">State:</label>
				<div class="col-lg-2">
						<select class="form-control" name="state">
							<option value="AL">Alabama</option>
							<option value="AK">Alaska</option>
							<option value="AZ">Arizona</option>
							<option value="AR">Arkansas</option>
							<option value="CA">California</option>
							<option value="CO">Colorado</option>
							<option value="CT">Connecticut</option>
							<option value="DE">Delaware</option>
							<option value="DC">District Of Columbia</option>
							<option value="FL">Florida</option>
							<option value="GA">Georgia</option>
							<option value="HI">Hawaii</option>
							<option value="ID">Idaho</option>
							<option value="IL">Illinois</option>
							<option value="IN">Indiana</option>
							<option value="IA">Iowa</option>
							<option value="KS">Kansas</option>
							<option value="KY">Kentucky</option>
							<option value="LA">Louisiana</option>
							<option value="ME">Maine</option>
							<option value="MD">Maryland</option>
							<option value="MA">Massachusetts</option>
							<option value="MI">Michigan</option>
							<option value="MN">Minnesota</option>
							<option value="MS">Mississippi</option>
							<option value="MO">Missouri</option>
							<option value="MT">Montana</option>
							<option value="NE">Nebraska</option>
							<option value="NV">Nevada</option>
							<option value="NH">New Hampshire</option>
							<option value="NJ">New Jersey</option>
							<option value="NM">New Mexico</option>
							<option value="NY">New York</option>
							<option value="NC">North Carolina</option>
							<option value="ND">North Dakota</option>
							<option value="OH">Ohio</option>
							<option value="OK">Oklahoma</option>
							<option value="OR">Oregon</option>
							<option value="PA">Pennsylvania</option>
							<option value="RI">Rhode Island</option>
							<option value="SC">South Carolina</option>
							<option value="SD">South Dakota</option>
							<option value="TN">Tennessee</option>
							<option value="TX">Texas</option>
							<option value="UT">Utah</option>
							<option value="VT">Vermont</option>
							<option value="VA">Virginia</option>
							<option value="WA">Washington</option>
							<option value="WV">West Virginia</option>
							<option value="WI">Wisconsin</option>
							<option value="WY">Wyoming</option>
						</select>
					</div>


					<label class="col-lg-1 control-label">Zip Code:</label>
					<div class="col-lg-2">
						<input type="text" class="form-control" name="zipCode"
							value="${zipCode}">
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