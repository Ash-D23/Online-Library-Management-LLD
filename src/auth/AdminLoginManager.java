package auth;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.AdminUser;
import model.SellerUser;
import util.HibernateUtil;

public class AdminLoginManager {
	
	private static AdminLoginManager instance = null;
	
	Session session = null;
	Transaction transaction = null;
	
	private AdminLoginManager(){
		
	}
	
	public static AdminLoginManager getInstance() {
		if(instance == null) {
			instance = new AdminLoginManager();
		}
		
		return instance;
	}
	
	public AdminUser login() {
		System.out.println("Login");
		System.out.println("-----");
		
		System.out.print("Enter userId: ");
		Scanner input = new Scanner(System.in);
		int userId = input.nextInt();
		
		System.out.print("Enter Password: ");
		String password = input.next();
		
		AdminUser user = null;
		
		try {
			session = HibernateUtil.getSession();
			user = session.get(AdminUser.class, userId);
			
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
	
	public void createAdmin() {
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
			
			AdminUser newUser = new AdminUser(name, password, "admin");

			userId = (Integer) session.save(newUser);

			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object saved....");		
			} else {
				transaction.rollback();
				System.out.println("object not saved...");
			}
			//HibernateUtil.closeSession(session);
			//HibernateUtil.closeSessionFactory();
		}
		
	}
	
	public void createSeller() {
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
			
			SellerUser newUser = new SellerUser(name, password, "seller");

			userId = (Integer) session.save(newUser);

			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object saved....");		
			} else {
				transaction.rollback();
				System.out.println("object not saved...");
			}
			//HibernateUtil.closeSession(session);
			//HibernateUtil.closeSessionFactory();
		}
		
	}
}
