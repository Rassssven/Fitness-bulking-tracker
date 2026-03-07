package proiect.demo.web.ang_spring.services;

import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.db.WorkoutRepository;

@Service
public class workoutService {

    private WorkoutRepository workoutRepo;

    public workoutService(WorkoutRepository workoutRepo) {
        this.workoutRepo = workoutRepo;
    }

    public void createWorkout(String name, String description) {
        workoutRepo.addWorkout(name, description);
    }
}
