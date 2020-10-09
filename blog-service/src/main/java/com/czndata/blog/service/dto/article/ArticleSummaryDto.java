package com.czndata.blog.service.dto.article;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleSummaryDto {
    private Integer id;

    private String title;

    private String summary;

    private Date createTime;

    private String username;

    private Integer commentCount;

    private Integer viewCount;
}
