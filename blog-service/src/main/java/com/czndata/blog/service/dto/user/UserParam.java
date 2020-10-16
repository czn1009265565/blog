package com.czndata.blog.service.dto.user;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
@Builder
public class UserParam {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private Integer enabled;

    @NotBlank
    private String roles;

    private String introduction;
}
