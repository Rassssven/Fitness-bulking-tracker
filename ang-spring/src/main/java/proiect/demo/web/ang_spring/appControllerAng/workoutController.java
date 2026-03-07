package proiect.demo.web.ang_spring.appControllerAng;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.services.workoutService;

@RestController
@RequestMapping("/workout")
public class workoutController {

	private final workoutService wkService;
	
	public workoutController(workoutService wkService) {
        this.wkService = wkService;
    }

    @GetMapping("/add")
    public String addWorkout() {

        wkService.createWorkout(
                "Chest workout",
                "Bench press and pushups"
        );

        return "Workout added";
    }
	
}
