package com.app.test;

import org.junit.*;
import java.util.List;

import static org.junit.Assert.*;
import models.Product;
import models.dao.ProductDao;
import models.dao.impl.ProductDaoImpl;


public class ProductTest {
	
	static ProductDao dao = new ProductDaoImpl();
	
	@Test
	public void testSaveProduct(){
		Product p = new Product();
		p.id=5L;
		p.title = "Calibee";
		p.pricing.cost = 10L;
		p.pricing.price = 11L;
		p.pricing.promoPrice = 10.5;
		p.pricing.onSale = true;
		p.pricing.savings = 0.5;
		long savedId = dao.create(p);
		assertNotNull("Saving has issues, ID is null",savedId);
		assertEquals("The ID returned and the ID set are not the same",5L, savedId);
	}
	
	@Test
	public void testUpdateProduct(){
		Product p = dao.read(5L);
		assertNotNull("Product is null", p);
		
		p.title = "Pringles";
		long savedId = dao.update(p);
		assertNotNull("Saving has issues, ID is null",savedId);
		assertEquals("The ID returned and the ID set are not the same",5L, savedId);
		
		p = dao.read(5L);
		assertEquals("Calibee is not updated to Pringles", "Pringles", p.title);
	}
	
	@Test
	public void testDeleteProduct(){
		boolean isDeleted = dao.delete(5L);
		assertTrue("Calibee which is now Pringles is not deleted" ,isDeleted);
	}
	
	public void testGetAllProducts(){
		List<Product> products = dao.getAllProducts();
		assertNotNull("There is an issue with fetching all products", products);
		
		assertFalse("Product list is empty", products.isEmpty());
	}

}
