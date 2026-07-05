package proiect.demo.web.ang_spring.Services.ShopServices;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.DTO.CreateProductRequest;
import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Shop.Product;
import proiect.demo.web.ang_spring.db.UserRepository;
import proiect.demo.web.ang_spring.db.ShopRepos.ProductRepository;

@Service
public class ShopService {

	private final ProductRepository productRepo;
	private final UserRepository userRepo;
	
	public ShopService(ProductRepository productRepo, UserRepository userRepo) {
		super();
		this.productRepo = productRepo;
		this.userRepo = userRepo;
	}
	
	public Product createProduct(CreateProductRequest dto, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Food can't be created!"));
		
		Product prod = new Product();
		
		
	}
	
}
