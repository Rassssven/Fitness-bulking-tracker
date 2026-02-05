package proiect.demo.web.ang_spring.appControllerAng;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AngController {

    @GetMapping("/hello")
    public String hello() {
        return "Salut din Spring Boot!";
    }
}
