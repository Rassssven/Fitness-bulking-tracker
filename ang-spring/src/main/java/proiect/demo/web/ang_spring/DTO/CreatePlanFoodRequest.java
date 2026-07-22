package proiect.demo.web.ang_spring.DTO;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CreatePlanFoodRequest {

	private String name;
    private Integer calories;
    private Integer protein;
    private Integer carbs;
    private Integer fat;
    private String description;
    private DayOfWeek dayOfWeek;
    
    private Integer quantity;
    private String mealType;
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCalories() {
		return calories;
	}
	
	public void setCalories(Integer calories) {
		this.calories = calories;
	}
	
	public Integer getProtein() {
		return protein;
	}
	
	public void setProtein(Integer protein) {
		this.protein = protein;
	}
	
	public Integer getCarbs() {
		return carbs;
	}
	
	public void setCarbs(Integer carbs) {
		this.carbs = carbs;
	}
	
	public Integer getFat() {
		return fat;
	}
	
	public void setFat(Integer fat) {
		this.fat = fat;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public String getMealType() {
		return mealType;
	}
	
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
}
