package proiect.demo.web.ang_spring.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DailyTrackerResponse {

    private Long id;
    private LocalDate date;

    private int totalCalories;
    private int totalProtein;
    private int totalCarbs;
    private int totalFat;

    private List<DailyTrackerFoodResponse> meals = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public int getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(int totalProtein) {
        this.totalProtein = totalProtein;
    }

    public int getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(int totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public int getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(int totalFat) {
        this.totalFat = totalFat;
    }

    public List<DailyTrackerFoodResponse> getMeals() {
        return meals;
    }

    public void setMeals(List<DailyTrackerFoodResponse> meals) {
        this.meals = meals;
    }
}