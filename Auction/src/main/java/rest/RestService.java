package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ejb.ProductEJB;
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
	
	@EJB
	private ProductEJB productEJB;
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Product> getAuctions() {
		return productEJB.findProducts();
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{id}")
	public Product getAuction(@PathParam("id") String id){
		int idInt = Integer.parseInt(id);
		Product product = em.find(Product.class, idInt);
		if (product == null)
			throw new NotFoundException();
		return product;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{id}/bids")
	public List<Bid> getBids(@PathParam("id") String id) {
		int idInt = Integer.parseInt(id);
		Product product = em.find(Product.class, idInt);
		if (product == null)
			throw new NotFoundException();
		return product.getBidHistory();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{id}/bids/{bid}")
	public Bid getBid(@PathParam("id") String id, @PathParam("bid") String bId) {
		int bIdInt = Integer.parseInt(bId);
		Bid bid = em.find(Bid.class, bIdInt);
		if (bid == null)
			throw new NotFoundException();
		return bid;
	}
	
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{id}/bids")
	public Bid postBid(@PathParam("id") String id, Bid bid){
		int productId = Integer.parseInt(id);
		productEJB.placeBid(productId, 1, bid).ordinal();
		return bid;
	}
}


