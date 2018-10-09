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
 * @author Markus
 *
 */

@Stateless
public class UserEJB {
	
	
	@PersistenceContext(unitName = "Auction")
	private EntityManager em;
	
	public void addUser(User user) {
		em.persist(user);
	}
	
	public List<User> getUsers() {
		return em.createNamedQuery(User.FIND_ALL, User.class).getResultList();
	}
	
	public User getUser(int userId) {
		return em.find(User.class, userId);
	}
}
