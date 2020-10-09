package com.czndata.blog.service.service;

import com.czndata.blog.service.dto.category.CategoryCountDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ArticleCategoryService {

    @Transactional
    void insertList(List<Integer> categoryIds, Integer articleId);

    @Transactional
    int deleteByArticleId(Integer articleId);


    List<CategoryCountDto> countArticleByCategory();
}
