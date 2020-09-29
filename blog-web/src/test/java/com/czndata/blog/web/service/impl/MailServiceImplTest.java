package com.czndata.blog.web.service.impl;

import com.czndata.blog.service.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;

    @Test
    public void sendSimpleMail(){
        mailService.sendSimpleMail("1009265565@qq.com", "i am you", "i believe you van better");
    }
}
