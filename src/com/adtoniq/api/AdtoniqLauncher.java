package com.adtoniq.api;


/** This simple class implements a singleton pattern to provide access to Adtoniq services
 * and cache Adtoniq parameters across all pages
 * @author David
 *
 */
public class AdtoniqLauncher {
	/** This points to the single instance of adtoniq. Customize this call here to pass in
	 * your Adtoniq Cloud key. Get your cloud key at www.adtoniq.com.
	 */
	public final static Adtoniq adtoniq = new Adtoniq("your cloud key here");
}
