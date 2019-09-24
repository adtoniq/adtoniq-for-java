package com.adtoniq.api;


/** This simple class implements a singleton pattern to provide access to Adtoniq services
 * and cache Adtoniq parameters across all pages
 * @author David
 *
 */
public class AdtoniqLauncher {
	/** This singleton points to the single instance of adtoniq. Customize this code here to set
	 * your API key.
	 */
	public final static Adtoniq adtoniq = new Adtoniq("1716ef08-65db-4f55-83a9-d2c624c1475a") {
		
		/** When Adtoniq JavaScript is updated, your pages will contain the new script inline within
		 *  the head section of your pages. If you need to update your page cache or CDN when Adtoniq updates its JavaScript,
		 *  implement that code here. If your cache automatically updates itself when your page
		 *  content changes, you don't need to implement anything here. 
		 */
		@Override
		public void updatePageCache() {
			
		}
	};
}
