package com.czndata.blog.service.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentParam {
    private Integer pid;

    @NotNull(message = "文章id不能为空")
    private Integer articleId;

    @NotBlank(message = "作者名称不能为空")
    private String authorName;

    private String authorEmail;

    @NotBlank(message = "评论不能为空")
    private String comment;
}
