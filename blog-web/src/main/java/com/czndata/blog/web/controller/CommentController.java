package com.czndata.blog.web.controller;

import com.czndata.blog.service.dto.comment.CommentParam;
import com.czndata.blog.service.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public String add(@Valid CommentParam commentParam){
        Integer articleId = commentParam.getArticleId();

        commentService.create(commentParam);
        return "redirect:" + "/article" + "/" + articleId;
    }
}
