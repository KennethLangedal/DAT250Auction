package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;

/**
 * @author Markus
 *
 */

	@Entity
	@XmlRootElement
	@Table(name="bid")
	@NamedQuery(name="Bid.findAll", query="SELECT t FROM Bid t")
	public class Bid {
		private static final long serialVersionUID = 1L;
		
		public static final String FIND_ALL = "Bid.findAll";

		//Create elements ids automatically, incremented 1 by 1
		@TableGenerator(
				  name = "bidTableGenerator",
				  allocationSize = 1,
				  initialValue = 1)
		@Id
		@Expose
		@GeneratedValue(strategy=GenerationType.TABLE,generator="bidTableGenerator")
		private int id;
		
		@Expose
		private int amount;
		
		@ManyToOne
		@Expose
		private User user;
		
		
		public Bid() {
			
		}
		
		

		public int getId() {
			return id;
		}



		public void setId(int id) {
			this.id = id;
		}



		public int getAmount() {
			return amount;
		}



		public void setAmount(int amount) {
			this.amount = amount;
		}


		public User getUser() {
			return user;
		}



		public void setUser(User user) {
			this.user = user;
		}



		@Override
		public String toString() {
			return "Bid [id=" + id + ", amount=" + amount + ", user=" + user + "]";
		}
		
		
		
		
		
		
		
		
 }

