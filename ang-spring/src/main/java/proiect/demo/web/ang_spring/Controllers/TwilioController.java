package proiect.demo.web.ang_spring.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.Services.TwilioService;

@RestController
@RequestMapping("/api/sms")
public class TwilioController {

    private final TwilioService twilioService;

    public TwilioController(TwilioService twilioService) {
        this.twilioService = twilioService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestParam String to, @RequestParam String message) {
        twilioService.sendSms(to, message);
        return ResponseEntity.ok("SMS trimis!");
    }
}