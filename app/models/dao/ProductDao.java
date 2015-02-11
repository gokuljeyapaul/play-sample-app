package models.dao;

import java.util.List;

import models.Product;

public interface ProductDao{
	
	public List<Product> getAllProducts();
	
	public long create(Product p);
	
	public Product read(long id);
	
	public long update(Product p);
	
	public boolean delete(long id);

}
