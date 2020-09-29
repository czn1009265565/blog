package com.czndata.blog.service.service;

public interface MailService {
    void sendSimpleMail(String to, String subject, String content);
}
