package proiect.demo.web.ang_spring.services;

import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.db.FoodRepository;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public void updateCalories(int id, int calories) {
        foodRepository.updateFoodCalories(id, calories);
    }
}