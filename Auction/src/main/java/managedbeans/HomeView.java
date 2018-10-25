package managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.ProductEJB;
import entities.Product;

@Named(value = "homeView")
@SessionScoped
public class HomeView implements Serializable{
	private static final long serialVersionUID = 3254181123309041386L;
	
	@Inject
	private ProductEJB productEJB;
	
	public List<Product> getProducts() {
		return productEJB.findActiveProducts();
	}
}
