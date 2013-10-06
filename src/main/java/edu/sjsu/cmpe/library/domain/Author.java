package edu.sjsu.cmpe.library.domain;


public class Author{

	private static Long AuthorID=  (long) 0;
	private String authorName=null;;

	public Long createAuthorID()
	{
		AuthorID=AuthorID+1;
		return AuthorID;
	}

	public void setAuthorID(Long iD) {
		AuthorID = iD;
	}


	public Long getAuthorID() {
		return AuthorID;
	}

	public void setAuthorName(String name) {
		authorName = name;
	}

	public String getAuthorName() {
		return authorName;
	}


}
