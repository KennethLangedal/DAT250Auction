package rest;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Kenneth
 *
 */
@Path("/test")
@Stateless
public class RestService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getTweets() {
		return "test";
	}
}
