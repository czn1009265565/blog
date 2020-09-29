package com.czndata.blog.service.dto.tag;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TagParam {
    @NotBlank(message="tagId不能为空")
    private Integer id;

    @NotBlank(message="tag不能为空")
    private String tag;
}
