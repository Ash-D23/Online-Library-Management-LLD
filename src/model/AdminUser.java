package model;

import javax.persistence.Entity;

@Entity
public class AdminUser extends User {

	public AdminUser(String name, String password, String role) {
		super(name, password, role);
		// TODO Auto-generated constructor stub
	}
	
	public AdminUser() {
		super();
	}

}
