<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="${url}">
<title>Login</title>

<link rel="stylesheet" href="resources/style.css"> 
<style>

h3{
	text-align: center;
}

#login {
   margin: 0 auto; 
   text-align: center;
   vertical-align: middle;
   padding: 50px;
   }
#buttoncenter{
	
	margin: 25px 565px;
}   
  
</style>
<script>
function onSignIn(googleUser) {
      var profile = googleUser.getBasicProfile();
      var id_token = googleUser.getAuthResponse().id_token;
      document.getElementsByName("email")[0].value=profile.getEmail();
      document.getElementsByName("fullName")[0].value=profile.getName();
      document.getElementsByName("id")[0].value=id_token;
    }
    
function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
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
					<li><a href="http://sample-env.dzxmm3mvps.us-east-2.elasticbeanstalk.com">Home</a></li>
					<li><a href="searchresults">Venues</a></li>
					<li><a href="accountpage">My Account</a></li>
					<li><a href="login">Login/Logout</a></li>				</ul>
			</div>
		</div>
	</nav>


<div id="login">
<div class="g-signin2" data-onsuccess="onSignIn" id="buttoncenter"></div>

<button class="btn btn-warning" onclick="signOut();">Sign Out of Google</button>
<form name="login" method="POST" action="accountpage" >

<input type="hidden" name="email">
<input type="hidden" name="fullName">
<input type="hidden" name="id">
<br>
<br>
<button type="submit" class="btn btn-primary">Continue</button>
</form>
</div>

<h3>${error}</h3>
<h3>Disclosure: AdBlock needs to be off in order to have access to accounts</h3>
<div class="footer">Local Eventuring <a href="aboutus" >Developers</a></div>
</body>
</html>