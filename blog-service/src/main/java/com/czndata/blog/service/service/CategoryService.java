package com.czndata.blog.service.service;

import com.czndata.blog.service.dto.category.CategoryCountDto;
import com.czndata.blog.service.dto.category.CategoryParam;

import java.util.List;

public interface CategoryService {
    /**
     * 创建分类
     * @param categoryParam category参数
     * @return count
     */
    int create(CategoryParam categoryParam);

    /**
     * 删除文章分类
     * @param categoryId 分类id
     * @return count
     */
    int delete(Integer categoryId);

    /**
     * 更新 category
     * @param categoryId 分类id
     * @param categoryParam category参数
     * @return count
     */
    int update(Integer categoryId, CategoryParam categoryParam);

    /**
     * 分类列表
     * @return List<CategoryCountDto>
     */
    List<CategoryCountDto> list();
}
