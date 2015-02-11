package models.dao.impl;

import java.util.ArrayList;
import java.util.List;

import net.vz.mongodb.jackson.JacksonDBCollection;
import play.modules.mongodb.jackson.MongoDB;
import play.Logger;
import models.Product;
import models.dao.ProductDao;

public class ProductDaoImpl implements ProductDao{
	
	private static JacksonDBCollection<Product, Long> productCollection = MongoDB
			.getCollection("product", Product.class, Long.class);

	@Override
	public List<Product> getAllProducts() {
		return productCollection.find().toArray();
	}
	
	@Override
	public long create(Product p) {
		p.id = Long.valueOf(productCollection.count()+1);
		p = productCollection.save(p).getSavedObject();
		return p.id;
		
	}

	@Override
	public Product read(long id) {
		Product p = productCollection.findOneById(id);
		return p;
	}

	@Override
	public long update(Product p) {
		Product savedProduct = productCollection.save(p).getSavedObject();
		return (savedProduct == null) ? 0L : savedProduct.id;
	}

	@Override
	public boolean delete(long id) {
		Product p = productCollection.findOneById(id);
		if(p != null){
			productCollection.remove(p);
		}else {
			return false;
		}
		return true;
	}

}
