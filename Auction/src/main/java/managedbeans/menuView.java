package managedbeans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "menuView")
@SessionScoped
public class menuView implements Serializable{
	private static final long serialVersionUID = 3254181193309041386L;
	
	public boolean getIsLoggedIn() {
		return LoginView.sessionUser() != null;
	}
	
	public String getUserName() {
		return LoginView.sessionUser().getfName();
	}
}
