package managedbeans;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ejb.UserEJB;
import entities.User;

@Named(value = "loginView")
@SessionScoped
public class LoginView implements Serializable {
	
	private static final long serialVersionUID = 3254181235309041386L;
	
	@Inject
	private UserEJB userEJB;
	
	private String email;
	private String password;
	private User user;
	
	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			request.login(email, password);
		} catch (ServletException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			return "signin";
		}
		Principal principal = request.getUserPrincipal();
		this.user = userEJB.getUser(principal.getName());
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("User", user);
		if (request.isUserInRole("user")) {
			return "/index?faces-redirect=true";
		} else {
			return "signin";
		}
	}
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			this.user = null;
			request.logout();
			// clear the session
			((HttpSession) context.getExternalContext().getSession(false)).invalidate();
		} catch (ServletException e) {
			
		}
		return "/signin?faces-redirect=true";
	}
	public User getAuthenticatedUser() {
		return user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
