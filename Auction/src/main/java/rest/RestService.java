package rest;

import java.util.List;

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
	
	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); 
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public List<Product> getAuctions() {
		TypedQuery<Product> query = em.createNamedQuery(Product.FIND_ALL, Product.class);
		return query.getResultList();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getAuctionsJSON() {
		TypedQuery<Product> query = em.createNamedQuery(Product.FIND_ALL, Product.class);
		return gson.toJson(query.getResultList());
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("{id}")
	public Product getAuction(@PathParam("id") String id){
		int idInt = Integer.parseInt(id);
		Product product = em.find(Product.class, idInt);
		if (product == null)
			throw new NotFoundException();
		return product;
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String getAuctionJson(@PathParam("id") String id){
		int idInt = Integer.parseInt(id);
		Product product = em.find(Product.class, idInt);
		if (product == null)
			throw new NotFoundException();
		return gson.toJson(product);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("{id}/bids")
	public List<Bid> getBids(@PathParam("id") String id) {
		int idInt = Integer.parseInt(id);
		Product product = em.find(Product.class, idInt);
		if (product == null)
			throw new NotFoundException();
		return product.getBidHistory();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}/bids")
	public String getBidsJSON(@PathParam("id") String id) {
		int idInt = Integer.parseInt(id);
		Product product = em.find(Product.class, idInt);
		if (product == null)
			throw new NotFoundException();
		return gson.toJson(product.getBidHistory());
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("{id}/bids/{bid}")
	public Bid getBid(@PathParam("id") String id, @PathParam("bid") String bId) {
		int bIdInt = Integer.parseInt(bId);
		Bid bid = em.find(Bid.class, bIdInt);
		if (bid == null)
			throw new NotFoundException();
		return bid;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}/bids/{bid}")
	public String getBidJSON(@PathParam("id") String id, @PathParam("bid") String bId) {
		int bIdInt = Integer.parseInt(bId);
		Bid bid = em.find(Bid.class, bIdInt);
		if (bid == null)
			throw new NotFoundException();
		return gson.toJson(bid);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("{id}/makebid")
	public Bid postBid(@PathParam("id") String id, @QueryParam("amount") String amount){
		int bidId = Integer.parseInt(id);
		int amountInt = Integer.parseInt(amount);

		Product product = em.find(Product.class, bidId);
		if (product == null)
			throw new NotFoundException();
		
		
		Bid bid = new Bid();
		bid.setAmount(amountInt);
		product.addBid(bid);
		em.persist(bid);
		
		return bid;
			
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}/makebid")
	public String postBidJson(@PathParam("id") String id, @QueryParam("amount") String amount){
		int bidId = Integer.parseInt(id);
		int amountInt = Integer.parseInt(amount);

		Product product = em.find(Product.class, bidId);
		if (product == null)
			throw new NotFoundException();
		
		
		Bid bid = new Bid();
		bid.setAmount(amountInt);
		product.addBid(bid);
		em.persist(bid);
		
		return gson.toJson(bid);
			
	}
	
	
}


