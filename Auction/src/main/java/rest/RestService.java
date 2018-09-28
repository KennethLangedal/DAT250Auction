package rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.Bid;
import entities.Product;

/**
 * @author Kenneth
 *
 */
@Path("/auctions")
@Stateless
public class RestService {
	
	@PersistenceContext(unitName = "Auction")
	private EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Product> getProducts() {
		TypedQuery<Product> query = em.createNamedQuery(Product.FIND_ALL, Product.class);
		return query.getResultList();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProduct(@PathParam("id") String id) {
		//int idp = Integer.parseInt(id);
		//Product prod = em.find(Product.class, idp);
		//return prod;
		return id;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("bid")
	public List<Bid> getBids() {
		TypedQuery<Bid> query = em.createNamedQuery(Bid.FIND_ALL, Bid.class);
		return query.getResultList();
	}
}


