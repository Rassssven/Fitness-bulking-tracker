package proiect.demo.web.ang_spring.Entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="Goals")
public class Goal {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer targetCalories;
	private Integer targetWeight;
	
	private String type;
	
	private LocalDate startDate;
	private LocalDate endDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy = "goal")
	@JsonIgnore
	private List<Plan> plans;
	
	public Goal() {}

	public Goal(Integer targetCalories, String type, LocalDate startDate, LocalDate endDate, Integer targetWeight) {
		super();
		this.targetCalories = targetCalories;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.targetWeight = targetWeight;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTargetCalories() {
		return targetCalories;
	}

	public void setTargetCalories(Integer targetCalories) {
		this.targetCalories = targetCalories;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Integer getTargetWeight() {
		return targetWeight;
	}

	public void setTargetWeight(Integer targetWeight) {
		this.targetWeight = targetWeight;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
