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

import entities.Bid;
import entities.Product;
import plurals.Bids;
import plurals.Products;

/**
 * @author Markus
 *
 */
@Path("/test2")
@Stateless
public class RestTest {

	@PersistenceContext(unitName = "Auction")
	private EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_XML)

	public Response getProducts() {
		TypedQuery<Product> query = em.createNamedQuery(Product.FIND_ALL, Product.class);
		Products products = new Products(query.getResultList());
		return Response.ok(products).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("bid")
	public Response getBids() {
		TypedQuery<Bid> query = em.createNamedQuery(Bid.FIND_ALL, Bid.class);
		Bids bids = new Bids(query.getResultList());
		return Response.ok(bids).build();
	}
}
