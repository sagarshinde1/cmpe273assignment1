package edu.sjsu.cmpe.library.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.jersey.params.IntParam;

public class Reviews {

	
    public static Long ID= (long) 0;
    public String bookRating;
    public String bookComment;
  
	public Long createRevID()
    {
		ID= ID+1;
      	 
    	 return ID;
    }   
	public void setID(Long ID) {
		this.ID = ID;
	}	
	public void setComment(String bookComment) {
		 this.bookComment = bookComment;
	}
	public void setRating(String bookRating) {
		 this.bookRating = bookRating;
	}
}
