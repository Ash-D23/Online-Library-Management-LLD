package auth;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import model.SellerUser;
import util.HibernateUtil;

public class SellerPasswordStrategy implements SellerLoginStrategy{
	
	Session session = null;

	@Override
	public SellerUser login() {
		// TODO Auto-generated method stub
		System.out.println("Login");
		System.out.println("-----");
		
		System.out.print("Enter userId: ");
		Scanner input = new Scanner(System.in);
		int userId = input.nextInt();
		
		System.out.print("Enter Password: ");
		String password = input.next();
		
		SellerUser user = null;
		
		try {
			session = HibernateUtil.getSession();
			user = session.get(SellerUser.class, userId);
			
			if(user != null && !user.getPassword().equals(password)) {
				return null;
			}
			
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

}
