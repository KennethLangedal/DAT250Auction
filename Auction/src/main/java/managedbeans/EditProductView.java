package managedbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.ProductEJB;
import ejb.UserEJB;
import entities.Picture;
import entities.Product;
import entities.User;

@Named(value = "editProductView")
@SessionScoped
public class EditProductView implements Serializable{
	private static final long serialVersionUID = 3254181173309041386L;

	@Inject
	private ProductEJB productEJB;
	
	@Inject
	private UserEJB userEJB;
	
	private Product product;
	private int productId;
	
	public String onLoad() {
		product = productEJB.findProduct(productId);
		User user = LoginView.sessionUser();
		if (user == null)
			return "signin";
		if (product == null) {
			product = new Product();
			Picture p = new Picture();
			product.setPicture(p);
		} else if (product.getUser().getId() != user.getId())
			return "home";
		return "editProduct";
	}
	
	public String save() {
		User user = LoginView.sessionUser();
		if (product.getId() == 0)
			productEJB.addProduct(product, user.getId());
		else
			productEJB.mergeProduct(product);
		LoginView.refreshSessionUser(userEJB.getUser(user.getEmail()));
		return "profile";
	}
	
	public String publish() {
		User user = LoginView.sessionUser();
		if (productId == 0)
			return "editProfile";
		productEJB.publish(productId, product.getEndTime());
		LoginView.refreshSessionUser(userEJB.getUser(user.getEmail()));
		return "profile";
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
}
