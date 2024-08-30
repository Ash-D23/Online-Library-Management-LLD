package OrderManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Book;
import model.BuyerUser;
import model.Cart;
import model.Order;
import model.BookTransaction;
import model.User;
import payment.PaymentManager;
import util.HibernateUtil;

public class OrderManager {
	
	private static OrderManager instance = null;
	
	Session session = HibernateUtil.getSession();
	
	Transaction transaction = null;
	
	private OrderManager() {
		
	}
	
	public static OrderManager getInstance() {
		
		if(instance == null) {
			instance = new OrderManager();
		}
		
		return instance;
	}

	public Integer createOrder(Set<Book> bookItems, Double amount, BuyerUser user) {
		int id = -1;
		try {
			transaction = session.beginTransaction();
			
			Order order = new Order(bookItems, amount, user);
			
			BookTransaction result = PaymentManager.getInstance().process(amount);
			
			order.setStatus(result.getStatus());
			
			order.setTransaction(result);
			
			id = (int) session.save(order);
			
			if(result.getStatus() == "complete") {
				for(Book b : bookItems) {
					user.addBook(b);
				}
			}
			
			Cart cart = user.getCart();
			cart.emptyCart();
			
			session.save(cart);
			
			session.save(user);
			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			transaction.commit();
		}
		
		return id;
		
	}
	
	public List<Order> getOrder(Integer id){
		Query query = session.createQuery("FROM Order WHERE user=:id");
		query.setParameter("id", id);
		
		List<Order> orderList = query.list();
		return orderList;
	}
}
