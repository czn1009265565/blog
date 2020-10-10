package com.czndata.blog.service.dao;

import com.czndata.blog.service.dto.article.ArticleArchiveDto;
import com.czndata.blog.service.dto.article.ArticleSummaryDto;

import java.util.List;

public interface ArticleMapperExtend {
    List<ArticleArchiveDto> countArticleByCreateTime();

    List<ArticleSummaryDto> selectArticle();

    void addViewCountById(Integer articleId);

    void addCommentCountById(Integer articleId);

    void subCommentCountById(Integer articleId);
}
