package com.czndata.blog.service.dao;

import com.czndata.blog.mbg.entity.ArticleTag;
import com.czndata.blog.service.dto.article.ArticleSummaryDto;

import java.util.List;

public interface ArticleTagMapperExtend {
    void insertList(List<ArticleTag> articleTags);

    List<ArticleSummaryDto> selectArticleByTagId(Integer tagId);
}
