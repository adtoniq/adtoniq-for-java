package com.adtoniq.api;

import java.io.DataOutputStream;
import java.io.StringWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import com.adtoniq.api.IAdtoniq;

public class Adtoniq implements IAdtoniq {
	// Set the apiKey to the API Key you receive from Adtoniq.
	private String apiKey = "";
	
	private String javaScript = "";
	private String version = "Java 1.0.2";
	
	public static Adtoniq singleton = null;

	/** Construct the Adtoniq singleton and initialize it
	 * @param apiKey Your unique API key, obtained from Adtoniq when you register
	 */
	public Adtoniq(String apiKey) {
		this.apiKey = apiKey;
		singleton = this;
		getLatestJavaScript();
	}
	
	public void processRequest(HttpServletRequest request) {
		String adtoniqAPIKey = getQueryArg(request, "adtoniqAPIKey");
		String adtoniqNonce = getQueryArg(request, "adtoniqNonce");
		
		if (adtoniqAPIKey.equals(apiKey) && adtoniqNonce.length() > 0)
			getLatestJavaScript(adtoniqNonce);
	}
	
	
	/** By default, do nothing to update page caches. Override this method to
	 *  manually update your cache / CDN when the JavaScript is updated.
	 */
	public void updatePageCache() {
		
	}

	private void getLatestJavaScript(String nonce) {
		String ret = executePost("https://integration.adtoniq.com/api/v1/", "operation=update&apiKey="+apiKey+"&version="+version+"&nonce="+nonce);
		if (ret.length() > 0) {
			javaScript = ret;
			updatePageCache();
		} else
			System.err.println("Error initializing Adtoniq for Java.");
	}

	public void getLatestJavaScript() {
		getLatestJavaScript("");
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getJavaScript() {
	    String ret = javaScript;
	    return ret;
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
		return "";
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

}
