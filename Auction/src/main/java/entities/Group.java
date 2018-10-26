package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kenneth
 *
 */
@Entity
@XmlRootElement
@Table(name = "user_group")
@NamedQuery(name = "Group.findAll", query = "SELECT g FROM Group g")
public class Group implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Transient
	public static final String USER_GROUP = "user";
	@Transient
	public static final String ADMIN_GROUP = "admin";

	@TableGenerator(name = "groupTableGenerator", allocationSize = 1, initialValue = 1)
	
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "groupTableGenerator")
	private int id;
	
	@Id
	private String email;
	
	private String groupname;
	
	public Group() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", email=" + email + ", groupname=" + groupname + "]";
	}
}
