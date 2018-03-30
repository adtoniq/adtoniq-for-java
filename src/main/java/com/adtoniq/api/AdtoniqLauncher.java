package com.adtoniq.api;


/** This simple class implements a singleton pattern to provide access to Adtoniq services
 * and cache Adtoniq parameters across all pages
 * @author David
 *
 */
public class AdtoniqLauncher {
	/** This points to the single instance of adtoniq. Customize this class here to set
	 * your API key and fully qualified domain name of the front end of your website.
	 * If you are using Adtoniq for Google Analytics, add your GA Id as well, or omit
	 * that line of code.
	 */
	public final static Adtoniq adtoniq = new Adtoniq();
	
	static {
	    adtoniq.setApiKey("put-your-adtoniq-cloud-key-here");
	    adtoniq.setGoogleAnalyticsId("UA-XXXXXXXX-X");	// Optional - use if you are using Adtoniq for Google Analoytics features
	    adtoniq.getLatestJavaScript();	// Must call this once to fetch latest JavaScript definitions
	}
}
