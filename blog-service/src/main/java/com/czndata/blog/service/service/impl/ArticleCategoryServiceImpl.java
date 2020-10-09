package com.czndata.blog.service.service.impl;

import com.czndata.blog.mbg.dao.ArticleCategoryMapper;
import com.czndata.blog.mbg.entity.ArticleCategory;
import com.czndata.blog.mbg.entity.ArticleCategoryExample;
import com.czndata.blog.service.dao.ArticleCategoryMapperExtend;
import com.czndata.blog.service.dto.category.CategoryCountDto;
import com.czndata.blog.service.dto.category.CategoryDto;
import com.czndata.blog.service.enums.ResultEnum;
import com.czndata.blog.service.exception.BlogException;
import com.czndata.blog.service.service.ArticleCategoryService;
import com.czndata.blog.service.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleCategoryServiceImpl implements ArticleCategoryService {
    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

    @Autowired
    private ArticleCategoryMapperExtend articleCategoryMapperExtend;

    @Autowired
    private CategoryService categoryService;


    @Override
    public void insertList(List<Integer> categoryIds, Integer articleId) {
        if (categoryIds == null || categoryIds.size() == 0) throw new BlogException(ResultEnum.NOT_EXIST_CATEGORY);

        Map<Integer, String> map = categoryService.list().stream()
                .collect(Collectors.toMap(CategoryDto::getId, CategoryDto::getCategoryName));
        for (Integer categoryId : categoryIds){
            if (map.get(categoryId) == null)throw new BlogException(ResultEnum.NOT_EXIST_CATEGORY);
        }

        List<ArticleCategory> articleCategoryList = categoryIds.stream().map(i -> {
            ArticleCategory articleCategory = new ArticleCategory();
            articleCategory.setCategoryId(i);
            articleCategory.setArticleId(articleId);
            return articleCategory;
        }).collect(Collectors.toList());
        articleCategoryMapperExtend.insertList(articleCategoryList);
    }

    @Override
    public int deleteByArticleId(Integer articleId) {
        ArticleCategoryExample articleCategoryExample = new ArticleCategoryExample();
        articleCategoryExample.or().andArticleIdEqualTo(articleId);
        return articleCategoryMapper.deleteByExample(articleCategoryExample);
    }

    @Override
    public List<CategoryCountDto> countArticleByCategory() {
        return articleCategoryMapperExtend.countArticleByCategoryId();
    }
}
