package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kenneth
 *
 */
@Entity
@XmlRootElement
@Table(name="product")
@NamedQuery(name="Product.findAll", query="SELECT t FROM Product t")
public class Product {
	private static final long serialVersionUID = 1L;

	//Create elements ids automatically, incremented 1 by 1
	@TableGenerator(
			  name = "productTableGenerator",
			  allocationSize = 1,
			  initialValue = 1)
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="productTableGenerator")
	private int id;
	
	private String name;
	
	private 
}
