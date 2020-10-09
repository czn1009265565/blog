package com.czndata.blog.web.controller;

import com.czndata.blog.service.dto.article.ArticleArchiveDto;
import com.czndata.blog.service.dto.article.ArticleNewestDto;
import com.czndata.blog.service.dto.article.ArticleSearch;
import com.czndata.blog.service.dto.article.ArticleSummaryDto;
import com.czndata.blog.service.dto.category.CategoryCountDto;
import com.czndata.blog.service.dto.category.CategoryDto;
import com.czndata.blog.service.dto.tag.TagDto;
import com.czndata.blog.service.service.ArticleCategoryService;
import com.czndata.blog.service.service.ArticleService;
import com.czndata.blog.service.service.CategoryService;
import com.czndata.blog.service.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    private TagService tagService;

    @GetMapping
    public ModelAndView index(
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @ModelAttribute ArticleSearch articleSearch,
            Model model
            ){
        PageInfo<ArticleSummaryDto> pageInfo = articleService.list(pageNum, pageSize, articleSearch);
        model.addAttribute("pageInfo", pageInfo);

        List<ArticleNewestDto> articleNewestDtoList = articleService.newest();
        model.addAttribute("articleNewestDtoList", articleNewestDtoList);

        List<ArticleArchiveDto> articleArchiveDtoList = articleService.archive();
        model.addAttribute("articleArchiveDtoList", articleArchiveDtoList);

        List<CategoryCountDto> categoryCountDtoList = articleCategoryService.countArticleByCategory();
        model.addAttribute("categoryCountDtoList", categoryCountDtoList);

        List<TagDto> tagDtoList = tagService.list();
        model.addAttribute("tagDtoList", tagDtoList);

        model.addAttribute("articleSearch", articleSearch);
        System.out.println(articleSearch);
        return new ModelAndView("index");
    }
}
