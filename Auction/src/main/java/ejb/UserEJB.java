package ejb;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.xml.bind.DatatypeConverter;

import entities.*;

/**
 * @author Markus
 *
 */

@Stateless
public class UserEJB {
	
	
	@PersistenceContext(unitName = "Auction")
	private EntityManager em;
	
	public User createUser(User user) {
		try {
			user.setPassword(encodeSHA256(user.getPassword()));
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		Group group = new Group();
		group.setEmail(user.getEmail());
		group.setGroupname(Group.USER_GROUP);
		em.persist(user);
		em.persist(group);
		return user;
	}
	
	public List<User> getUsers() {
		return em.createNamedQuery(User.FIND_ALL, User.class).getResultList();
	}
	
	public User getUser(String email) {
		return em.find(User.class, email);
	}
	
	public static String encodeSHA256(String password) 
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes("UTF-8"));
        byte[] digest = md.digest();
        return DatatypeConverter.printBase64Binary(digest).toString();
    }
}
