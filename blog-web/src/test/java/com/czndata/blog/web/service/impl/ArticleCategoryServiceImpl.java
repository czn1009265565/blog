package com.czndata.blog.web.service.impl;

import com.czndata.blog.service.service.ArticleCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ArticleCategoryServiceImpl {

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Test
    void countArticleByCategory(){
        articleCategoryService.countArticleByCategory().forEach(System.out::println);
    }

    @Test
    void insertList(){
        List<Integer> arr = Arrays.asList(1,2,3);
        articleCategoryService.insertList(arr, 2);
    }
}
