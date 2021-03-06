package entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Kenneth
 *
 */
@Entity
@XmlRootElement
@Table(name = "profile")
@NamedQuery(name = "User.findAll", query = "SELECT t FROM User t")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	// Create elements ids automatically, incremented 1 by 1
	@TableGenerator(name = "userTableGenerator", allocationSize = 1, initialValue = 1)
	
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "userTableGenerator")
	private int id;
	
	private String password;

	private String fName;

	private String lName;

	@Id
	private String email;

	private int phone;

	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private ArrayList<Product> products;

	@OneToMany(mappedBy = "buyer", cascade=CascadeType.ALL)
	private ArrayList<Product> purchaseHistory;

	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private ArrayList<Bid> bidHistory;

	public static final String FIND_ALL = "User.findAll";

	public User() {

	}
	
	public void addBid(Bid bid) {
		if (this.bidHistory == null)
			this.bidHistory = new ArrayList<Bid>();
		this.bidHistory.add(bid);
	}
	
	public void addProduct(Product product) {
		if (this.products == null)
			this.products = new ArrayList<Product>();
		this.products.add(product);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	@XmlTransient
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	@XmlTransient
	public ArrayList<Product> getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(ArrayList<Product> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}

	@XmlTransient
	public ArrayList<Bid> getBidHistory() {
		return bidHistory;
	}

	public void setBidHistory(ArrayList<Bid> bidHistory) {
		this.bidHistory = bidHistory;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", phone=" + phone
				+ "]";
	}
}
