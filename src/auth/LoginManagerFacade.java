package auth;

public class LoginManagerFacade {
	
		public BuyerLoginManager getBuyerLoginManager() {
			return BuyerLoginManager.getInstance();
		}
		
		public SellerLoginManager getSellerLoginManager() {
			return SellerLoginManager.getInstance();
		}
		
		public AdminLoginManager getAdminLoginManager() {
			return AdminLoginManager.getInstance();
		}
}
