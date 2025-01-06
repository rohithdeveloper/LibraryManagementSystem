package com.example.lms.service;

public interface EmailService {
    void sendEmail(String host, String port, String username, String password, String toEmail, String subject, String text);
}
