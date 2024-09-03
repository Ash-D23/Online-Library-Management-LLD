package system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Inventory.Inventory;
import OrderManagement.OrderManager;
import model.Book;
import model.BuyerUser;
import model.Cart;
import model.Order;
import model.SellerUser;
import util.BookUtil;
import util.HibernateUtil;
import util.OrderUtil;

public class BookManagementSystem {
	
	private static BookManagementSystem instance;
	
	private Inventory inventory = Inventory.getInstance();
	
	Session session = HibernateUtil.getSession();
	
	Transaction transaction = null;
	
	private BookManagementSystem() {
		
	}
	
	public static BookManagementSystem getinstance() {
		if(instance == null) {
			instance = new BookManagementSystem();
		}
		
		return instance;
	}
	
	public void searchBooks(){
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the Keyword: ");
		
		String keyword = input.next();
		
		List<Book> bookList = inventory.searchBooks(keyword);
		
		BookUtil.ViewBooks(bookList);
	}
	
	public void addBook(SellerUser User) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter Book Name: ");
		
		String bookName = input.next();
		
		System.out.print("Enter Book Price: ");
		
		Double bookPrice = input.nextDouble();
		
		System.out.print("Enter Book Category: ");
		
		String bookCategory = input.next();
		
		Book book = new Book(bookName, bookPrice, bookCategory, User);
		
//		System.out.print("Enter the path: ");
//		
//		String filePath = input.next();
		
		byte[] pdfContent = null;

		try (FileInputStream fis = new FileInputStream("LLD_HACKATHON.pdf")) {
			pdfContent = new byte[(int) fis.available()];
			fis.read(pdfContent);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		book.setPdf(pdfContent);
		
		int bookId = inventory.addBook(book);
		
		System.out.println("Book Created: " + bookId);
	}
	
	public void viewAllBooks() {
		List<Book> bookList = inventory.getAllBooks();
		
		BookUtil.ViewBooks(bookList);
	}

	public void addToCart(BuyerUser user) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter Book ID: ");
		
		int bookId = input.nextInt();
		
		Book book = inventory.getBook(bookId);
		
		// Update user's Cart and Save session to DB
		try {
			transaction = session.beginTransaction();
			Cart cart = user.getCart();
			
			cart.addCartItem(book);
			
			session.save(cart);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			transaction.commit();
		}
		
		System.out.println("Book Added to Cart");
	}

	public void placeOrder(BuyerUser user) {
		
		Set<Book> bookItems = new HashSet<Book>();
		bookItems.addAll(user.getCart().getBookItems());
		
		if(bookItems.size() != 0) {
			
			Double amount = (double) 0;
			
			for (Book book : bookItems) {
				  amount += book.getPrice();
			}
			
			Integer id = OrderManager.getInstance().createOrder(bookItems, amount, user);
			
			System.out.println("Order placed ID: " + id);
			
		}
		
	}

	public void downloadBook(BuyerUser user) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter Book ID: ");
		
		int bookId = input.nextInt();
		
		// check if book id is present in user book list
		
		boolean isBookInUserBookList = user.checkMyBook(bookId);
		
		if(!isBookInUserBookList) {
			System.out.println("Book is not user's");
			return;
		}
		
		Book book = inventory.getBook(bookId);
		
		FileOutputStream fos = null;
		
		try {

			if (book != null) {
				
				fos = new FileOutputStream("output/"+book.getTitle()+".pdf");
				fos.write(book.getPdf());

				fos.flush();
				
				System.out.println("Book Downloaded in output folder");

				
			} else {
				System.out.println("Book not found");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public Book getBookById(int i) {
		// TODO Auto-generated method stub
		return inventory.getBook(i);
	}

	public void viewBooksBySeller(SellerUser user) {
		// TODO Auto-generated method stub
		List<Book> books = inventory.getBooksBySeller(user.getUserId());
		
		BookUtil.ViewBooks(books);
	}

	public void viewBooksbyCategory() {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the Category: ");
		
		String keyword = input.next();
		
		List<Book> bookList = inventory.searchBooksByCategory(keyword);
		
		BookUtil.ViewBooks(bookList);
	}

	public void viewOrdersByUser(BuyerUser user) {
		// TODO Auto-generated method stub
		Query query = session.createQuery("FROM Order where user= :id");
		query.setLong("id", user.getUserId());
		List<Order> orderList = query.list();
		
		OrderUtil.viewOrders(orderList);
	}
	
}
