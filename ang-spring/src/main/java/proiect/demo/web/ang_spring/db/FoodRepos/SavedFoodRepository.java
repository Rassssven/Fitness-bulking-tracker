package proiect.demo.web.ang_spring.db.FoodRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proiect.demo.web.ang_spring.Entities.Food.SavedFood;

@Repository
public interface SavedFoodRepository extends JpaRepository<SavedFood, Long> {

}
