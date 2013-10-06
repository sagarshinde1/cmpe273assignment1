package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.Collection;

import edu.sjsu.cmpe.library.domain.Author;

public class AuthorsDto extends LinksDto{

 public Collection<Author> author=null;
 Author authorbook; 

	public void setAuthor(ArrayList<Author> author) {
		this.author = author;
	}
	public AuthorsDto(Collection<Author> collection) {
		this.author = collection;
	}

	
	
}
