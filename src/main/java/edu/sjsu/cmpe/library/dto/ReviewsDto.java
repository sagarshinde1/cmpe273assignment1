package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;

import edu.sjsu.cmpe.library.domain.Reviews;

public class ReviewsDto extends LinksDto{

	 public ArrayList  bookReviews=null;
	 
	 
	 public ReviewsDto(ArrayList bookReviews) {
			this.bookReviews = bookReviews;
		}

	 
		public void bookReviews(ArrayList bookReviews) {
			this.bookReviews = bookReviews;
		}
	 
}
