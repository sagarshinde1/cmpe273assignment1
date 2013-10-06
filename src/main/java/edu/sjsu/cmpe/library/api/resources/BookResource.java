package edu.sjsu.cmpe.library.api.resources;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.Object;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.io.IOException;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.BookLink;
import edu.sjsu.cmpe.library.domain.Reviews;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


public class BookResource {

	public static Map<Long, ArrayList> BookReviewMapOne = new HashMap<Long, ArrayList>();
	public static ConcurrentHashMap<Long, Object> book_map =new ConcurrentHashMap<Long, Object>();
	public static Map<Long, Reviews> newBookReviewMap = new HashMap<Long, Reviews>();
	public static Map<Long, Map<Long,Author>> AuthorHashMap = new HashMap<Long, Map<Long,Author>>();
	public static ArrayList<Reviews> reviewsArray = new ArrayList<Reviews>();
	public static Map<Long, Map<Long,Reviews>> BookReviewMap = new HashMap<Long, Map<Long,Reviews>>();

	public BookResource() {

	}

	@GET
	@Path("/{isbn}")
	@Timed(name = "view-book")

	public Response getBookByIsbn(@PathParam("isbn") LongParam isbn) 
	{
		{
			Book book = (Book) book_map.get(isbn.get());
			checkNotNull(book, "newBook instance must not be null");
			BookDto bookResponse = new BookDto(book);
			bookResponse.addLink(new LinkDto("view-book", "/books/" + isbn,"GET"));
			bookResponse.addLink(new LinkDto("update-book","/books/" + isbn, "PUT"));
			bookResponse.addLink(new LinkDto("delete-book","/books/" + isbn, "DELETE"));
			bookResponse.addLink(new LinkDto("create-review","/books/" + isbn +"/reviews", "POST"));
			if(reviewsArray.size()>0)
				bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + isbn + "/reviews", "GET"));
			return Response.ok(bookResponse).build();
		}


	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Timed(name = "create-book")


	public Response createBook(BookLink bookRequest)
	{
		ArrayList<LinkDto> authorArray = new ArrayList();
		Book newBook = new Book(bookRequest.getBookTitle(), bookRequest.getBookAuthors(),bookRequest.getBookLanguage(),bookRequest.getNoOfPages(),bookRequest.getPublication_date(),bookRequest.getBookStatus(),bookRequest.getBookReviews()); // newbook created
		Long new_isbn = newBook.getNewIsbn();
		Map<Long,Author> authorMap =  new HashMap<Long, Author>();
		book_map.put(new_isbn,newBook);
		for(Author author: bookRequest.getBookAuthors())
		{
			Long author_id = author.createAuthorID();
			authorMap.put(author_id, author);
			authorArray.add(new LinkDto("view-author","/books/" + new_isbn + "/authors/" + author_id , "GET"));
		}
		newBook.setBookAuthors(authorArray);
		AuthorHashMap.put(new_isbn, authorMap);
		LinksDto linkResponse = new LinksDto();
		linkResponse.addLink(new LinkDto("view-book", "/books/" + new_isbn,"GET"));
		linkResponse.addLink(new LinkDto("update-book","/books/" + new_isbn, "PUT"));
		linkResponse.addLink(new LinkDto("delete-book","/books/" + new_isbn, "PUT"));
		linkResponse.addLink(new LinkDto("create-review","/books/" + new_isbn + "/reviews", "POST"));
		return Response.created(null).entity(linkResponse).build();
	}



	public boolean isAuthorNameEmpty(Author author) {
		if(author.getAuthorName().isEmpty())
			return true;

		return false;
	}

	@DELETE
	@Path("/{isbn}")
	@Timed(name = "delete-book")

	public Response deleteBook(@PathParam("isbn") LongParam isbn)  
	{

		book_map.remove(isbn.get());
		LinksDto linkResponse = new LinksDto();
		linkResponse.addLink(new LinkDto("create-book", "/books/","POST"));
		return Response.ok(linkResponse).build();


	}

	@PUT
	@Path("/{isbn}")
	@Timed(name = "update-book")

	public Response updateBookStatus(@PathParam("isbn") LongParam isbn, @QueryParam("status") String status) 
	{

		Book book = (Book) book_map.get(isbn.get());
		book.setNewStatus(status);
		LinksDto response = new LinksDto();
		response.addLink(new LinkDto("view-book", "/books/" + isbn,"GET"));
		response.addLink(new LinkDto("update-book","/books/" + isbn, "PUT"));
		response.addLink(new LinkDto("delete-book","/books/" + isbn, "DELETE"));
		response.addLink(new LinkDto("create-review","/books/" + isbn + "/reviews", "POST"));
		if(reviewsArray.size()>0)
			response.addLink(new LinkDto("view-all-reviews", "/books/" + isbn + "/reviews", "GET"));
		return Response.ok(response).build();


	}

	@POST
	@Path("/{isbn}/reviews")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "create-book-review")

	public Response createReview(@PathParam("isbn") LongParam isbn, Reviews newReview)
	{	

		Reviews newBookReview = new Reviews();
		Long reviewId = newBookReview.createRevID();
		newReview.setID(reviewId);
		newBookReviewMap.put(reviewId, newReview);
		reviewsArray.add(newReview);
		BookReviewMapOne.put(isbn.get(), reviewsArray);
		LinksDto reviewResponse = new LinksDto();
		reviewResponse.addLink(new LinkDto("view-review", "/books/" + isbn + "/reviews/" + reviewId,"GET"));
		return Response.created(null).entity(reviewResponse).build();

	}

	@GET
	@Path("/{isbn}/reviews/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "view-book-review")
	public Response getReview(@PathParam("isbn") LongParam isbn,@PathParam("id") LongParam reviewId)
	{

		Reviews bookReview = BookReviewMap.get(isbn.get()).get(reviewId.get());
		ReviewDto reviewResponse = new ReviewDto(bookReview);
		reviewResponse.addLink(new LinkDto("view-review", "/books/" + isbn + "/reviews/" + reviewId ,"GET"));
		return Response.ok(reviewResponse).build();


	}

	@GET
	@Path("/{isbn}/reviews")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name="view-all-reviews")

	public Response getAllBookReviews(@PathParam("isbn") LongParam isbn)
	{

		ArrayList allReviews;
		allReviews = BookReviewMapOne.get(isbn.get());
		ReviewsDto bookResponse = new ReviewsDto(allReviews);
		return Response.ok(bookResponse).build();

	}

	@GET
	@Path("/{isbn}/authors/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "view-book-author")
	public Response getAuthorByBook(@PathParam("isbn") LongParam isbn,@PathParam("id") LongParam authorId)
	{	
		Author bookAuthor;
		bookAuthor=AuthorHashMap.get(isbn.get()).get(authorId.get());
		AuthorDto authorResponse = new AuthorDto(bookAuthor);
		authorResponse.addLink(new LinkDto("view-author", "/books/" + isbn + "/authors/" + authorId ,"GET"));
		return Response.ok(authorResponse).build();

	}



	@GET
	@Path("/{isbn}/authors")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name="view-authors-all-isbn")

	public Response getAllAuthors(@PathParam("isbn") LongParam bookId)
	{

		Map<Long, Author> authorsMap;
		authorsMap = AuthorHashMap.get(bookId.get());
		AuthorsDto bookResponse = new AuthorsDto(authorsMap.values());
		return Response.ok(bookResponse).build();

	}



}

