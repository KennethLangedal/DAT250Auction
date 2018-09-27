package rest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	public String getUser() {
		User test = em.find(User.class, 2);
		return test.toString();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUserId(@PathParam("id") int id) {
		User test = em.find(User.class, id);
		return test.toString();
	}
}
