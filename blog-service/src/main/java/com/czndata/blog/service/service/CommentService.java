package com.czndata.blog.service.service;

import com.czndata.blog.service.dto.comment.CommentDto;
import com.czndata.blog.service.dto.comment.CommentParam;

import java.util.List;

public interface CommentService {
    /**
     * 新建评论
     * @param commentParam comment
     * @return count
     */
    int create(CommentParam commentParam);

    /**
     * 删除评论
     * @param commentId 评论id
     * @return count
     */
    int delete(Integer commentId);

    /**
     * 当前文章下的所有评论
     * @param articleId 文章id
     * @return List<CommentDto>
     */
    List<CommentDto> list(Integer articleId);
}
