package proiect.demo.web.ang_spring.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Food.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{
	
	List<Food> findByUser(User user);
	
	List<Food> findByProteinGreaterThan(double number);
	
	List<Food> findByCaloriesLessThan(double number);
	
	boolean existsByName(String name);
	
	List<Food> findByNameContaining(String name);
	
	List<Food> findByCaloriesAndProteinGreaterThan(double number, double number2);
	
}
