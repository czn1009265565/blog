package com.czndata.blog.web.controller;

import com.czndata.blog.service.dto.article.ArticleDetailDto;
import com.czndata.blog.service.dto.article.ArticleSearch;
import com.czndata.blog.service.dto.article.ArticleSummaryDto;
import com.czndata.blog.service.service.ArticleService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public ModelAndView list(
            @RequestParam(value = "pageNum",required = false) Integer pageNum,
            @RequestParam(value = "pageSize",required = false) Integer pageSize,
            Model model){
        PageInfo<ArticleSummaryDto> pageInfo = articleService.list(pageNum, pageSize, new ArticleSearch());
        model.addAttribute("pageInfo", pageInfo);
        return new ModelAndView("full-width");
    }

    @GetMapping(value = "/{id}")
    public ModelAndView detail(@PathVariable Integer id, Model model) {
        ArticleDetailDto articleDetailDto = articleService.detail(id);
        model.addAttribute("articleDetailDto", articleDetailDto);

        // 增加浏览记录
        articleService.addViewCount(id);
        return new ModelAndView("single");
    }
//    @PostMapping
//    @ResponseBody
//    public ResponseVO create(@Valid ArticleParam articleParam){
//        String title = articleParam.getTitle();
//
//        int num = articleService.create(articleParam);
//        if (num == 1){
//            return ResponseVO.success(String.format("创建文章:%s 成功", title));
//        }
//        return ResponseVO.success(String.format("创建文章:%s 失败", title));
//    }
}
