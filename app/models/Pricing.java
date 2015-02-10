package models;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.ValidationError;
import play.data.validation.Constraints.Required;

public class Pricing {

	@Required
	public double cost;
	
	@Required
	public double price;
	
	@Required
	public double promoPrice;
	
	public double savings=0.00;
	
	public boolean onSale = false;
	
	public List<ValidationError> validate(){
		List<ValidationError> errors = new ArrayList<ValidationError>();
		if(cost == 0.0){
			errors.add(new ValidationError("cost", "Cost is invalid"));
		}
		
		if(price == 0.0){
			errors.add(new ValidationError("price", "Price is invalid"));
		}
		
		if(promoPrice == 0.0){
			errors.add(new ValidationError("promoPrice", "Promo Price has to be at least same as product price"));
		}
		
		if(savings < (price - promoPrice)){
			errors.add(new ValidationError("promoPrice", "Savings field is invalid"));
		}
		return errors.isEmpty() ? null : errors;
	}
	
	public Pricing(){
	}
	
	public Pricing(double cost, double price, double promoPrice, double savings, boolean onSale){
		this.cost = cost;
		this.price = price;
		this.promoPrice = promoPrice;
		this.savings = savings;
		this.onSale = onSale;
	}
}
