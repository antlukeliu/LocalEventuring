<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert Title Here</title>
</head>
<body>
<!--  "to" field is FROM the venue owner, must get from database
"from" field will be filled in : create an input field 
"subject" user input 
"content" will be a text box"  -->
<form name="addform" action="email" method="post">
<div class="form-group">

<input type = "hidden" name = "toEmail" value = ${email}> 
					<label for="" class="col-lg-2 control-label"></label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="yourEmail"
							placeholder="Your Email">
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-lg-2 control-label"></label>
					<div class="col-lg-10">
						<input type="text" class="form-control" name="Subject"
							placeholder="Subject">
					</div>
				</div>
					<div class="form-group">
					<label for="textArea" class="col-lg-2 control-label"></label>
					<div class="col-lg-10">
					<span class = "help-block">Message</span>
						<textarea class="form-control" rows="5" id="textArea"
							name="Message"></textarea>
					
					</div>
				</div>
</form>
</body>
</html>