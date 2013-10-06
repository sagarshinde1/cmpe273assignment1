package edu.sjsu.cmpe.library.dto;

import edu.sjsu.cmpe.library.domain.Reviews;

public class ReviewDto extends LinksDto{

	 private Reviews review=null;
	 
	 public ReviewDto(Reviews review) {
			this.review=review;
		}
		
	 public ReviewDto()
	 {
		 
	 }
		
		public void setBookReview(Reviews review) {
			this.review = review;
		}
		public Reviews getBookReview() {
			return review;
		}

}
