package com.example.lms.serviceimpl;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.example.lms.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
 
	public void sendEmail(String host, String port, String username, String password, String toEmail , String subject, String text) {
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		
		// Set SMTP server details
	    mailSender.setHost(host);
	    mailSender.setPort(Integer.parseInt(port));
	    mailSender.setUsername(username);
	    mailSender.setPassword(password);

	    // Set JavaMailSender properties
	    Properties properties = new Properties();
	    properties.put("mail.smtp.auth", "true"); // Use authentication
	    properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS
	    properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Ensure secure protocol
	    mailSender.setJavaMailProperties(properties);

	    // Create email message
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setFrom(username); // Set from address (usually the username)
	    message.setTo(toEmail); // Set recipient's email address
	    message.setSubject(subject); // Set email subject
	    message.setText(text); // Set email body content

	    // Send email
	    try {
	        mailSender.send(message);
	        System.out.println("Email sent successfully to " + toEmail);
	    } catch (Exception e) {
	        System.out.println("Error sending email: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
}
