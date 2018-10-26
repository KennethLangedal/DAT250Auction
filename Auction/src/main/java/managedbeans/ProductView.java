package managedbeans;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.ProductEJB;
import entities.Bid;
import entities.Product;
import entities.User;

@Named(value = "productView")
@SessionScoped
public class ProductView implements Serializable{
	private static final long serialVersionUID = 3254181123309041386L;

	@Inject
	private ProductEJB productEJB;
	
	private Product product;
	private int productId;
	private int amount;
	
	public String onLoad() {
		product = productEJB.findProduct(productId);
		if (product == null)
			return "home";
		return "product";
	}
	
	public String makeBid() {
		Bid bid = new Bid();
		bid.setAmount(amount);
		User user = LoginView.sessionUser();
		if (user == null)
			return "signin";
		if (productEJB.placeBid(productId, user.getEmail(), bid) == ProductEJB.BidStatus.OK)
			LoginView.refreshSessionUser(user);
		return onLoad();
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
