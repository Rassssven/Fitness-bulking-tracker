package proiect.demo.web.ang_spring.Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import proiect.demo.web.ang_spring.Entities.Food.DailyTrackerFood;

@Entity
@Table(
		name = "daily_trackers",
		uniqueConstraints = {
				@UniqueConstraint(
						name = "uk_daily_tracker_user_date",
						columnNames = {"user_id", "tracker_date"}
				)
		}
)
public class DailyTracker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tracker_date", nullable = false)
	private LocalDate date;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private User user;

	@OneToMany(
			mappedBy = "dailyTracker",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@OrderBy("mealNumber ASC")
	private List<DailyTrackerFood> foods = new ArrayList<>();

	public DailyTracker() {
	}

	public DailyTracker(LocalDate date, User user) {
		this.date = date;
		this.user = user;
	}

	public void addFood(DailyTrackerFood trackerFood) {
		foods.add(trackerFood);
		trackerFood.setDailyTracker(this);
	}

	public void removeFood(DailyTrackerFood trackerFood) {
		foods.remove(trackerFood);
		trackerFood.setDailyTracker(null);
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<DailyTrackerFood> getFoods() {
		return foods;
	}

	public void setFoods(List<DailyTrackerFood> foods) {
		this.foods = foods;
	}
}