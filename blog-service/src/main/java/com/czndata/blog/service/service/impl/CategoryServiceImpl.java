package com.czndata.blog.service.service.impl;

import com.czndata.blog.mbg.dao.CategoryMapper;
import com.czndata.blog.mbg.entity.Category;
import com.czndata.blog.mbg.entity.CategoryExample;
import com.czndata.blog.service.dto.category.CategoryDto;
import com.czndata.blog.service.dto.category.CategoryParam;
import com.czndata.blog.service.enums.ResultEnum;
import com.czndata.blog.service.exception.BlogException;
import com.czndata.blog.service.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int create(CategoryParam categoryParam) {
        // 去重判断
        String categoryName = categoryParam.getCategoryName();
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategoryNameEqualTo(categoryName);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        if (categoryList.size() > 0){
            throw new BlogException(ResultEnum.EXIST_CATEGORY);
        }

        // insert
        Category category = new Category();
        BeanUtils.copyProperties(categoryParam, category);
        return categoryMapper.insertSelective(category);
    }

    @Override
    public int delete(Integer categoryId) {
        return categoryMapper.deleteByPrimaryKey(categoryId);
    }

    @Override
    public int update(Integer categoryId, CategoryParam categoryParam) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryParam, category);
        category.setId(categoryId);
        category.setUpdateTime(new Date());
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public List<CategoryDto> list() {
        List<Category> categoryList = categoryMapper.selectByExample(new CategoryExample());
        return categoryList.stream()
                .map(i -> {
                    CategoryDto categoryDto = new CategoryDto();
                    BeanUtils.copyProperties(i, categoryDto);
                    return categoryDto;
                })
                .collect(Collectors.toList());
    }
}
