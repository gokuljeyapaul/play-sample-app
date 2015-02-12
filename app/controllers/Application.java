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

public class Application extends Controller {

	static Form<Product> productForm = new Form<Product>(Product.class);
	
	static ProductDao dao = new ProductDaoImpl();

	public static Result index() {
		return redirect(routes.Application.products());
	}

	public static Result products() {
		return ok(views.html.index.render(dao.getAllProducts(), new Form<Product>(Product.class)));
	}
	
	public static Result addProduct() {
		return ok(views.html.add.render(new Form<Product>(Product.class)));
	}
	
	public static Result searchProducts() {
		return ok(views.html.search.render(productForm));
	}

	public static Result productIds() {
		return ok(Util.getAllIDs(dao.getAllProducts()));
	}
	
	public static Result getProduct(Long id){
		productForm = productForm.fill(dao.read(id));
		return ok(views.html.edit.render(productForm));
	}

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
	
	public static Result saveProduct(){
		Form<Product> filledForm = Form.form(Product.class).bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.edit.render(filledForm));
		} else {
			dao.update(filledForm.get());
			return redirect(routes.Application.products());
		}
	}

	public static Result deleteProduct(Long id) {
		dao.delete(id);
		return redirect(routes.Application.products());
	}

}
