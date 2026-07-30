package proiect.demo.web.ang_spring.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.annotation.PostConstruct;

//@Service
//public class TwilioService {
//
//	@Value("${twilio.account.sid}")
//	private String accountSid;
//	
//	@Value("${twilio.auth.token}")
//	private String authToken;
//	
//	@Value("${twilio.phone.number}")
//	private String fromNumber;
//	
//	@PostConstruct
//	public void init() {
//		Twilio.init(accountSid, authToken);
//	}
//	
//	public void sendSms(String toPhoneNumber, String messageText) {
//        Message message = Message.creator(
//                new PhoneNumber(toPhoneNumber),
//                new PhoneNumber(fromNumber),
//                messageText
//        ).create();
//
//        System.out.println("SMS sent, SID: " + message.getSid());
//    }
//	
//}
