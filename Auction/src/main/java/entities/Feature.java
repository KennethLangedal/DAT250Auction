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
 * @author Markus
 *
 */

	@Entity
	@XmlRootElement
	@Table(name="feature")
	@NamedQuery(name="Feature.findAll", query="SELECT t FROM Feature t")
	public class Feature {
		private static final long serialVersionUID = 1L;

		//Create elements ids automatically, incremented 1 by 1
		@TableGenerator(
				  name = "featureTableGenerator",
				  allocationSize = 1,
				  initialValue = 1)
		@Id
		@GeneratedValue(strategy=GenerationType.TABLE,generator="userTableGenerator")
		private int id;
		
		private String description;
		
		public Feature() {
			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public String toString() {
			return "Feature [id=" + id + ", description=" + description + "]";
		}

	
		
		
 }
