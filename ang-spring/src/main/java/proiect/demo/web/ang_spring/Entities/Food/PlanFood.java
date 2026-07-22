package proiect.demo.web.ang_spring.Entities.Food;

import java.time.DayOfWeek;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import proiect.demo.web.ang_spring.Entities.Plan;

@Entity
@Table(name = "PlanFoods")
public class PlanFood {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantity;
	private String mealType;
	
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;
	
	@ManyToOne
	@JoinColumn(name = "food_id")
	private Food food;
	
	@ManyToOne
	@JoinColumn(name = "plan_id")
	private Plan plan;
	
	public PlanFood() { }
	
	public PlanFood(Integer quantity, String mealType, DayOfWeek dayOfWeek, Food food, Plan plan) {
		super();
		this.quantity = quantity;
		this.mealType = mealType;
		this.dayOfWeek = dayOfWeek;
		this.food = food;
		this.plan = plan;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
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
