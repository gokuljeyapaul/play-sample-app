package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import net.vz.mongodb.jackson.JacksonDBCollection;
import play.data.validation.Constraints.Required;
import play.data.validation.ValidationError;
import play.modules.mongodb.jackson.MongoDB;

public class Product {

	/**
	 * 
	 */
	private static final long serialVersionUID = 94587092799205246L;

	@Id
	public Long id;
	
	@Required
	public String title;
	
	@Required
	public Pricing pricing;
	
	public List<ValidationError> validate(){
		List<ValidationError> errors = new ArrayList<ValidationError>();
		if(id == null){
			errors.add(new ValidationError("id", "Id is null or empty"));
		}
		
		if(title == null){
			errors.add(new ValidationError("title", "Title of the product is null or empty"));
		}
		return errors.isEmpty() ? null : errors;
	}
	
	public Product(){
	}
	
	public Product(String title){
		this.title = title;
	}

	private static JacksonDBCollection<Product, Long> products = MongoDB
			.getCollection("product", Product.class, Long.class);
	
	public static List<Product> all() {
		return Product.products.find().toArray();
	}
	
	public static void create(Product product) {
		product.id = Long.valueOf(Product.products.count()+1);
		Product.products.save(product);
	}
	
	public static void save(Product product){
		Product.products.save(product);
	}
	
	public static Product get(Long id){
		return Product.products.findOneById(id);
	}

	public static void delete(Long id) {
		Product p = Product.products.findOneById(id);
		if(p != null){
			Product.products.remove(p);
		}
	}

}
