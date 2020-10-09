package com.czndata.blog.service.service.impl;

import com.czndata.blog.mbg.dao.ArticleTagMapper;
import com.czndata.blog.mbg.entity.ArticleTag;
import com.czndata.blog.mbg.entity.ArticleTagExample;
import com.czndata.blog.service.dao.ArticleTagMapperExtend;
import com.czndata.blog.service.dto.tag.TagDto;
import com.czndata.blog.service.enums.ResultEnum;
import com.czndata.blog.service.exception.BlogException;
import com.czndata.blog.service.service.ArticleTagService;
import com.czndata.blog.service.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleTagServiceImpl implements ArticleTagService {
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private ArticleTagMapperExtend articleTagMapperExtend;

    @Autowired
    private TagService tagService;

    @Override
    public void insertList(List<Integer> tagIds, Integer articleId) {
        if (tagIds == null || tagIds.size() == 0)throw new BlogException(ResultEnum.NOT_EXIST_TAG);

        Map<Integer, String> map = tagService.list().stream().collect(Collectors.toMap(TagDto::getId, TagDto::getTag));
        for (Integer tagId : tagIds){
            if (map.get(tagId) == null)throw new BlogException(ResultEnum.NOT_EXIST_TAG);
        }
        List<ArticleTag> articleTagList = tagIds.stream().map(i->{
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(articleId);
            articleTag.setTagId(i);
            return articleTag;
        })
                .collect(Collectors.toList());
        articleTagMapperExtend.insertList(articleTagList);
    }

    @Override
    public int deleteByArticleId(Integer articleId) {
        ArticleTagExample articleTagExample = new ArticleTagExample();
        articleTagExample.or().andArticleIdEqualTo(articleId);
        return articleTagMapper.deleteByExample(articleTagExample);
    }

}
