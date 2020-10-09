package com.czndata.blog.service.dto.article;

import lombok.Data;

@Data
public class ArticleArchiveDto {
    private String date; // 时间日期

    private Integer articleCount; // 文章数量
}
