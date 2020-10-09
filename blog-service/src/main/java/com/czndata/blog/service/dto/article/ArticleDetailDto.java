package com.czndata.blog.service.dto.article;

import com.czndata.blog.service.dto.comment.CommentDto;
import com.czndata.blog.service.dto.user.UserDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleDetailDto {
    private Integer id;

    private String title;

    private String content;

    private Date createTime;

    private Integer commentCount;

    private Integer viewCount;

    private UserDto userDto;

    private List<CommentDto> commentDtoList;

    private List<String> direcotryList; // 目录
}
