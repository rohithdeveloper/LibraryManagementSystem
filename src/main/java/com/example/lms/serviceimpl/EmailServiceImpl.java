package com.example.lms.serviceimpl;

import com.example.lms.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService{

    @Override
    public void sendEmail(String host, String port, String username, String password, String toEmail, String subject, String text) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Setting up the SMTP server details
        mailSender.setHost(host);
        mailSender.setPort(Integer.parseInt(port));
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        // Set up mail server properties
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Creating the email message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);  // Sender's email
        message.setTo(toEmail);     // Recipient's email
        message.setSubject(subject);  // Subject of the email
        message.setText(text);        // Body content of the email

        // Send the email
        mailSender.send(message);
    }
}
