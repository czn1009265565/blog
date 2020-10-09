package com.czndata.blog.service.service;

import com.czndata.blog.service.dto.article.*;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArticleService {
    /**
     * 创建文章
     * @param articleParam 文章param
     * @return count
     */
    @Transactional
    int create(ArticleParam articleParam);

    /**
     * 删除文章
     * @param articleId 文章id
     * @return count
     */
    @Transactional
    int delete(Integer articleId);

    /**
     * 更新文章
     * @param articleId 文章id
     * @param articleParam 文章参数
     * @return count
     */
    @Transactional
    int update(Integer articleId, ArticleParam articleParam);

    PageInfo<ArticleSummaryDto> list(ArticleSearch articleSearch);
    PageInfo<ArticleSummaryDto> list(Integer pageNum, Integer pageSize, ArticleSearch articleSearch);

    /**
     * 文章详情
     * @param articleId 文章id
     * @return ArticleDetailDto
     */
    ArticleDetailDto detail(Integer articleId);

    /**
     * 获取最新文章列表
     * @return List<ArticleNewestDto>
     */
    List<ArticleNewestDto> newest();

    /**
     * 文章归档数据展示
     * @return List<ArticleArchiveDto>
     */
    List<ArticleArchiveDto> archive();
}
