package edu.sjsu.cmpe.library.dto;

import edu.sjsu.cmpe.library.domain.Author;


public class AuthorDto extends LinksDto {

	public Author author;
	
	public AuthorDto()
	{
	}

	
	public void setBookAuthor(Author author) {
		this.author = author;
	}

	public AuthorDto(Author author) {
		this.author=author;
	}

	public Author getBookAuthor() {
		return author;
	}


}
