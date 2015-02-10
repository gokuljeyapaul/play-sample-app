package controllers;

import java.io.IOException;

import models.Product;

import org.codehaus.jackson.map.ObjectMapper;

import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import controllers.helpers.Util;

public class Application extends Controller {

	static Form<Product> productForm = new Form<Product>(Product.class);

	public static Result index() {
		return redirect(routes.Application.products());
	}

	public static Result products() {
		return ok(views.html.index.render(Product.all(), new Form<Product>(Product.class)));
	}
	
	public static Result addProduct() {
		return ok(views.html.add.render(new Form<Product>(Product.class)));
	}
	
	public static Result searchProducts() {
		return ok(views.html.search.render(productForm));
	}

	public static Result productIds() {
		return ok(Util.getAllIDs(Product.all()));
	}
	
	public static Result getProduct(Long id){
		productForm = productForm.fill(Product.get(id));
		return ok(views.html.edit.render(productForm));
	}

	public static Result newProduct() {
		Form<Product> filledForm = Form.form(Product.class).bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.add
					.render(filledForm));
		} else {
			Product.create(filledForm.get());
			return redirect(routes.Application.products());
		}
	}
	
	public static Result saveProduct(){
		Form<Product> filledForm = productForm.bindFromRequest();
		if (productForm.hasErrors()) {
			return badRequest(views.html.index
					.render(Product.all(), filledForm));
		} else {
			Product.save(filledForm.get());
			return redirect(routes.Application.products());
		}
	}

	public static Result deleteProduct(Long id) {
		Product.delete(id);
		return redirect(routes.Application.products());
	}

}
