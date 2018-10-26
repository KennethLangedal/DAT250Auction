package ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import entities.*;

/**
 * @author Kenneth
 *
 */

@Stateless
public class ProductEJB {
	
	public final long MinBidTime = 600000;
	
	public enum BidStatus{OK,LOW,NOT_ACTIVE,MISSING_PRODUCT,PRODUCT_CLOSED,MISSING_USER}
	
	@PersistenceContext(unitName = "Auction")
	private EntityManager em;
	
	/**
	 * Place a new bid.
	 * 
	 * Returnes status OK if there is no errors.
	 * 
	 * @param productId
	 * @param userId
	 * @param bid
	 * @return BidStatus
	 */
	public BidStatus placeBid(int productId, String email, Bid bid) {
		// Get data
		Product product = em.find(Product.class, productId);
		User user = em.find(User.class, email);
		
		// Validate bid
		if (product == null)
			return BidStatus.MISSING_PRODUCT;
		if (user == null || email.equals(product.getUser().getEmail()))
			return BidStatus.MISSING_USER;
		if (bid.getAmount() < 1 || (product.getLastBid() != null && product.getLastBid().getAmount() >= bid.getAmount()))
			return BidStatus.LOW;
		if (!product.getActive())
			return BidStatus.NOT_ACTIVE;
		if (product.getEndTime().getTime() < System.currentTimeMillis())
			return BidStatus.PRODUCT_CLOSED;
		if (product.getEndTime().getTime() < System.currentTimeMillis() + MinBidTime)
			product.setEndTime(new Date(System.currentTimeMillis() + MinBidTime));
		
		// Persist bid
		product.addBid(bid);
		user.addBid(bid);
		bid.setUser(user);
		bid.setProduct(product);
		product.setBuyer(user);
		
		return BidStatus.OK;
	}
	
	public List<Bid> findBids(int productId) {
		Product product = em.find(Product.class, productId);
		if (product == null)
			return null;
		return product.getBidHistory();
	}
	
	public Bid findBid(int bidId) {
		return em.find(Bid.class, bidId);
	}
	
	public List<Product> findProducts() {
		return em.createNamedQuery(Product.FIND_ALL, Product.class).getResultList();
	}
	
	public List<Product> findActiveProducts() {
		return em.createNamedQuery(Product.FIND_ACTIVE, Product.class).getResultList();
	}
	
	public Product findProduct(int id) {
		Product product = em.find(Product.class, id);
		return product;
	}
	
	public void addProduct(Product product, int userId) {
		User user = em.find(User.class, userId);
		if (user == null)
			throw new IllegalArgumentException("Missing user");
		
		product.setUser(user);
		user.addProduct(product);
	}
	
	public void mergeProduct(Product product) {
		Product mproduct = em.find(Product.class, product.getId());
		mproduct.setName(product.getName());
		mproduct.setDescription(product.getDescription());
		mproduct.setEndTime(product.getEndTime());
		Picture p = em.find(Picture.class, mproduct.getPicture().getId());
		p.setPath(product.getPicture().getPath());
		p.setTitle(product.getPicture().getTitle());
	}
	
	public void publish(int productId, Date endDate) {
		Product product = em.find(Product.class, productId);
		if (product == null)
			throw new IllegalArgumentException("Missing product");
		
		if (endDate.getTime() < System.currentTimeMillis() + MinBidTime)
			throw new IllegalArgumentException("Invalid duration");
		
		product.setEndTime(endDate);
		product.setActive(true);
	}
	
	public void giveFeedback(Rating rating, int productId, int userId) {
		Product product = em.find(Product.class, productId);
		User user = em.find(User.class, userId);
		if (product == null)
			throw new IllegalArgumentException("Missing product");
		
		if (user == null)
			throw new IllegalArgumentException("Missing user");
		
		rating.setAuthor(user);
		product.setRating(rating);
	}
}
