package auth;

import model.BuyerUser;

public interface BuyerLoginStrategy {
	public BuyerUser login();
}
