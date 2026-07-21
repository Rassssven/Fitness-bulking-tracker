package proiect.demo.web.ang_spring.Entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DailyTracker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private LocalDate date;
	private int weight;
	
	private int consumedCalories;
	private int consumedProtein;
	private int consumedCarbs;
	private int consumedFat;
	private int burnedCalories;
	
	@ManyToOne
	@JoinColumn(name = "plan_id")
	@JsonIgnore
	private Plan plan;
	
	public DailyTracker() { }

	public DailyTracker(LocalDate date, int weight, int consumedCalories, int consumedProtein, int consumedCarbs,
			int consumedFat, int burnedCalories, Plan plan) {
		super();
		this.date = date;
		this.weight = weight;
		this.consumedCalories = consumedCalories;
		this.consumedProtein = consumedProtein;
		this.consumedCarbs = consumedCarbs;
		this.consumedFat = consumedFat;
		this.burnedCalories = burnedCalories;
		this.plan = plan;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getConsumedCalories() {
		return consumedCalories;
	}

	public void setConsumedCalories(int consumedCalories) {
		this.consumedCalories = consumedCalories;
	}

	public int getConsumedProtein() {
		return consumedProtein;
	}

	public void setConsumedProtein(int consumedProtein) {
		this.consumedProtein = consumedProtein;
	}

	public int getConsumedCarbs() {
		return consumedCarbs;
	}

	public void setConsumedCarbs(int consumedCarbs) {
		this.consumedCarbs = consumedCarbs;
	}

	public int getConsumedFat() {
		return consumedFat;
	}

	public void setConsumedFat(int consumedFat) {
		this.consumedFat = consumedFat;
	}

	public int getBurnedCalories() {
		return burnedCalories;
	}

	public void setBurnedCalories(int burnedCalories) {
		this.burnedCalories = burnedCalories;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
	
	
}
