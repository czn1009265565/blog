package com.czndata.blog.web.controller;

import com.czndata.blog.service.dto.article.ArticleArchiveDto;
import com.czndata.blog.service.dto.article.ArticleDetailDto;
import com.czndata.blog.service.dto.article.ArticleNewestDto;
import com.czndata.blog.service.dto.category.CategoryCountDto;
import com.czndata.blog.service.dto.category.CategoryDto;
import com.czndata.blog.service.service.ArticleCategoryService;
import com.czndata.blog.service.service.ArticleService;
import com.czndata.blog.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/{id}")
    public ModelAndView detail(@PathVariable Integer id, Model model) {
        ArticleDetailDto articleDetailDto = articleService.detail(id);
        model.addAttribute("articleDetailDto", articleDetailDto);

        List<ArticleNewestDto> articleNewestDtoList = articleService.newest();
        model.addAttribute("articleNewestDtoList", articleNewestDtoList);

        List<ArticleArchiveDto> articleArchiveDtoList = articleService.archive();
        model.addAttribute("articleArchiveDtoList", articleArchiveDtoList);

        List<CategoryCountDto> categoryCountDtoList = articleCategoryService.countArticleByCategory();
        model.addAttribute("categoryCountDtoList", categoryCountDtoList);

        List<CategoryDto> categoryDtoList = categoryService.list();
        model.addAttribute("categoryDtoList", categoryDtoList);
        return new ModelAndView("single");
    }
}
