package proiect.demo.web.ang_spring.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import proiect.demo.web.ang_spring.Entities.Food.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{
	
}
