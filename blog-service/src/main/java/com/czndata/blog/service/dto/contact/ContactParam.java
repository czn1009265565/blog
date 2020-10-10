package com.czndata.blog.service.dto.contact;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ContactParam {
    @NotBlank(message = "Name 不能为空")
    private String name;

    @Email(message = "email 格式不符合规范")
    private String email;

    @NotBlank(message = "主题不能为空")
    private String subject;

    @NotBlank(message = "留言不能为空")
    private String message;
}
