package com.czndata.blog.service.service;

import com.czndata.blog.service.dto.article.*;

import java.util.List;

public interface ArticleService {
    /**
     * 创建文章
     * @param articleParam 文章param
     * @return count
     */
    int create(ArticleParam articleParam);

    /**
     * 删除文章
     * @param articleId 文章id
     * @return count
     */
    int delete(Integer articleId);

    /**
     * 更新文章
     * @param articleId 文章id
     * @param articleParam 文章参数
     * @return count
     */
    int update(Integer articleId, ArticleParam articleParam);


    List<ArticleSummaryDto> list();
    List<ArticleSummaryDto> list(ArticleSearch articleSearch);
    List<ArticleSummaryDto> list(Integer pageNum, Integer pageSize, ArticleSearch articleSearch);

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
}
