package com.czndata.blog.service.dto.contact;

import lombok.Data;

@Data
public class ContactParam {
    private String name;

    private String email;

    private String subject;

    private String message;
}
