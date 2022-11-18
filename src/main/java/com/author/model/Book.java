package com.author.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int bId;
	
	private String logo;

	private String title;
	
	private String category;
	
	private Double price;
	
	private String author;
	
	private String publisher;
	
	private String publishedDate;
	
	private String chapterOrContent;
	
	private boolean isActive ;
	
	private Integer aId;
	

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}
	
	public int getaId() {
		return aId;
	}

	public void setaId(int aId) {
		this.aId = aId;
	}

	
	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getChapterOrContent() {
		return chapterOrContent;
	}

	public void setChapterOrContent(String chapterOrContent) {
		this.chapterOrContent = chapterOrContent;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Book(int bId, String logo, String title, String category, Double price, String author, String publisher,
			String publishedDate, String chapterOrContent, boolean isActive,int aId ) {
		super();
		this.bId = bId;
		this.logo = logo;
		this.title = title;
		this.category = category;
		this.price = price;
		this.author = author;
		this.publisher = publisher;
		this.publishedDate = publishedDate;
		this.chapterOrContent = chapterOrContent;
		this.isActive = isActive;
		this.aId = aId;
		
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Book [bId=" + bId + ", logo=" + logo + ", title=" + title + ", category=" + category + ", price="
				+ price + ", author=" + author + ", publisher=" + publisher + ", publishedDate=" + publishedDate
				+ ", chapterOrContent=" + chapterOrContent + ", isActive=" + isActive + ", aId=" + aId + "]";
	}

	

	
	
	
	

}