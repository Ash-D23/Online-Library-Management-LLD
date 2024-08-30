package zone;

import java.util.Scanner;

import auth.LoginManagerFacade;
import model.Book;
import model.BuyerUser;
import model.SellerUser;
import system.BookManagementSystem;

public class SellerZone implements Zone {
	
	private SellerUser authenticate() {
		boolean loginflag = false;
		SellerUser user = null;
		
		LoginManagerFacade lmf = new LoginManagerFacade();
		
		while(!loginflag) {
			System.out.println("Authenticate: ");
			System.out.println("1. Login");
			System.out.println("2. Exit");
			
			System.out.print("Enter: ");
			
			Scanner input = new Scanner(System.in);
			
			int authChoice = input.nextInt();
			
			if(authChoice == 1) {
				user = lmf.getSellerLoginManager().login();
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
		System.out.println("Seller Zone");
		System.out.println("----------");
		
		SellerUser user = authenticate();
		
		BookManagementSystem bms = BookManagementSystem.getinstance();
		
		while(true) {
			System.out.println("Options:");
			System.out.println("--------");
			System.out.println("1. Add Book");
			System.out.println("2. View My Books");
			System.out.println("3. Exit");
			
			System.out.print("Enter: ");
			
			Scanner input = new Scanner(System.in);
			
			int option = input.nextInt();
			
			switch(option) {
			case 1:
				bms.addBook(user);
				break;
			case 2:
				bms.viewBooksBySeller(user);
				break;
			case 3:
				System.exit(0);
			}
		}
	}
	

}
