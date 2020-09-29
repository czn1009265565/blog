package com.czndata.blog.service.dto.comment;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private String authorName;

    private String comment;

    private Date createTime;
}
