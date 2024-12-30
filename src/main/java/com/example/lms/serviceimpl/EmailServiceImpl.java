//package com.example.lms.serviceimpl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import com.example.lms.service.EmailService;
//
//@Service
//public class EmailServiceImpl implements EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//    
//    @Autowired
//    private User
//
//    @Override
//    public void sendEmail(String to, String subject, String body) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("rohithparimella724@gmail.com");
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(body);
//        mailSender.send(message);
//        System.out.println("mail send successfully....");
//    }
//}
