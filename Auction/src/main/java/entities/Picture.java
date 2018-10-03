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
 * @author Markus
 *
 */

@Entity
@XmlRootElement
@Table(name = "picture")
@NamedQuery(name = "Picture.findAll", query = "SELECT t FROM Picture t")
public class Picture {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Picture.findAll";

	// Create elements ids automatically, incremented 1 by 1
	@TableGenerator(name = "pictureTableGenerator", allocationSize = 1, initialValue = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "pictureTableGenerator")
	private int id;

	private String path;

	private String title;

	public Picture() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Picture [id=" + id + ", path=" + path + ", title=" + title + "]";
	}

}
