package proiect.demo.web.ang_spring.Entities.Food;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import proiect.demo.web.ang_spring.Entities.DailyTracker;

@Entity
@Table(name = "daily_tracker_foods")
public class DailyTrackerFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer mealNumber;

    private Double quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "daily_tracker_id", nullable = false)
    @JsonIgnore
    private DailyTracker dailyTracker;

    @ManyToOne(optional = false)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    public DailyTrackerFood() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMealNumber() {
        return mealNumber;
    }

    public void setMealNumber(Integer mealNumber) {
        this.mealNumber = mealNumber;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public DailyTracker getDailyTracker() {
        return dailyTracker;
    }

    public void setDailyTracker(DailyTracker dailyTracker) {
        this.dailyTracker = dailyTracker;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}