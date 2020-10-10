package com.czndata.blog.service.service.impl;

import com.czndata.blog.mbg.dao.ArticleMapper;
import com.czndata.blog.mbg.dao.CommentMapper;
import com.czndata.blog.mbg.entity.Article;
import com.czndata.blog.mbg.entity.Comment;
import com.czndata.blog.mbg.entity.CommentExample;
import com.czndata.blog.service.dao.ArticleMapperExtend;
import com.czndata.blog.service.dto.comment.CommentDto;
import com.czndata.blog.service.dto.comment.CommentParam;
import com.czndata.blog.service.enums.ResultEnum;
import com.czndata.blog.service.exception.BlogException;
import com.czndata.blog.service.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleMapperExtend articleMapperExtend;

    @Override
    public int create(CommentParam commentParam) {
        Integer articleId = commentParam.getArticleId();

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentParam, comment);
        int count = commentMapper.insertSelective(comment);
        articleMapperExtend.addCommentCountById(articleId);
        return count;
    }

    @Override
    public int delete(Integer commentId) {
        Comment comment = commentMapper.selectByPrimaryKey(commentId);
        if (comment == null)throw new BlogException(ResultEnum.NOT_EXIST_COMMENT);

        Integer articleId = comment.getArticleId();
        int count = commentMapper.deleteByPrimaryKey(commentId);

        articleMapperExtend.subCommentCountById(articleId);
        return count;
    }

    @Override
    public List<CommentDto> list(Integer articleId) {
        CommentExample commentExample = new CommentExample();
        commentExample.or().andArticleIdEqualTo(articleId);

        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        return commentList.stream()
                .map(i -> {
                    CommentDto commentDto = new CommentDto();
                    BeanUtils.copyProperties(i, commentDto);
                    return commentDto;
                }).collect(Collectors.toList());
    }
}
