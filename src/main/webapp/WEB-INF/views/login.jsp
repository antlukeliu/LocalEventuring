<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
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
					<li><a href="searhresults">Venues</a></li>
					<li><a href="accountpage">My Account</a></li>
					<li><a href="login">Login/Logout</a></li>				</ul>
			</div>
		</div>
	</nav>


<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="443097452223-brgf9pqihosda3gtc1bmd0f3aj4muh2h.apps.googleusercontent.com">
<div class="g-signin2" data-onsuccess="onSignIn"></div>
<form name="login" method="POST" action="accountpage">
<input type="hidden" name="email">
<input type="hidden" name="fullName">
<input type="hidden" name="id">
<button type="submit" class="btn btn-primary">Continue</button>
</form>
<script>
function onSignIn(googleUser) {
      var profile = googleUser.getBasicProfile();
      var id_token = googleUser.getAuthResponse().id_token;
      document.getElementsByName("email")[0].value=profile.getEmail();
      document.getElementsByName("fullName")[0].value=profile.getName();
      document.getElementsByName("id")[0].value=id_token;
    }
    
</script>

<h3>${error}</h3>
<h3>Disclosure: AdBlock needs to be off in order to have access to accounts</h3>
<div class="footer">Local Eventuring <a href="aboutus" >Developers</a></div>
</body>
</html>