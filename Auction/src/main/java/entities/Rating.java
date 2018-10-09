package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;

/**
 * @author Kenneth
 *
 */
@Entity
@XmlRootElement
@Table(name = "rating")
@NamedQuery(name = "Rating.findAll", query = "SELECT t FROM Rating t")
public class Rating {
	private static final long serialVersionUID = 1L;

	// Create elements ids automatically, incremented 1 by 1
	@TableGenerator(name = "ratingTableGenerator", allocationSize = 1, initialValue = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ratingTableGenerator")
	private int id;

	private int score;

	private String comment;

	private User author;

	public static final String FIND_ALL = "Rating.findAll";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		if (score < 0)
			this.score = 0;
		else if (score > 5)
			this.score = 5;
		else
			this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
}
