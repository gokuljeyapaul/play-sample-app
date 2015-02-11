package models;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.ValidationError;
import play.data.validation.Constraints.Required;
import play.data.validation.Constraints.Min;

public class Pricing {

	@Min(1)
	public double cost;
	
	@Min(1)
	public double price;
	
	@Min(1)
	public double promoPrice;
	
	public double savings=0.00;
	
	public boolean onSale = false;
	
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
