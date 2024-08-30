package model;

import javax.persistence.Entity;

@Entity
public class SellerUser extends User{
	
	public SellerUser(String name, String password, String role) {
		super(name, password, role);
		// TODO Auto-generated constructor stub
	}
	
	public SellerUser() {
		super();
	}

	
}
