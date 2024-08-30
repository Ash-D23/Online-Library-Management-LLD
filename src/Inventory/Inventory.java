package Inventory;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Book;
import util.HibernateUtil;

public class Inventory {
	
	private static Inventory instance;
	
	Session session = HibernateUtil.getSession();
	
	private Inventory() {
		
	}
	
	public static Inventory getInstance() {
		if(instance == null) {
			instance = new Inventory();
		}
		
		return instance;
	}
	
	public List<Book> searchBooks(String keyword){
		Query query = session.createQuery("FROM Book WHERE title LIKE :i");
		query.setParameter("i", "%"+keyword+"%");
		
		List<Book> bookList = query.list();
		return bookList;
		
	}
	
	public int addBook(Book book) {
		int bookId = -1;
		try {
			bookId =  (int) session.save(book);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return bookId;
		
	}

	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		Query query = session.createQuery("from Book");
		List<Book> bookList = query.list();
		
		return bookList;
	}

	public Book getBook(int bookId) {
		
		Book book = session.get(Book.class, bookId);
		
		return book;
	}

	public List<Book> getBooksBySeller(Integer userId) {
		Query query = session.createQuery("FROM Book WHERE seller= :id");
		query.setLong("id", userId);
		
		List<Book> bookList = query.list();
		return bookList;
	}

	public List<Book> searchBooksByCategory(String category) {
		// TODO Auto-generated method stub
		Query query = session.createQuery("FROM Book WHERE category= :param");
		query.setParameter("param", category);
		
		List<Book> bookList = query.list();
		return bookList;
	}
	
}
