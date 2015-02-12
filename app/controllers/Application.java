package controllers;

import java.io.IOException;
import java.util.List;

import models.Product;
import models.dao.ProductDao;
import models.dao.impl.ProductDaoImpl;

import org.codehaus.jackson.map.ObjectMapper;

import play.data.Form;
import play.Logger;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import controllers.helpers.Util;

/**
 * 
 * Main product controller that handles all product CRUD operations
 *
 */
public class Application extends Controller {

	static Form<Product> productForm = new Form<Product>(Product.class);
	
	static ProductDao dao = new ProductDaoImpl();

	/**
	 * Index action
	 * @return
	 */
	public static Result index() {
		return redirect(routes.Application.products());
	}

	/**
	 * Products action to list all products
	 * @return
	 */
	public static Result products() {
		return ok(views.html.index.render(dao.getAllProducts(), new Form<Product>(Product.class)));
	}
	
	/**
	 * Add product page, shows empty form(no Ajax)
	 * @return
	 */
	public static Result addProduct() {
		return ok(views.html.add.render(new Form<Product>(Product.class)));
	}
	
	/**
	 * Search and edit - to show search box
	 * @return
	 */
	public static Result searchProducts() {
		return ok(views.html.search.render(productForm));
	}

	/**
	 * Get all product ids to be shown in search box auto-suggestion
	 * @return
	 */
	public static Result productIds() {
		return ok(Util.getAllIDs(dao.getAllProducts()));
	}
	
	/**
	 * Get a specific product whose ID is in the search box
	 * @param id
	 * @return
	 */
	public static Result getProduct(Long id){
		productForm = productForm.fill(dao.read(id));
		return ok(views.html.edit.render(productForm));
	}

	/**
	 * Add a new product
	 * @return
	 */
	public static Result newProduct() {
		Form<Product> filledForm = Form.form(Product.class).bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.add
					.render(filledForm));
		} else {
			dao.create(filledForm.get());
			return redirect(routes.Application.products());
		}
	}
	
	/**
	 * Update or save an existing product
	 * @return
	 */
	public static Result saveProduct(){
		Form<Product> filledForm = Form.form(Product.class).bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.edit.render(filledForm));
		} else {
			dao.update(filledForm.get());
			return redirect(routes.Application.products());
		}
	}

	/**
	 * Delete a product
	 * @param id
	 * @return
	 */
	public static Result deleteProduct(Long id) {
		dao.delete(id);
		return redirect(routes.Application.products());
	}

}
