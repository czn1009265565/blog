package com.czndata.blog.web.service.impl;


import com.czndata.blog.service.dto.article.ArticleParam;
import com.czndata.blog.service.dto.article.ArticleSearch;
import com.czndata.blog.service.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class ArticleServiceImplTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void create(){
        ArticleParam articleParam = new ArticleParam();
        articleParam.setUserId(1);
        articleParam.setTitle("Go战未来2");
        articleParam.setContent(".....");
        articleParam.setStatus(1);
        articleParam.setTagIds(Arrays.asList(1,2));
        articleParam.setCategoryIds(Arrays.asList(3));
        articleService.create(articleParam);
    }

    @Test
    void newest(){
        articleService.newest().stream().forEach(System.out::println);
    }

    @Test
    void archive(){
        articleService.archive().stream().forEach(System.out::println);
    }

    @Test
    void list(){
        ArticleSearch articleSearch = new ArticleSearch();
//        articleSearch.setTagId(2);
        articleSearch.setCategoryId(1);
        System.out.println(articleService.list(articleSearch));
    }
}
