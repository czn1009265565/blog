package com.czndata.blog.service.dto.article;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ArticleParam {
    @NotBlank(message = "文章标题不能为空")
    private String title;

    private String summary;

    private Integer status;

    @NotBlank(message = "文章内容不能为空")
    private String content;

    @NotEmpty(message = "文章标签不能为空")
    private List<Integer> tagIds;

    @NotEmpty(message = "文章分类不能为空")
    private List<Integer> categoryId;
}
