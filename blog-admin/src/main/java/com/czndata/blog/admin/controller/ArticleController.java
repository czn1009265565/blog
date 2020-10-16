package com.czndata.blog.admin.controller;

import com.czndata.blog.admin.entity.UserProfile;
import com.czndata.blog.service.dto.article.ArticleParam;
import com.czndata.blog.service.enums.ResultEnum;
import com.czndata.blog.service.exception.BlogException;
import com.czndata.blog.service.service.ArticleService;
import com.czndata.blog.service.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseVO<String> add(@Valid ArticleParam articleParam, Authentication authentication){
        UserProfile userProfile = (UserProfile) authentication.getPrincipal();
        articleParam.setUserId(userProfile.getId());

        articleService.create(articleParam);
        return ResponseVO.success(articleParam.getTitle());
    }

    @PutMapping
    public ResponseVO<String> update(Integer articleId, @Valid ArticleParam articleParam, Authentication authentication){
        UserProfile userProfile = (UserProfile) authentication.getPrincipal();
        articleParam.setUserId(userProfile.getId());

        articleService.update(articleId, articleParam);
        return ResponseVO.success(articleParam.getTitle());
    }

}
