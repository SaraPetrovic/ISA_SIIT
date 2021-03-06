package ProjectIsa.bioskop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	JavaMailSenderImpl mailSender;
	
    public void sendSimpleMessage(){
      
        
    }

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);
		
	}
}
