package proiect.demo.web.ang_spring.db.FoodRepos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Food.SavedFood;

@Repository
public interface SavedFoodRepository extends JpaRepository<SavedFood, Long> {

	List<SavedFood> findByUser(User user);
	
}
