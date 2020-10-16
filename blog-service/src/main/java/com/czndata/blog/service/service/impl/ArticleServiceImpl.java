package com.czndata.blog.service.service.impl;

import com.czndata.blog.mbg.dao.ArticleMapper;
import com.czndata.blog.mbg.entity.*;
import com.czndata.blog.service.constant.BlogConstant;
import com.czndata.blog.service.dao.ArticleCategoryMapperExtend;
import com.czndata.blog.service.dao.ArticleMapperExtend;
import com.czndata.blog.service.dao.ArticleTagMapperExtend;
import com.czndata.blog.service.dto.article.*;
import com.czndata.blog.service.enums.ArticleStatusEnum;
import com.czndata.blog.service.enums.ResultEnum;
import com.czndata.blog.service.exception.BlogException;
import com.czndata.blog.service.service.*;
import com.czndata.blog.service.utils.HtmlUtils;
import com.czndata.blog.service.utils.MarkdownUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleMapperExtend articleMapperExtend;

    @Autowired
    private ArticleTagMapperExtend articleTagMapperExtend;

    @Autowired
    private ArticleCategoryMapperExtend articleCategoryMapperExtend;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Override
    public int create(ArticleParam articleParam) {
        // insert Article
        Article article = new Article();
        BeanUtils.copyProperties(articleParam, article);
        String summary = article.getContent().substring(0, Math.min(BlogConstant.SUMMARY_LENGTH, article.getContent().length()));
        summary = Jsoup.parse(MarkdownUtils.markdown2Html(summary)).text();
        article.setSummary(summary+"...");
        int count = articleMapper.insertSelective(article);
        log.info("新建文章 title:{}", articleParam.getTitle());

        Integer articleId = article.getId();

        // insert article_tag_ref
        articleTagService.insertList(articleParam.getTagIds(), articleId);

        // insert article_category_ref
        articleCategoryService.insertList(articleParam.getCategoryIds(), articleId);
        return count;
    }

    @Override
    public int delete(Integer articleId) {
        int count = articleMapper.deleteByPrimaryKey(articleId);
        articleTagService.deleteByArticleId(articleId);

        articleCategoryService.deleteByArticleId(articleId);
        return count;
    }

    @Override
    public int update(Integer articleId, ArticleParam articleParam) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        if (article == null){
            log.error("文章不存在 articleId:{} title:{} [article update]",articleId, articleParam.getTitle());
            throw new BlogException(ResultEnum.NOT_EXIST_ARTICLE);
        }
        // 如果文章所属用户不同则抛出异常
        if (!article.getUserId().equals(articleParam.getUserId())){
            log.error("文章不属于该作者 articleId:{} userId:{} [article update]", articleId, articleParam.getUserId());
            throw new BlogException(ResultEnum.NOT_EXIST_ARTICLE);
        }

        Article articleUpdate = new Article();
        BeanUtils.copyProperties(articleParam, articleUpdate);
        articleUpdate.setUpdateTime(new Date());
        articleUpdate.setId(articleId);
        int count = articleMapper.updateByPrimaryKeySelective(articleUpdate);

        articleTagService.deleteByArticleId(articleId);
        articleTagService.insertList(articleParam.getTagIds(), articleId);

        articleCategoryService.deleteByArticleId(articleId);
        articleCategoryService.insertList(articleParam.getCategoryIds(), articleId);
        return count;
    }

    @Override
    public void addViewCount(Integer articleId) {
        articleMapperExtend.addViewCountById(articleId);
    }

    @Override
    public PageInfo<ArticleSummaryDto> list(ArticleSearch articleSearch) {
        return list(null, null, articleSearch);
    }

    @Override
    public PageInfo<ArticleSummaryDto> list(Integer pageNum, Integer pageSize, ArticleSearch articleSearch) {
        if (pageNum == null){
            pageNum = BlogConstant.PAGE_NUM;
        }
        if (pageSize == null){
            pageSize = BlogConstant.PAGE_SIZE;
        }

        List<ArticleSummaryDto> articleSummaryDtoList;
        PageHelper.startPage(pageNum,pageSize);
        if (articleSearch.getCategoryId() != null){
            articleSummaryDtoList = articleCategoryMapperExtend.selectArticleByCategoryId(articleSearch.getCategoryId());
        }else if (articleSearch.getTagId() != null){
            articleSummaryDtoList = articleTagMapperExtend.selectArticleByTagId(articleSearch.getTagId());
        }else{
            articleSummaryDtoList = articleMapperExtend.selectArticle();
        }
        return new PageInfo<>(articleSummaryDtoList);
    }

    @Override
    public ArticleDetailDto detail(Integer articleId) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        // 若article不存在或未发布 则抛出异常
        if (article == null || ArticleStatusEnum.NOT_PUBLISH.getCode().equals(article.getStatus())){
            throw new BlogException(ResultEnum.NOT_EXIST_ARTICLE);
        }

        ArticleDetailDto articleDetailDto = new ArticleDetailDto();
        BeanUtils.copyProperties(article, articleDetailDto);

        // 评论
        articleDetailDto.setCommentDtoList(commentService.list(articleId));
        // 用户
        Integer userId = article.getUserId();
        articleDetailDto.setUserDto(userService.detail(userId));
        // markdown 转 hmtl
        String content = article.getContent();
        String html = MarkdownUtils.markdown2Html(content);
        articleDetailDto.setContent(html);
        // 目录
        articleDetailDto.setDirecotryList(HtmlUtils.extractDirectory(html));
        return articleDetailDto;
    }

    @Override
    public List<ArticleNewestDto> newest() {
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andStatusEqualTo(ArticleStatusEnum.PUBLISH.getCode());
        // 展示最新已发布三篇博文
        articleExample.setOrderByClause("create_time DESC limit 3");

        List<Article> articleList = articleMapper.selectByExample(articleExample);

        return articleList.stream().map(i -> {
            ArticleNewestDto articleNewestDto = new ArticleNewestDto();
            BeanUtils.copyProperties(i, articleNewestDto);
            return articleNewestDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ArticleArchiveDto> archive() {
        return articleMapperExtend.countArticleByCreateTime();
    }
}
