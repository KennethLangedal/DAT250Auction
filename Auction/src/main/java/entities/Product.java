package entities;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kenneth
 *
 */
@Entity
@XmlRootElement
@Table(name="product")
@NamedQuery(name="Product.findAll", query="SELECT t FROM Product t")
public class Product {
	private static final long serialVersionUID = 1L;

	//Create elements ids automatically, incremented 1 by 1
	@TableGenerator(
			  name = "productTableGenerator",
			  allocationSize = 1,
			  initialValue = 1)
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="productTableGenerator")
	private int id;
	
	private String name;
	
	private Date endTime;
	
	private Boolean active;
	
	private User user;
	
	private Rating rating;
	
	private ArrayList<Feature> features;
	
	private ArrayList<Picture> pictures;
	
	private ArrayList<Bid> bidHistory;
	
	public static final String FIND_ALL = "Product.findAll";
	
	public Product() {
		
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

	public ArrayList<Bid> getBidHistory() {
		return bidHistory;
	}

	public void setBidHistory(ArrayList<Bid> bidHistory) {
		this.bidHistory = bidHistory;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", endTime=" + endTime + ", active=" + active + "]";
	}
}
