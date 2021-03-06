<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
<link rel="stylesheet" href="resources/style.css">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta charset="utf-8">
<style>
#map {
	height: 500px;
	width: 600px;
	border-radius: 4px;
	border: 3px solid black;
}

.controls {
	background-color: #fff;
	border-radius: 2px;
	border: 1px solid transparent;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
	box-sizing: border-box;
	font-family: Roboto;
	font-size: 15px;
	font-weight: 300;
	height: 29px;
	margin-left: 17px;
	margin-top: 10px;
	outline: none;
	padding: 0 11px 0 13px;
	text-overflow: ellipsis;
	width: 225px;
}

.controls:focus {
	border-color: black;
}

.title {
	font-weight: bold;
}

#infowindow-content {
	display: none;
}

#map #infowindow-content {
	display: inline;
}

h2 {
	font-size: 30px;
	text-align: left-center;
	text-weight: bold;
	text-decoration: underline;
	color: green;
}
</style>

		<script>
			var map;

			function initMap() {
				var detroit = new google.maps.LatLng(${lat}, ${lng});
				map = new google.maps.Map(document.getElementById('map'), {
					zoom : 15,
					center : detroit
				});
				var marker = new google.maps.Marker({
					position : detroit,
					map : map,
					animation : google.maps.Animation.DROP,
					draggable : true

				});
				initAutocomplete();
			}
			function initAutocomplete() {
				//   var map = new google.maps.Map(document.getElementById('map'), {
				//   center: {lat: 42.3313889, lng: -83.0458333},
				// zoom: 10,
				//map: map,
				// });

				// Create the search box and link it to map.
				var input = document.getElementById('user-input');
				var searchBox = new google.maps.places.SearchBox(input);
				map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

				map.addListener('bounds_changed', function() {
					searchBox.setBounds(map.getBounds());
				});

				var markers = [];

				searchBox.addListener('places_changed', function() {
					var place = searchBox.getPlaces();

					if (place.length == 0) {
						return;
					}

					// Clear off map.
					// markers.forEach(function(marker) {
					//marker.setMap(null);
					// });
					// markers = [];

					// For each place, get the icon, name and location.
					var bounds = new google.maps.LatLngBounds();
					place.forEach(function(place) {

						if (!place.geometry) {
							console.log("Returned place contains no geometry");
							return;
						}
						var icon = {
							url : place.icon,
							size : new google.maps.Size(71, 71),
							origin : new google.maps.Point(0, 0),
							anchor : new google.maps.Point(17, 34),
							scaledSize : new google.maps.Size(25, 25)
						};
						var detroit = new google.maps.LatLng(
								place.geometry.location.lat(),
								place.geometry.location.lng());
						map.setCenter(place.geometry.location);

						//Create a marker for each place.
						markers.push(new google.maps.Marker({
							map : map,
							icon : icon,
							title : place.name,
							zoom: 10, 
							position : place.geometry.location
						}));

						if (place.geometry.viewport) {
							// Only geocodes have viewport.
							bounds.union(place.geometry.viewport);
						} else {
							bounds.extend(place.geometry.location);
						}
					});
					map.fitBounds(bounds);
				});
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
				<li><a
					href="http://sample-env.dzxmm3mvps.us-east-2.elasticbeanstalk.com">Home</a></li>
				<li><a href="searchresults">Venues</a></li>
				<li><a href="accountpage">My Account</a></li>
				<li><a href="login">Login/Logout</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<img src="${photoLink }">
	<br>

	<div class="container">
		<h1>
			<b>${venueName }</b>
		</h1>
		<br>

		<div class="form-group">
			<div class="col-lg-3">
				<h3>
					<b>Size of Venue (sq. ft):</b>
				</h3>
			</div>
			<div class="col-lg-3">
				<h3>${roomSize }</h3>
			</div>
			<div class="col-lg-3">
				<h3>
					<b>Price Per Hour:</b>
				</h3>
			</div>
			<div class="col-lg-3">
				<h3>${price }</h3>
			</div>
		</div>

		<div class="form-group">
			<div class="col-lg-3">
				<h3>
					<b>Capacity:</b>
				</h3>
			</div>
			<div class="col-lg-3">
				<h3>${capacity }</h3>
			</div>
			<div class="col-lg-3">
				<h3>
					<b>Category:</b>
				</h3>
			</div>
			<div class="col-lg-3">
				<h3>${category }</h3>
			</div>
		</div>
		<div class="form-group">
			<div class="form-group col-lg-12">
				<h3>
					<b>Venue Description:</b>
				</h3>

				<h4>${description }</h4>
			</div>
		</div>

	<br>
	<div class="form-group">
	<input id="user-input" class="controls" type="text"
		placeholder="Enter a location">
	<div id="map"></div>
	</div>
	<br>
	<div class="form-group">
	
		${calendarLink}	
	</div>

	<br>
		<form name="addform" action="emailform" method="post">
			<input type="hidden" name="venueId" value=${venueId}> <input
				type="hidden" name="toEmail" value=${email}>
			<div class="form-group">

				<div class="col-lg-8">
					<input type="text" class="form-control" name="yourEmail"
						placeholder="Your Email">
				</div>

			</div>
			<div class="form-group">
				<div class="col-lg-8">
					<input type="text" class="form-control" name="Subject"
						placeholder="Subject">
				</div>

			</div>
			<div class="form-group">

				<div class="col-lg-8">
					<span class="help-block">Message</span>
					<textarea class="form-control" rows="5" id="textArea"
						name="Message"></textarea>
				</div>

			</div>
			<div class="col-lg-10">
				<button type="submit" class="btn btn-primary-default">Send
					Email</button>
			</div>

		</form>
	
	</div>

		<script
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDSomm5E27N6WDyVMe8579tWLJTG2M9nC8&libraries=places&callback=initMap"
			async defer></script>

	


	<div class="footerprofile">
		Local Eventuring <a href="aboutus">Developers</a>
	</div>
</body>
</html>