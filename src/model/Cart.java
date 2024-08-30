package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartID;
	
	@OneToMany(cascade = CascadeType.ALL)	
	private Set<Book> bookItems;
	
	public Cart() {
		this.bookItems = new HashSet<Book>();
	}

	public Integer getCartID() {
		return cartID;
	}

	public void setCartID(Integer cartID) {
		this.cartID = cartID;
	}

	public Set<Book> getBookItems() {
		return bookItems;
	}

	public void setBookItems(Set<Book> bookItems) {
		this.bookItems = bookItems;
	}

	public void addCartItem(Book book) {
		// TODO Auto-generated method stub
		
		this.bookItems.add(book);
		
	}

	public void emptyCart() {
		// TODO Auto-generated method stub
		this.bookItems.clear();
	}
	
	
}
