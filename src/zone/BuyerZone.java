package zone;

import java.util.List;
import java.util.Scanner;

import OrderManagement.OrderManager;
import auth.LoginManagerFacade;
import model.BuyerUser;
import model.Order;
import system.BookManagementSystem;
import util.OrderUtil;

public class BuyerZone implements Zone {
	
	private BuyerUser authenticate() {
		boolean loginflag = false;
		BuyerUser user = null;
		
		LoginManagerFacade lmf = new LoginManagerFacade();
		
		while(!loginflag) {
			System.out.println("Authenticate: ");
			System.out.println("1. Login");
			System.out.println("2. SignUp");
			System.out.println("3. Exit");
			
			System.out.print("Enter: ");
			
			Scanner input = new Scanner(System.in);
			
			int authChoice = input.nextInt();
			
			if(authChoice == 1) {
				user = lmf.getBuyerLoginManager().login();
				if(user == null) {
					System.out.println("Login Failed, Try again");
				}else {
					System.out.println("Login Succesfull");
					loginflag = true;
				}
			}else if(authChoice == 2) {
				int id = lmf.getBuyerLoginManager().SignUp();
				if(id == -1) {
					System.out.println("Sign Up failed please try again");
				}else {
					System.out.println("Sign Up Succesfull, User ID is " + id);
				}
			}else {
				System.exit(0); 
			}
		}
		
		return user;
	}
	
	@Override
	public void view() {
		
		System.out.println("Buyer Zone");
		System.out.println("----------");
		
		BuyerUser user = authenticate();
		
		while(true) {
			System.out.println("Options:");
			System.out.println("--------");
			System.out.println("1. Search Books");
			System.out.println("2. View All Books");
			System.out.println("3. Search Books by Category");
			System.out.println("4. Add Book to cart");
			System.out.println("5. View Cart");
			System.out.println("6. Place Order");
			System.out.println("7. View My Books");
			System.out.println("8. View My Orders");
			System.out.println("9. Download Book");
			System.out.println("10. Exit");
			
			System.out.print("Enter: ");
			
			Scanner input = new Scanner(System.in);
			
			int option = input.nextInt();
			
			buyerActions(option, user);
			
		}
	}

	private void buyerActions(int option, BuyerUser user) {
		BookManagementSystem bookManagementSystem = BookManagementSystem.getinstance();
		
		switch(option) {
		case 1:
			bookManagementSystem.searchBooks();
			break;
		case 2:
			bookManagementSystem.viewAllBooks();
			break;
		case 3:
			bookManagementSystem.viewBooksbyCategory();
			break;
		case 4:
			bookManagementSystem.addToCart(user);
			break;
		case 5:
			user.viewCart();
			break;
		case 6:
			bookManagementSystem.placeOrder(user);
			break;
		case 7:
			user.viewMyBooks();
			break;
		case 8:
			bookManagementSystem.viewOrdersByUser(user);
			break;
		case 9:
			bookManagementSystem.downloadBook(user);
			break;
		case 10:
			System.exit(0);
		default:
			System.out.println("Incorrect Choice");
			break;
		}
		
	}

}
