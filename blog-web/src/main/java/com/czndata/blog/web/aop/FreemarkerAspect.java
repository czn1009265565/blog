package com.czndata.blog.web.aop;


import com.czndata.blog.service.dto.article.ArticleArchiveDto;
import com.czndata.blog.service.dto.article.ArticleNewestDto;
import com.czndata.blog.service.dto.article.ArticleSearch;
import com.czndata.blog.service.dto.category.CategoryCountDto;
import com.czndata.blog.service.dto.tag.TagDto;
import com.czndata.blog.service.service.ArticleCategoryService;
import com.czndata.blog.service.service.ArticleService;
import com.czndata.blog.service.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Aspect
@Component
public class FreemarkerAspect {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Pointcut("execution(public * com.czndata.blog.web.controller.IndexController.*(..)) " +
            "|| execution(public * com.czndata.blog.web.controller.ArticleController.detail(..))")
    public void point() {
    };

    @AfterReturning(pointcut = "point()",returning = "object")
    public void doAfterReturning(Object object){
        ModelAndView modelAndView = (ModelAndView) object;

        // 最新
        List<ArticleNewestDto> articleNewestDtoList = articleService.newest();
        modelAndView.addObject("articleNewestDtoList", articleNewestDtoList);

        // 归档
        List<ArticleArchiveDto> articleArchiveDtoList = articleService.archive();
        modelAndView.addObject("articleArchiveDtoList", articleArchiveDtoList);

        // 分类统计
        List<CategoryCountDto> categoryCountDtoList = articleCategoryService.countArticleByCategory();
        modelAndView.addObject("categoryCountDtoList", categoryCountDtoList);

        // 标签
        List<TagDto> tagDtoList = tagService.list();
        modelAndView.addObject("tagDtoList", tagDtoList);
    }
}
