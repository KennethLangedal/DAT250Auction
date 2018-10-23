package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Group {
	
	public static final String USER_GROUP = "user";
	public static final String ADMIN_GROUP = "admin";

	@Id
	private String email;
	
	private String groupname;
	
	public Group() {
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
}
