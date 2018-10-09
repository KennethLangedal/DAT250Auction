package ejb;

import java.util.Calendar;
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
	
	public enum BidStatus{OK,LOW,NOT_ACTIVE,MISSING_PRODUCT,PRODUCT_CLOSED,MISSING_USER}
	
	@PersistenceContext(unitName = "Auction")
	private EntityManager em;
	
	public BidStatus placeBid(int productId, int userId, Bid bid) {
		Product product = em.find(Product.class, productId);
		User user = em.find(User.class, userId);
		if (user == null)
			return BidStatus.MISSING_USER;
		if (product == null)
			return BidStatus.MISSING_PRODUCT;
		if (bid.getAmount() < 1 || (product.getLastBid() != null && product.getLastBid().getAmount() >= bid.getAmount()))
			return BidStatus.LOW;
		if (!product.getActive())
			return BidStatus.NOT_ACTIVE;
		if (product.getEndTime().getTime() < System.currentTimeMillis())
			return BidStatus.PRODUCT_CLOSED;
		product.addBid(bid);
		user.addBid(bid);
		bid.setUser(user);
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
		TypedQuery<Product> q = em.createQuery("SELECT t FROM Product t WHERE t.endTime > CURRENT_TIMESTAMP and t.active = true", Product.class);
		return q.getResultList();
	}
	
	public Product findProduct(int id) {
		Product product = em.find(Product.class, id);
		return product;
	}
}
