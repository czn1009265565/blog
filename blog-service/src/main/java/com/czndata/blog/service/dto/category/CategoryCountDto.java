package com.czndata.blog.service.dto.category;

import lombok.Data;

@Data
public class CategoryCountDto {
    private Integer id;

    private String categoryName;

    private Integer articleCount;
}
