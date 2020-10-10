package com.czndata.blog.web.controller;

import com.czndata.blog.service.dto.article.ArticleSearch;
import com.czndata.blog.service.dto.article.ArticleSummaryDto;
import com.czndata.blog.service.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public ModelAndView index(
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @ModelAttribute ArticleSearch articleSearch,
            Model model
            ){
        PageInfo<ArticleSummaryDto> pageInfo = articleService.list(pageNum, pageSize, articleSearch);
        model.addAttribute("pageInfo", pageInfo);

        model.addAttribute("articleSearch", articleSearch);
        return new ModelAndView("index");
    }
}
