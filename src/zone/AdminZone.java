package zone;

import java.util.Scanner;

import auth.AdminLoginManager;
import auth.LoginManagerFacade;
import model.AdminUser;
import model.BuyerUser;

public class AdminZone implements Zone {
	
	private AdminUser authenticate() {
		boolean loginflag = false;
		AdminUser user = null;
		
		LoginManagerFacade lmf = new LoginManagerFacade();
		
		while(!loginflag) {
			System.out.println("Authenticate: ");
			System.out.println("1. Login");
			System.out.println("2. Exit");
			
			System.out.print("Enter: ");
			
			Scanner input = new Scanner(System.in);
			
			int authChoice = input.nextInt();
			
			if(authChoice == 1) {
				user = lmf.getAdminLoginManager().login();
				if(user == null) {
					System.out.println("Login Failed, Try again");
				}else {
					System.out.println("Login Succesfull");
					loginflag = true;
				}
			}else {
				System.exit(0); 
			}
		}
		
		return user;
	}

	@Override
	public void view() {
		System.out.println("Admin Zone");
		System.out.println("----------");
		
		AdminUser user = authenticate();
		
		AdminLoginManager alm = AdminLoginManager.getInstance();
		
		while(true) {
			System.out.println("Options:");
			System.out.println("--------");
			System.out.println("1. Create Seller");
			System.out.println("2. Create Admin");
			System.out.println("3. Exit");
			
			System.out.print("Enter: ");
			
			Scanner input = new Scanner(System.in);
			
			int option = input.nextInt();

			switch(option) {
			case 1:
				alm.createSeller();
				break;
			case 2:
				alm.createAdmin();
				break;
			case 3:
				System.exit(0);
			}
		}
	}

}
