package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	
	private final String tempUserId = "Kenneth.Langedal@hotmail.com";
	
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
		Product product = productEJB.findProduct(idInt);
		if (product == null)
			throw new NotFoundException();
		return product;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{id}/bids")
	public List<Bid> getBids(@PathParam("id") String id) {
		int idInt = Integer.parseInt(id);
		List<Bid> products = productEJB.findBids(idInt);
		if (products == null)
			throw new NotFoundException();
		return products;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{id}/bids/{bid}")
	public Bid getBid(@PathParam("id") String id, @PathParam("bid") String bId) {
		int bIdInt = Integer.parseInt(bId);
	
		Bid bid = productEJB.findBid(bIdInt);
		if (bid == null)
			throw new NotFoundException();
		return bid;
	}
	
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{id}/bids")
	public Response postBid(@PathParam("id") String id, Bid bid){
		int productId = Integer.parseInt(id);
		switch(productEJB.placeBid(productId, tempUserId, bid)) {
		case LOW:
			return Response.ok().build();
		case MISSING_PRODUCT:
	        return Response.status(404).entity("Missing product").build();
		case MISSING_USER:
			return Response.status(404).entity("Missing user").build();
		case NOT_ACTIVE:
			return Response.status(400).entity("The product is not active").build();
		case PRODUCT_CLOSED:
			return Response.status(400).entity("Product is closed").build();
		};
		return Response.ok(bid).build();
	}
}


