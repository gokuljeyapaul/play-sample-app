package models.dao;

import java.util.List;

import models.Product;

/**
 * Product DAO interface 
 *
 */
public interface ProductDao{
	
	/**
	 * Get a list of products
	 * @return List<Product>
	 */
	public List<Product> getAllProducts();
	
	/**
	 * Create a product
	 * @param Product p
	 * @return long id
	 */
	public long create(Product p);
	
	/**
	 * Read or get a product
	 * @param id
	 * @return Product
	 */
	public Product read(long id);
	
	/**
	 * Update a product
	 * @param Product p
	 * @return long id
	 */
	public long update(Product p);
	
	/**
	 * Delete a product
	 * @param long id
	 * @return boolean
	 */
	public boolean delete(long id);

}
