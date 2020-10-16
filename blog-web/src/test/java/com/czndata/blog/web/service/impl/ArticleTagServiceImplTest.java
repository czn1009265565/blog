package com.czndata.blog.web.service.impl;

import com.czndata.blog.service.service.ArticleTagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@SpringBootTest
public class ArticleTagServiceImplTest {
    @Autowired
    private ArticleTagService articleTagService;

    @Test
    void insertListTest(){
        List<Integer> arr = Arrays.asList(1,2);
        articleTagService.insertList(arr, 1);
    }
}
