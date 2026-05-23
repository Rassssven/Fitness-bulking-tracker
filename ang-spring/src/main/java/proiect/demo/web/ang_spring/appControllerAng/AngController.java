package proiect.demo.web.ang_spring.appControllerAng;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AngController {

	private static final Logger logger = LogManager.getLogger(AngController.class);

    
    @GetMapping("/testLog4j")
    public String test() {
    	logger.info("Logger apelat");
    	logger.debug("Debug info");
    	logger.error("Test error log");
    	
    	return "Ok!";
    }
    
}
