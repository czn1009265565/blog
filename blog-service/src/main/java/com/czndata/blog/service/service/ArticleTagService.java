package com.czndata.blog.service.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArticleTagService {

    @Transactional
    void insertList(List<Integer> tagIds, Integer articleId);

    int deleteByArticleId(Integer articleId);
}
