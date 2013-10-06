package edu.sjsu.cmpe.library.domain;
import java.util.List;
import java.util.ArrayList;
import edu.sjsu.cmpe.library.dto.LinkDto;

public class AuthorRef {	
	private List<LinkDto> authorLinks = new ArrayList<LinkDto>();
	
	public void addAuthorLink(LinkDto link) {
		authorLinks.add(link);
    }
    public void setAuthorLinks(List<LinkDto> links) {
	this.authorLinks = links;
    }
    public List<LinkDto> getAuthorLinks() {
    	return authorLinks;
        }

}
	

