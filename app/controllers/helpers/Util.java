package controllers.helpers;

import java.util.ArrayList;


import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;


import models.Product;

public class Util {
	
	public static String getAllIDs(List<Product> products){
		List<String> ids = new ArrayList<String>();
		for(Product product : products){
			ids.add(String.valueOf(product.id));
		}
		return new Gson().toJson(ids);
	}

}
