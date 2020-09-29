package com.czndata.blog.service.dto.introduction;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class IntroductionParam {
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @NotBlank(message = "介绍不能为空")
    private String introduction;
}
