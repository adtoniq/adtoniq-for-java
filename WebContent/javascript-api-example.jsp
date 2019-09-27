<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.adtoniq.api.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<!-- This is the first place to integrate Adtoniq, to inject content into the head section -->
	<%=AdtoniqLauncher.adtoniq.getHeadCode(request) %>
	<!-- Example showing how to customize Adtoniq behavior on this page -->
	<script>
		adtoniq.setEnableAdtoniq(function() {
			return confirm("Should we enable Adtoniq?");
		})
		adtoniq.setShouldShowAds(function() {
			return confirm("Should we show ads?");
		})
	</script>
	<style>
	p, code {font-size: 24px;}
	</style>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>JavaScript API Example</title>
</head>
<body>
<p>The following script on this page shows how to use two of the Adtoniq API functions:</p>
<p>
 <pre>
    <code>
		adtoniq.setEnableAdtoniq(function() {
			return confirm("Should we enable Adtoniq?");
		})
    </code>
  </pre>
</p>
</body>
</html>