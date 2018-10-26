package jms;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.google.gson.JsonObject;

import ejb.ProductEJB;
import entities.Product;


/**
 * @Author Markus
 * 
 * 
 * 
 */

@MessageDriven(mappedName = "jms/dat250/Topic", activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class DweetListener implements MessageListener {
	
	@EJB
	ProductEJB productEJB;

	@Override
	public void onMessage(Message message) {
		
		

		try {
			Product product = message.getBody(Product.class);
			if(product.getEndTime().getTime() > System.currentTimeMillis())
				return;
			Product pro = productEJB.findProduct(product.getId());
			JsonObject json = new JsonObject();
			json.addProperty("name", pro.getName());
			json.addProperty("buyer", pro.getBuyer().toString());
			json.addProperty("amount", pro.getLastBid().getAmount());
			json.addProperty("user", pro.getUser().toString());
//			
			
			Logger logger = Logger.getLogger(getClass().getName());
			logger.info("DTWEET name: " + pro.getName()); 
			logger.info("DTWEET buyer: " + pro.getBuyer().toString()); 
			logger.info("DTWEET amount: " +  pro.getLastBid().getAmount()); 
			logger.info("DTWEET user: " + pro.getUser().toString());
			logger.info("DTWEET: Sending bid to dweet...");
			logger.info("DTWEET JSON: " + json);
			try {
				DweetConnection dc = new DweetConnection();
				dc.publish(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}