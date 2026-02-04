package proiect.demo.web.ang_spring.appControllerAng;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class AngController {

    @GetMapping
    public String test() {
        return "Salut din Spring Boot";
    }
}