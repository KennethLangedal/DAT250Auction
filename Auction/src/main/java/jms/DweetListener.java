package jms;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.google.gson.JsonObject;

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

	@Override
	public void onMessage(Message message) {
		
		

		try {
			Product product = message.getBody(Product.class);
			if(product.getEndTime().getTime() > System.currentTimeMillis())
				return;
			JsonObject json = new JsonObject();
			json.addProperty("name", product.getName());
			json.addProperty("buyer", product.getBuyer().toString());
			json.addProperty("amount", product.getLastBid().getAmount());
			json.addProperty("user", product.getUser().toString());
//			
			
			Logger logger = Logger.getLogger(getClass().getName());
			logger.info("DTWEET name: " + product.getName()); 
			logger.info("DTWEET buyer: " + product.getBuyer().toString()); 
			logger.info("DTWEET amount: " +  product.getLastBid().getAmount()); 
			logger.info("DTWEET user: " + product.getUser().toString());
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