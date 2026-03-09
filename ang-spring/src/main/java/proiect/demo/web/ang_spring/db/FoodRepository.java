package proiect.demo.web.ang_spring.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import proiect.demo.web.ang_spring.Entities.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{

	@Procedure(procedureName = "update_food_calories")
	void updateFoodCalories(
	        @Param("p_food_id") int foodId,
	        @Param("p_calories") int calories
	);
	
}
