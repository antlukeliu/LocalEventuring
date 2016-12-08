<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>


<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="443097452223-brgf9pqihosda3gtc1bmd0f3aj4muh2h.apps.googleusercontent.com">
<div class="g-signin2" data-onsuccess="onSignIn"></div>
<form name="login" method="POST" action="accountpage">
<input type="hidden" name="email">
<input type="hidden" name="name">
<input type="hidden" name="id">
<input type="submit" value="continue">
</form>
<script>
function onSignIn(googleUser) {
      var profile = googleUser.getBasicProfile();
      var id_token = googleUser.getAuthResponse().id_token;
      document.getElementsByName("email")[0].value=profile.getEmail();
      document.getElementsByName("name")[0].value=profile.getName();
      document.getElementsByName("id")[0].value=id_token;
    }
    
</script>


</body>
</html>