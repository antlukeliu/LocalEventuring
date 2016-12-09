<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Venue Added!</title>
</head>
<h1>Venue Added</h1>
<body>

<h1><c:out value="${venuename }"/></h1><br>
<c:out value="${roomsize }"/><br>
<c:out value="${capacity }"/><br>
<c:out value="${price }"/><br>
<img src = "${image }"><br>
${calendar }<br>
<c:out value="${description }"/><br> 
</body>
</html>