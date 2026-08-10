package proiect.demo.web.ang_spring.DTO.FoodDTOs;

import java.time.LocalDateTime;

public class SavedFoodResponseDTO {

    private Long savedId;
    private Long foodId;
    private String name;
    private int calories;
    private int protein;
    private int carbs;
    private int fat;
    private String description;
    private LocalDateTime savedAt;

    public SavedFoodResponseDTO() {
    }

    public SavedFoodResponseDTO(Long savedId, Long foodId, String name,
                             int calories, int protein, int carbs,
                             int fat, String description,
                             LocalDateTime savedAt) {
        this.savedId = savedId;
        this.foodId = foodId;
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.description = description;
        this.savedAt = savedAt;
    }

    public Long getSavedId() {
        return savedId;
    }

    public void setSavedId(Long savedId) {
        this.savedId = savedId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }
}
