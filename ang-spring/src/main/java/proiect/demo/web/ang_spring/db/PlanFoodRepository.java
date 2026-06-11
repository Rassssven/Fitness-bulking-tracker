package proiect.demo.web.ang_spring.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import proiect.demo.web.ang_spring.Entities.Food.PlanFood;

public interface PlanFoodRepository extends JpaRepository<PlanFood, Long>{

	List<PlanFood> findByPlanId(Long planId);
	
}
