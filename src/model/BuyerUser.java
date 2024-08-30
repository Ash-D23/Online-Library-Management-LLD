package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import util.BookUtil;
import util.OrderUtil;

@Entity
public class BuyerUser extends User{
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;
	
	@OneToMany(cascade = CascadeType.ALL)	
	private Set<Book> bookItems;

	public Cart getCart() {
		return cart;
	}

	public Set<Book> getBookItems() {
		return bookItems;
	}

	public void setBookItems(Set<Book> bookItems) {
		this.bookItems = bookItems;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public BuyerUser() {
		super();
	}
	
	public BuyerUser(String name, String password, String role, Cart cart) {
		super(name, password, role);
		this.cart = cart;
	}

	public void viewCart() {
		
		Set<Book> books = this.cart.getBookItems();
		
		List<Book> booklist = new ArrayList<Book>(books);

		
		BookUtil.ViewBooks(booklist);
		
	}

	public void viewMyBooks() {
		// TODO Auto-generated method stub
		List<Book> booklist = new ArrayList<Book>(this.bookItems);
		
		BookUtil.ViewBooks(booklist);
	}
	
	public void addBook(Book book) {
		bookItems.add(book);
	}
	

	
}
