package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.data.validation.ValidationError;

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
}
