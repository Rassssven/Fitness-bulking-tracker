package proiect.demo.web.ang_spring.Services.ShopServices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import proiect.demo.web.ang_spring.DTO.ShopDTOs.CreateProductRequest;
import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Enums.Role;
import proiect.demo.web.ang_spring.Entities.Shop.Product;
import proiect.demo.web.ang_spring.Entities.Shop.ProductImage;
import proiect.demo.web.ang_spring.Services.FileStorageService;
import proiect.demo.web.ang_spring.db.UserRepository;
import proiect.demo.web.ang_spring.db.ShopRepos.ProductRepository;

@Service
public class ShopService {

	private final ProductRepository productRepo;
	private final UserRepository userRepo;
	private final FileStorageService fileStorageService;
	
	public ShopService(ProductRepository productRepo, UserRepository userRepo, FileStorageService fileStorageService) {
		super();
		this.productRepo = productRepo;
		this.userRepo = userRepo;
		this.fileStorageService = fileStorageService;
	}

	public Product createProduct(CreateProductRequest dto, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Product can't be created!"));
		
		if(user.getRole() != Role.ADMIN) {
			throw new RuntimeException("User must be admin to post products!");
		}
		
		Product prod = new Product();
		
		prod.setName(dto.getName());
		prod.setShortDescription(dto.getShortDescription());
		prod.setDescription(dto.getDescription());
		prod.setPrice(dto.getPrice());
		prod.setInStock(dto.getInStock());
		prod.setCategory(dto.getCategory());
		prod.setBrand(dto.getBrand());
		prod.setDiscountPercentage(dto.getDiscountPercentage());
		
//		List<ProductImage> images = dto.getImages()
//				.stream()
//				.map(url -> {
//					ProductImage img = new ProductImage();
//					img.setFileName(url);
//					img.setProduct(prod);
//					return img;
//				})
//				.toList();
		
		prod.setImages(new ArrayList<>());
		
//		List<ProductSpecification> specs = dto.getSpecifications()
//			    .stream()
//			    .map(s -> {
//			        ProductSpecification spec = new ProductSpecification();
//			        spec.setName(s.getName());
//			        spec.setValue(s.getValue());
//			        spec.setProduct(prod);
//			        return spec;
//			    })
//			    .toList();
//
//		prod.setSpecs(specs);
		
		return productRepo.save(prod);
	}
	
	public List<Product> getProducts() {
		return productRepo.findAll();
	}
	
	public void deleteProduct(Long prodId, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Invalid user!"));
		
		Product product = productRepo.findById(prodId)
				.orElseThrow(() -> new RuntimeException("Product doesn't exist!"));
		
		if(user.getRole() != Role.ADMIN) {
			throw new RuntimeException("User must be admin to handle products!");
		}
		
		productRepo.delete(product);
	}
	
	public Product updateProduct(CreateProductRequest dto, Long prodId, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("No user found!"));
	
		if(user.getRole() != Role.ADMIN) {
			throw new RuntimeException("User must be admin to handle products!");
		}
		
		Product product = productRepo.findById(prodId)
				.orElseThrow(() -> new RuntimeException("Product can't be updated!"));
		
		product.setName(dto.getName());
	    product.setShortDescription(dto.getShortDescription());
	    product.setDescription(dto.getDescription());
	    product.setPrice(dto.getPrice());
	    product.setInStock(dto.getInStock());
	    product.setCategory(dto.getCategory());
	    product.setBrand(dto.getBrand());
	    product.setDiscountPercentage(dto.getDiscountPercentage());
		
	    return productRepo.save(product);
	}
	
	public Product uploadImages(Long prodId, List<MultipartFile> files, Authentication auth) {

		String email = auth.getName();

		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Invalid user!"));

		if (user.getRole() != Role.ADMIN) {
			throw new RuntimeException("User must be admin to handle products!");
		}

		Product product = productRepo.findById(prodId)
				.orElseThrow(() -> new RuntimeException("Product doesn't exist!"));

		List<ProductImage> images = files.stream()
				.map(file -> {
					String savedFileName = fileStorageService.saveFile(file);

					ProductImage img = new ProductImage();
					img.setFileName(savedFileName);
					img.setOriginalFileName(file.getOriginalFilename());
					img.setProduct(product);
					return img;
				})
				.toList();

		product.getImages().addAll(images);

		return productRepo.save(product);
	}
	
}
