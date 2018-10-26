package managedbeans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.UserEJB;
import entities.User;

@Named(value = "profileView")
@SessionScoped
public class ProfileView implements Serializable{
	private static final long serialVersionUID = 3434181123309041386L;

	private User user;
	
	public String onLoad() {
		user = LoginView.sessionUser();
		if (user == null)
			return "signin";
		return "profile";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
