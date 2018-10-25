package soap;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.NotFoundException;

import entities.*;

import ejb.ProductEJB;

@WebService
public class SoapService {
	
	public final String tempUserId = "Kenneth.Langedal@hotmail.com";
	
	@EJB
	private ProductEJB productEJB;
	
	@WebMethod(operationName="getAuction")
	public Product getAuction(@WebParam(name = "auctionId") int id) {
		Product product = productEJB.findProduct(id);
		if (product == null)
			throw new NotFoundException();
		return product;
	}
	
    @WebMethod(operationName="getActiveAuctions")
    public List<Product> getActiveAuctions() {
        return productEJB.findActiveProducts();
    }
    
    @WebMethod(operationName="makeBid")
    public ProductEJB.BidStatus makeBid(@WebParam(name = "amount") int amount, @WebParam(name = "auctionId") int id) {
    	Bid bid = new Bid();
    	bid.setAmount(amount);
    	return productEJB.placeBid(id, tempUserId, bid);
    }
}
