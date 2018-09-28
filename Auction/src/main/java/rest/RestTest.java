package rest;

import java.util.List;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entities.Bid;
import entities.Product;

/**
 * @author Kenneth
 *
 */
@Path("/test")
@Stateless
public class RestTest {
	
	@PersistenceContext(unitName = "Auction")
	private EntityManager em;
	
	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); 

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getProducts() {
		TypedQuery<Product> query = em.createNamedQuery(Product.FIND_ALL, Product.class);
		return gson.toJson(query.getResultList().get(0));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("bid")
	public List<Bid> getBids() {
		TypedQuery<Bid> query = em.createNamedQuery(Bid.FIND_ALL, Bid.class);
		return query.getResultList();
	}
	
	@GET
	@Path("{id}")
	public Response getTweet(@PathParam("id") String id) {
		int idInt = Integer.parseInt(id);
		return Response.ok(idInt).build();
	}
}


