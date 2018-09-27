package entities;

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
@Table(name="rating")
@NamedQuery(name="Rating.findAll", query="SELECT t FROM Rating t")
public class Rating {
	private static final long serialVersionUID = 1L;
	
	//Create elements ids automatically, incremented 1 by 1
	@TableGenerator(
			  name = "ratingTableGenerator",
			  allocationSize = 1,
			  initialValue = 1)
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="ratingTableGenerator")
	private int id;
	
	private int score;
	
	private String comment;
	
	private User author;
	
	public static final String FIND_ALL = "Rating.findAll";
}