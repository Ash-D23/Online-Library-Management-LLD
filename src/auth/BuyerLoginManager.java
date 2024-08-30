package auth;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.BuyerUser;
import model.Cart;
import util.HibernateUtil;

public class BuyerLoginManager {
	
	private static BuyerLoginManager instance = null;
	
	private BuyerLoginStrategy loginStrategy;
	
	Session session = null;
	Transaction transaction = null;
	
	private BuyerLoginManager(){
		
	}
	
	public static BuyerLoginManager getInstance() {
		if(instance == null) {
			instance = new BuyerLoginManager();
		}
		
		return instance;
	}
	
	public BuyerUser login() {
		
		loginStrategy = new BuyerPasswordStrategy();
		return loginStrategy.login();
	}
	
	public int SignUp() {
		
		System.out.println("Sign Up");
		System.out.println("-------");
		
		System.out.print("Enter name: ");
		Scanner input = new Scanner(System.in);
		String name = input.next();
		
		System.out.print("Enter Password: ");
		String password = input.next();
		
		int userId = -1;
		
		boolean flag = false;
		
		try {
			session = HibernateUtil.getSession();

			transaction = session.beginTransaction();
			
			Cart cart = new Cart();
			
			BuyerUser newUser = new BuyerUser(name, password, "buyer", cart);

			userId = (Integer) session.save(newUser);

			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("User Sign Up Succesfull");		
			} else {
				transaction.rollback();
				System.out.println("User Sign Up UnSuccesfull");
			}
			//HibernateUtil.closeSession(session);
			//HibernateUtil.closeSessionFactory();
		}

		return userId;
		
	}
}
