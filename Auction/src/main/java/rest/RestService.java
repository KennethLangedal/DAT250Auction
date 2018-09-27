package rest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.User;

/**
 * @author Kenneth
 *
 */
@Path("/test")
@Stateless
public class RestService {

	@PersistenceContext(unitName = "Auction")
	private EntityManager em;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getTweets() {
		User test = em.find(User.class, 1);
		return test.toString();
	}
}
