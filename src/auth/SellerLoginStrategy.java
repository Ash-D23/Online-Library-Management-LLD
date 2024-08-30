package auth;

import model.SellerUser;

public interface SellerLoginStrategy {
	public SellerUser login();
}
