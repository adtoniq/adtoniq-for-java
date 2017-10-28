package com.adtoniq.api;

import java.io.DataOutputStream;
import java.io.StringWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

public class Adtoniq {
	// Set the apiKey to the API Key you receive from Adtoniq.
	private String cloudKey = "";

	// Set pollForUpdates to true to 
	private boolean pollForUpdates = false;
	
	private String javaScript = "";
	private String version = "Java 1.0.0";
	
	public static Adtoniq singleton = null;

	/** Construct the Adtoniq singleton and initiaqlize it
	 * @param apiKey Your unique API key, obtained from Adtoniq when you register
	 * @param fqdn The fully qualified domain of the front end of your website
	 */
	public Adtoniq(String key) {
		this.cloudKey = key;
		singleton = this;
		getLatestJavaScript();
	}

	public void register() {
		
	}
	
	public void processRequest(HttpServletRequest request) {
		String adtoniqAPIKey = getQueryArg(request, "adtoniqAPIKey");
		String adtoniqNonce = getQueryArg(request, "adtoniqNonce");
		
		if (adtoniqAPIKey.equals(cloudKey) && adtoniqNonce.length() > 0)
			getLatestJavaScript(adtoniqNonce);
	}

	private void getLatestJavaScript(String nonce) {
		String ret = executePost("https://integration.adtoniq.com/api/v1/", "operation=update&apiKey="+cloudKey+"&version="+version+"&nonce="+nonce);
		if (ret.length() > 0)
			javaScript = ret;
	}

	public void getLatestJavaScript() {
		getLatestJavaScript("");
	}

	public String getApiKey() {
		return cloudKey;
	}

	public void setApiKey(String apiKey) {
		this.cloudKey = apiKey;
	}

	public String getJavaScript() {
		return javaScript;
	}

	public void setJavaScript(String javaScript) {
		this.javaScript = javaScript;
	}
	
	/** Returns the HTML that should be inserted into the head section of the website
	 * @param request The HttpServletRequest that is currently be served
	 * @return The code that should be inserted into the head section
	 */
	public String getHeadCode(HttpServletRequest request) {
		processRequest(request);
		return getJavaScript();
	}
	
	/** Returns the HTML that should be inserted into the body section of the website
	 * @return The code that should be inserted into the body section
	 */
	public String getBodyCode() {
		return "<iframe id='aq-ch' src='//static-42andpark-com.s3.amazonaws.com/html/danaton3.html' width='1' height='1' style='width:1px;height:1px;position:absolute;left:-1000;' frameborder=0></iframe>";
	}

	private static String getQueryArg(HttpServletRequest request, String argName) {
		String ret = request.getParameter(argName);
		
		return ret == null ? "" : ret.trim();
	}

	private static String executePost(String targetURL, String urlParameters) {
		URL url;
		HttpsURLConnection serverConnection = null;
		try {
			// Create connection
			url = new URL(targetURL);
			serverConnection = (HttpsURLConnection) url.openConnection();
			serverConnection.setRequestMethod("POST");
			serverConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			serverConnection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

			serverConnection.setRequestProperty("Content-Length",
					"" + Integer.toString(urlParameters.getBytes().length));
			serverConnection.setRequestProperty("Content-Language", "en-US");

			serverConnection.setUseCaches(false);
			serverConnection.setDoInput(true);
			serverConnection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(serverConnection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			// Get Response
			StringWriter writer = new StringWriter();
			IOUtils.copy(serverConnection.getInputStream(), writer);
			return writer.toString().trim();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (serverConnection != null) {
				serverConnection.disconnect();
			}
		}
	}

	public boolean isPollForUpdates() {
		return pollForUpdates;
	}

	public void setPollForUpdates(boolean pollForUpdates) {
		this.pollForUpdates = pollForUpdates;
	}
}
