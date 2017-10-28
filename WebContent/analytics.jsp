<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.adtoniq.api.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adtoniq for Java</title>
<style>
.adtoniq-analytics-panel {
	width: 100%;
	border: 1px solid #d7d7d7;
	padding: 10px;
	min-height: 300px;
	margin-bottom: 20px;
	border-radius: 0;
}

.adtoniq-analytics-panel iframe {
	width: 100%;
	height: 300px;
	border: 0;
	overflow: hidden;
}
</style>
</head>
<body>
	<!--
	 To protect the security of your API key, this invisible form is used to post your key
	 to the iframe rather than send it as a query arg where it is visible to potential
	 hackers.
	 -->
	<form id='anal-form' target='anal-iframe' method='post'
		action='https://integration.adtoniq.com/adtoniqAnalytics.jsp'>
		<input type='hidden' name='apikey' value='<%=AdtoniqLauncher.adtoniq.getApiKey()%>'>
	</form>
	<div class="adtoniq-analytics-panel">
		<iframe name='anal-iframe'></iframe>
	</div>
	<script type="text/javascript">
   		document.getElementById('anal-form').submit();
	</script>
</body>
</html>