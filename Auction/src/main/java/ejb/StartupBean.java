package ejb;

import java.util.ArrayList;
import java.sql.Date;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Bid;
import entities.Product;
import entities.User;

@Startup
@Singleton
public class StartupBean {
	
	@PersistenceContext(unitName = "Auction")
	private EntityManager em;
	
    @PostConstruct
    public void init() {
    	TypedQuery<User> deleteUsers = em.createQuery("DELETE FROM User CASCADE", User.class);
    	deleteUsers.executeUpdate();
    	
    	TypedQuery<Product> deleteProducts = em.createQuery("DELETE FROM Product CASCADE", Product.class);
    	deleteProducts.executeUpdate();
    	
    	User user = new User();
    	user.setfName("Kenneth nr1");
    	user.setlName("Langedal");
    	user.setPhone(90682290);
    	user.setEmail("Kenneth.Langedal@hotmail.com");

    	Product p1 = new Product();
    	p1.setActive(false);
    	p1.setName("Fersk sjokolademelk");
    	p1.setEndTime(new Date(10000));
    	p1.setUser(user);
    	
    	user.setProducts(new ArrayList<Product>() {{add(p1);}});
    	
    	User user1 = new User();
    	user1.setfName("Markus");
    	user1.setlName("Løland");
    	user1.setPhone(123456789);
    	user1.setEmail("Markus.Løland@hotmail.com");
    	
    	Bid bid = new Bid();
    	bid.setUser(user1);
    	bid.setAmount(200);
    	
    	Bid bid2 = new Bid();
    	bid2.setUser(user1);
    	bid2.setAmount(200);
    	
    	p1.setBidHistory(new ArrayList<Bid>() {{add(bid); add(bid2);}});
    	
    	Product p2 = new Product();
    	p2.setActive(false);
    	p2.setName("Coca Cola Lemon");
    	p2.setEndTime(new Date(111111));
    	p2.setUser(user);
    	user.setProducts(new ArrayList<Product>() {{add(p2);}});
    	
    	Product p3 = new Product();
    	p3.setUser(user);
    	user.setProducts(new ArrayList<Product>() {{add(p3);}});
    	
    	em.persist(user);
    	em.persist(user1);
    	em.persist(p3);
    	em.persist(p1);
    	em.persist(p2);
    	em.persist(bid);
    	em.persist(bid2);
    }
}
