package com.czndata.blog.service.dao;

import com.czndata.blog.mbg.entity.Article;
import com.czndata.blog.mbg.entity.ArticleCategory;
import com.czndata.blog.service.dto.article.ArticleSummaryDto;
import com.czndata.blog.service.dto.category.CategoryCountDto;

import java.util.List;

public interface ArticleCategoryMapperExtend {

    List<CategoryCountDto> countArticleByCategoryId();

    void insertList(List<ArticleCategory> categoryList);

    List<ArticleSummaryDto> selectArticleByCategoryId(Integer categoryId);
}
