package model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderTable")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
	
	@OneToMany(cascade = CascadeType.ALL)	
	private Set<Book> bookItems;
	
	@OneToOne(cascade = CascadeType.ALL)
	private BuyerUser user;
	
	private Double amount;
	
	private String status;
	
	@OneToOne(cascade = CascadeType.ALL)
	private BookTransaction transaction;

	public Order(Set<Book> bookItems, Double amount, BuyerUser user) {
		this.bookItems = bookItems;
		this.amount = amount;
		this.user = user;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Set<Book> getBookItems() {
		return bookItems;
	}

	public void setBookItems(Set<Book> bookItems) {
		this.bookItems = bookItems;
	}

	public BuyerUser getUser() {
		return user;
	}

	public void setUser(BuyerUser user) {
		this.user = user;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BookTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(BookTransaction transaction) {
		this.transaction = transaction;
	}
	
	public Order() {
		
	}
}
