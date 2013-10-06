package edu.sjsu.cmpe.library.domain;



import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;


public class BookLink {


	public String bookTitle;
	public String bookPublicationDate;
	
	public String bookLanguage;
	public String bookStatus;
	ArrayList<Reviews> bookReviews = new ArrayList<Reviews>();
	private int noOfPages;
	public ArrayList<Author> Bookauthors = new ArrayList();
	
	public BookLink(@JsonProperty("title") String bookTitle, @JsonProperty("publication-date") String bookPublicationDate, @JsonProperty("status") String bookStatus) {
		this.bookTitle = bookTitle;
		this.bookPublicationDate=bookPublicationDate;
		this.bookStatus = bookStatus;
	}
	
	
	
	public void setBookReviews(ArrayList<Reviews> bookReviews) {
		this.bookReviews = bookReviews;
	}
	public ArrayList<Reviews> getBookReviews() {
		return bookReviews;
	}
	
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	public void setBookLanguage(String bookLanguage) {
		this.bookLanguage = bookLanguage;
	}
	
	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
	
	
	public void setPublication_date(String bookPublicationDate) {
		this.bookPublicationDate = bookPublicationDate;
	}
	
	public void setNum_pages(int noOfPages) {
		this.noOfPages = noOfPages;
	}
	public ArrayList<Author> getBookAuthors() {
		return Bookauthors;
	}
	public void setAuthors(ArrayList<Author> Bookauthors) {
		this.Bookauthors = Bookauthors;
	}
	public String getBookLanguage() {
		return bookLanguage;
	}
	public int getNoOfPages() {
		return noOfPages;
	}
	public String getPublication_date() {
		return bookPublicationDate;
	}
	public String getBookStatus() {
		return bookStatus;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	
	
}
