package com.czndata.blog.web.controller;

import com.czndata.blog.service.dto.contact.ContactParam;
import com.czndata.blog.service.service.ContactService;
import com.czndata.blog.service.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private MailService mailService;

    @Value("${mail.toMail.addr}")
    private String to;

    @GetMapping
    public ModelAndView get(){
        return new ModelAndView("contact");
    }

    @PostMapping
    public ModelAndView post(@Valid ContactParam contactParam){
        // 创建留言
        contactService.create(contactParam);

        String subject = contactParam.getSubject();
        String email = contactParam.getEmail();
        String name = contactParam.getName();
        String content = contactParam.getMessage() + String.format("\n来自Name:%s,Email:%s", name, email);
        // 发送邮件
        mailService.sendSimpleMail(to, subject, content);
        return new ModelAndView("contact-success");
    }
}
