package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.ValidationError;

public class Product {

	/**
	 * 
	 */
	private static final long serialVersionUID = 94587092799205246L;

	@Id
	public Long id;
	
	@Required
	@MaxLength(50)
	@MinLength(8)
	public String title;
	
	@Required
	public Pricing pricing;
	
	public List<ValidationError> validate(){
		List<ValidationError> errors = new ArrayList<ValidationError>();
		if(pricing.cost == 0.0){
			errors.add(new ValidationError("cost", "Cost is invalid"));
		}
		
		if(pricing.price == 0.0){
			errors.add(new ValidationError("price", "Price is invalid"));
		}
		
		if(pricing.promoPrice == 0.0){
			errors.add(new ValidationError("promoPrice", "Promo Price has to be at least same as product price"));
		}
		
		if(pricing.price <= pricing.cost){
			errors.add(new ValidationError("price", "Price must be more than the cost"));
		}
		
		if(pricing.savings < (pricing.price - pricing.promoPrice)){
			errors.add(new ValidationError("promoPrice", "Savings field is invalid"));
		}
		return errors.isEmpty() ? null : errors;
	}
	
	public Product(){
	}
	
	public Product(String title){
		this.title = title;
	}
}
