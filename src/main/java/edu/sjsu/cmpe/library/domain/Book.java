package edu.sjsu.cmpe.library.domain;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.api.resources.BookResource;

import java.util.ArrayList;
import java.util.HashMap;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties(ignoreUnknown = true)

public class Book
{
	public static long isbn=0;
	public long getNewIsbn() {
		return isbn;
	}

	public String bookTitle;
	public String bookStatus;
	public String bookLanguage;
	public ArrayList<Reviews> bookReviews = new ArrayList();
	public ArrayList<LinkDto> bookAuthors = new ArrayList();
	public static HashMap<Long, ArrayList> BookAuthorsMap = new HashMap<Long, ArrayList>();
	public static HashMap<Long, Object> AuthorsMap = new HashMap<Long, Object>();
	private String publicationDate;
	private int noOfPages;


	public Book()
	{

	}

	public Book( String bookTitle, ArrayList<Author> authors2, String bookLanguage, int noOfPages, String publicationDate, String bookStatus, ArrayList<Reviews> bookReviews) {

		//randomGenerator = new Random();
		isbn++; //(long) randomGenerator.nextInt(100);
		this.bookTitle = bookTitle;
		this.bookLanguage = bookLanguage;
		this.noOfPages = noOfPages;
		this.publicationDate = publicationDate;
		this.bookStatus=bookStatus;
		this.bookReviews=bookReviews;
	}


	public ArrayList<Reviews> getBookReviews() {

				return bookReviews;
	}

	public void setBookReviews(ArrayList<Reviews> bookReviews) {

		this.bookReviews=bookReviews;
	}

	public ArrayList<LinkDto> getBookAuthors() {
		return bookAuthors;
	}

	public void setBookAuthors(ArrayList<LinkDto> bookAuthors) {
		this.bookAuthors = bookAuthors;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookLanguage() {
		return bookLanguage;
	}

	public void setBookLanguage(String bookLanguage) {
		this.bookLanguage = bookLanguage;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setNewStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
	public String setPublicationDate() {
		return publicationDate;
	}


	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}


	public int getNoOfPages() {
		return noOfPages;
	}

	public void setNoOfpages(int noOfPages) {
		this.noOfPages = noOfPages;
	}





}
