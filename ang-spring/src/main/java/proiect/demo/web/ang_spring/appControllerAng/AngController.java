package proiect.demo.web.ang_spring.appControllerAng;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.services.workoutService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AngController {

	private static final Logger logger = LogManager.getLogger(AngController.class);
	private final workoutService wkService;
	
	public AngController(workoutService wkService) {
        this.wkService = wkService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Salut din Spring Boot!";
    }
    
    @GetMapping("/testLog4j")
    public String test() {
    	logger.info("Logger apelat");
    	logger.debug("Debug info");
    	logger.error("Test error log");
    	
    	return "Ok!";
    }
    
    @GetMapping("/add-workout")
    public String addWorkout() {

        wkService.createWorkout(
                "Chest workout",
                "Bench press and pushups"
        );

        return "Workout added";
    }
    
 
}
