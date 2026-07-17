package proiect.demo.web.ang_spring.Controllers.ShopControllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.DTO.CreateProductRequest;
import proiect.demo.web.ang_spring.Entities.Shop.Product;
import proiect.demo.web.ang_spring.Services.ShopServices.ShopService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	private final ShopService productServ;

	public ProductController(ShopService productServ) {
		super();
		this.productServ = productServ;
	}
	
	@PostMapping
	public Product createProduct(@RequestBody CreateProductRequest prod, Authentication auth) {
		return productServ.createProduct(prod, auth);
	}
	
	@GetMapping
	public List<Product> getProducts() {
		return productServ.getProducts();
	}
	
	@DeleteMapping("/{prodId}")
	public void deleteProduct(@PathVariable Long prodId, Authentication auth) {
		productServ.deleteProduct(prodId, auth);
	}
	
	@PutMapping("/{prodId}")
	public Product updateProduct(@RequestBody CreateProductRequest prod, @PathVariable Long prodId, Authentication auth) {
		return productServ.updateProduct(prod, prodId, auth);
	}
	
	
}
