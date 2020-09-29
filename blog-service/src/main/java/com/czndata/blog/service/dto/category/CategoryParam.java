package com.czndata.blog.service.dto.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryParam {
    private Integer pid;

    @NotBlank(message = "分类名不能为空")
    private String categoryName;

    @NotBlank(message = "分类介绍不能为空")
    private String categoryDescription;
}
