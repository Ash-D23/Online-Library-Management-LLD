package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	private String title;
	
	@Lob
	private byte[] pdf;
	
	private Double price;
	
	private String category;
	
	
	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}

	@OneToOne(cascade = CascadeType.ALL)	
	private SellerUser seller;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public byte[] getPdf() {
		return pdf;
	}


	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public SellerUser getSeller() {
		return seller;
	}


	public void setSeller(SellerUser seller) {
		this.seller = seller;
	}
	
	public Book() {
		
	}
	
	public Book(String title, Double price, String category, SellerUser user){
		this.title = title;
		this.price = price;
		this.category = category;
		this.seller = user;
	}
	
}
