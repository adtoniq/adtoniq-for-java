<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.adtoniq.api.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- This is the first place to integrate Adtoniq, to inject content into the head section -->
	<%=AdtoniqLauncher.adtoniq.getHeadCode(request) %>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Adtoniq for Java</title>
	<style>
	img {
	    display: block;
	    margin: auto;
	}
	</style>
</head>
<body>
	<!-- This is the second place to integrate Adtoniq, to inject content into the body section -->
	<%=AdtoniqLauncher.adtoniq.getBodyCode()%>
	
	<!-- These three divs will have their display style set to 'block' based on whether or not an ad blocker has been detected -->
	<div>
		<div class="adtoniq_adblocked" style="display: none; color: red;">
			<img src="//static-42andpark-com.s3-us-west-2.amazonaws.com/images/stop.png" alt="Stop" width="300" height="300" />
			<h1 style="text-align: center;">You are using an ad blocker!!!!</h1>
		</div>
		<div class="adtoniq_nonblocked" style="display: none; color: green;">
			<img src="//static-42andpark-com.s3-us-west-2.amazonaws.com/images/go.png" alt="Stop" width="300" height="300" />
			<h1 style="text-align: center;">You are not using an ad blocker</h1>
		</div>
	</div>
</body>
</html>