package com.czndata.blog.service.dto.article;

import com.czndata.blog.service.dto.category.CategoryDto;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleDetailDto {
    private Integer id;

    private String title;

    private String content;

    private CategoryDto categoryDto;

    private Date createTime;

    private String username;

    private Integer commentCount;

    private Integer viewCount;
}
