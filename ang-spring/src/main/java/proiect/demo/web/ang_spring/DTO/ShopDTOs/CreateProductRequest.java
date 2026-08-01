package proiect.demo.web.ang_spring.DTO.ShopDTOs;

import java.util.List;

import proiect.demo.web.ang_spring.DTO.ProductSpecificationRequest;

public class CreateProductRequest {

	private String name;
	private String shortDescription;
	private String description;
	private Double price;
	private Boolean inStock;
	private String category;
	private String brand;
	private Integer discountPercentage;
	private Boolean listed;
	private List<ProductSpecificationRequest> specifications;
	
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

	public List<ProductSpecificationRequest> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(List<ProductSpecificationRequest> specifications) {
		this.specifications = specifications;
	}

	public Boolean isListed() {
		return listed;
	}

	public void setListed(Boolean listed) {
		this.listed = listed;
	}
	
	
}
