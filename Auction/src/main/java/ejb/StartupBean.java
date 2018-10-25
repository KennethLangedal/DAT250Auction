package ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Bid;
import entities.Picture;
import entities.Product;
import entities.User;

@Startup
@Singleton
public class StartupBean {
	
	@EJB
	private UserEJB userEJB;
	
    @PostConstruct
    public void init() {
    	User user = new User();
    	user.setfName("Kenneth");
    	user.setlName("Langedal");
    	user.setPhone(90682290);
    	user.setEmail("Kenneth.Langedal@hotmail.com");
    	user.setPassword("123asdA");

    	Product p1 = new Product();
    	p1.setActive(false);
    	p1.setName("Fersk sjokolademelk");
    	p1.setEndTime(new Date(System.currentTimeMillis() + 300000));
    	p1.setUser(user);
    	
    	user.setProducts(new ArrayList<Product>() {{add(p1);}});
    	
    	User user1 = new User();
    	user1.setfName("Markus");
    	user1.setlName("Løland");
    	user1.setPhone(123456789);
    	user1.setEmail("Markus.Løland@hotmail.com");
    	user1.setPassword("123asdA");
    	
    	Bid bid = new Bid();
    	bid.setUser(user1);
    	bid.setAmount(200);
    	
    	Bid bid2 = new Bid();
    	bid2.setUser(user1);
    	bid2.setAmount(300);
    	
    	p1.setBidHistory(new ArrayList<Bid>() {{add(bid); add(bid2);}});
    	
    	Product p2 = new Product();
    	p2.setActive(true);
    	p2.setName("Coca Cola Lemon");
    	p2.setEndTime(new Date(System.currentTimeMillis() + 100000));
    	p2.setUser(user1);
    	user1.setProducts(new ArrayList<Product>() {{add(p2);}});
    	
    	Picture picture = new Picture();
    	picture.setPath("https://upload.wikimedia.org/wikipedia/en/thumb/5/59/Lemon_Coke_bottle.jpg/80px-Lemon_Coke_bottle.jpg");
    	picture.setTitle("Coca Cola Lomon");
    	
    	p2.addPicture(picture);
    	
    	userEJB.createUser(user);
    	userEJB.createUser(user1);
    }
}
