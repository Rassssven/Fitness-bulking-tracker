package proiect.demo.web.ang_spring.Controllers.ShopControllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.Services.ShopServices.ShopService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ShopService productServ;

	public ProductController(ShopService productServ) {
		super();
		this.productServ = productServ;
	}
	
	
	
}
