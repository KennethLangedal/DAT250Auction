package entities;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kenneth
 *
 */
@Entity
@XmlRootElement
@Table(name = "product")
@NamedQuery(name = "Product.findAll", query = "SELECT t FROM Product t")
@NamedQuery(name = "Product.findActive", query = "SELECT t FROM Product t WHERE t.endTime > CURRENT_TIMESTAMP and t.active = true")
public class Product {
	private static final long serialVersionUID = 1L;

	// Create elements ids automatically, incremented 1 by 1
	@TableGenerator(name = "productTableGenerator", allocationSize = 1, initialValue = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "productTableGenerator")
	private int id;

	private String name;

	private Date endTime;

	private Boolean active;

	@ManyToOne(cascade=CascadeType.ALL)
	private User user;

	@ManyToOne(cascade=CascadeType.ALL)
	private User buyer;

	private Rating rating;

	@OneToMany(cascade=CascadeType.ALL)
	private ArrayList<Feature> features;

	@OneToMany(cascade=CascadeType.ALL)
	private ArrayList<Picture> pictures;

	@OneToMany(cascade=CascadeType.ALL)
	private ArrayList<Bid> bidHistory;

	public static final String FIND_ALL = "Product.findAll";
	public static final String FIND_ACTIVE = "Product.findActive";

	public Product() {

	}
	
	public Bid getLastBid() {
		if (this.bidHistory == null || bidHistory.isEmpty())
			return null;
		return this.bidHistory.get(this.bidHistory.size()-1);
	}

	public void addBid(Bid bid) {
		if (this.bidHistory == null)
			this.bidHistory = new ArrayList<Bid>();
		this.bidHistory.add(bid);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(ArrayList<Feature> features) {
		this.features = features;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public ArrayList<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(ArrayList<Picture> pictures) {
		this.pictures = pictures;
	}

	@XmlElementWrapper(name = "bidHistory")
	@XmlElement(name = "bid")
	public ArrayList<Bid> getBidHistory() {
		return bidHistory;
	}

	public void setBidHistory(ArrayList<Bid> bidHistory) {
		this.bidHistory = bidHistory;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

}
