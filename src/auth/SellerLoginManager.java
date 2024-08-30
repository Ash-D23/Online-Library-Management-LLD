package auth;

import model.SellerUser;

public class SellerLoginManager {
	
	private static SellerLoginManager instance = null;
	
	private SellerLoginStrategy loginStrategy;
	
	private SellerLoginManager(){
		
	}
	
	public static SellerLoginManager getInstance() {
		if(instance == null) {
			instance = new SellerLoginManager();
		}
		
		return instance;
	}
	
	public SellerUser login() {
		loginStrategy = new SellerPasswordStrategy();
		return loginStrategy.login();
		
	}
	
}
