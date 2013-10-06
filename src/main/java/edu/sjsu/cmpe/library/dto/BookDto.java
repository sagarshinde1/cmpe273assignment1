package edu.sjsu.cmpe.library.dto;

import edu.sjsu.cmpe.library.domain.Book;


public class BookDto extends LinksDto{
    private Book newBook;
 
	public BookDto(Book newBook) {	
	this.newBook = newBook;
    }
    
    public BookDto() {	
    	super();
    }
   
   
		
   public void setBook(Book newbook) {
    	
    	this.newBook = newbook;
    	
    	
    }
   public Book getBook() {
		
		return newBook;
	
	}
}
