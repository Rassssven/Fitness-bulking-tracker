package proiect.demo.web.ang_spring.Entities.Shop;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	private String shortDescription;
	private String description;
	
	@Column(nullable = false)
	private Double price;
	
	private Double rating = 0.0;
	private Integer reviews = 0;
	
	@Column(nullable = false)
	private Boolean inStock = true;
	
	private String category;
	private String brand;
	
	private Boolean listed = true;
	
	private Integer discountPercentage = 0;
	
    @OneToMany(mappedBy = "product",
	           cascade = CascadeType.ALL,
	           orphanRemoval = true)
	private List<ProductImage> images;
	
	@OneToMany(mappedBy = "product",
	           cascade = CascadeType.ALL,
	           orphanRemoval = true)
	private List<ProductSpecification> specs;
	 
	public Product() { }

	public Product(String name, String shortDescription, String description, Double price, Double rating,
			 Integer reviews, Boolean inStock, String category, String brand, Integer discountPercentage,
			 Boolean listed, List<ProductImage> images, List<ProductSpecification> specs) {
		this.name = name;
		this.shortDescription = shortDescription;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.reviews = reviews;
		this.inStock = inStock;
		this.category = category;
		this.brand = brand;
		this.discountPercentage = discountPercentage;
		this.images = images;
		this.specs = specs;
		this.listed = listed;
	 }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean isListed() {
		return listed;
	}

	public void setListed(Boolean listed) {
		this.listed = listed;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getReviews() {
		return reviews;
	}

	public void setReviews(Integer reviews) {
		this.reviews = reviews;
	}

	public Boolean getInStock() {
		return inStock;
	}

	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Integer discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}

	public List<ProductSpecification> getSpecs() {
		return specs;
	}

	public void setSpecs(List<ProductSpecification> specs) {
		this.specs = specs;
	}
	 
	 

}
