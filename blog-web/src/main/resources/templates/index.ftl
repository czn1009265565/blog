<!DOCTYPE html>
<html>
<head>
    <title>ZeNan's Blog</title>
    <#include "components/meta.ftl">
</head>

<body>
<#include "components/header.ftl">
<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-8">
                <#list pageInfo.getList() as article>
                    <article class="post post-1">
                        <header class="entry-header">
                            <h1 class="entry-title">
                                <a href="/article/${article.id}">${article.title}</a>
                            </h1>
                            <div class="entry-meta">
                                <span class="post-date"><a href="#">
                                        <time class="entry-date">${article.createTime?date}</time></a>
                                </span>
                                <span class="post-author"><a href="#">${article.username}</a></span>
                                <span class="comments-link"><a href="#">${article.commentCount} 评论</a></span>
                                <span class="views-count"><a href="#">${article.viewCount} 阅读</a></span>
                            </div>
                        </header>
                        <div class="entry-content clearfix">
                            <p>${article.summary}</p>
                            <div class="read-more cl-effect-14">
                                <a href="/article/${article.id}" class="more-link">继续阅读 <span class="meta-nav">→</span></a>
                            </div>
                        </div>
                    </article>
                </#list>

                <div class="pagination">
                    <ul>
                        <#if pageInfo.hasPreviousPage>
                            <li><a href="/?pageNum=${pageInfo.prePage}&categoryId=${articleSearch.categoryId!""}&tagId=${articleSearch.tagId!""}"><<</a></li>
                        </#if>

                        <#if (pageInfo.pageNum-2) gt 0>
                            <li><a href="/?pageNum=${pageInfo.pageNum-2}&categoryId=${articleSearch.categoryId!""}&tagId=${articleSearch.tagId!""}">${pageInfo.pageNum-2}</a></li>
                        </#if>
                        <#if (pageInfo.pageNum-1) gt 0>
                            <li><a href="/?pageNum=${pageInfo.pageNum-1}&categoryId=${articleSearch.categoryId!""}&tagId=${articleSearch.tagId!""}">${pageInfo.pageNum-1}</a></li>
                        </#if>

                        <li class="current"><a href="/?pageNum=${pageInfo.pageNum}&categoryId=${articleSearch.categoryId!""}&tagId=${articleSearch.tagId!""}">${pageInfo.pageNum}</a></li>

                        <#if pageInfo.pageNum lt pageInfo.pages>
                            <li><a href="/?pageNum=${pageInfo.pageNum+1}&categoryId=${articleSearch.categoryId!""}&tagId=${articleSearch.tagId!""}">${pageInfo.pageNum+1}</a></li>
                        </#if>

                        <#if (pageInfo.pageNum+1) lt pageInfo.pages>
                            <li><a href="/?pageNum=${pageInfo.pageNum+2}&categoryId=${articleSearch.categoryId!""}&tagId=${articleSearch.tagId!""}">${pageInfo.pageNum+2}</a></li>
                        </#if>

                        <#if pageInfo.hasNextPage>
                            <li><a href="/?pageNum=${pageInfo.nextPage}&categoryId=${articleSearch.categoryId!""}&tagId=${articleSearch.tagId!""}">>></a></li>
                        </#if>
                    </ul>
                </div>
            </main>
            <aside class="col-md-4">
                <div class="widget widget-recent-posts">
                    <h3 class="widget-title">最新文章</h3>
                    <ul>
                        <#list articleNewestDtoList as newest>
                            <li>
                                <a href="/article/${newest.id}">${newest.title}</a>
                            </li>
                        </#list>

                    </ul>
                </div>
                <div class="widget widget-archives">
                    <h3 class="widget-title">归档</h3>
                    <ul>
                        <#list articleArchiveDtoList as archive>
                            <li>
                                <a href="#">${archive.date}(${archive.articleCount})</a>
                            </li>
                        </#list>
                    </ul>
                </div>

                <div class="widget widget-category">
                    <h3 class="widget-title">分类</h3>
                    <ul>
                        <#list categoryCountDtoList as category>
                            <li>
                                <a href="/?categoryId=${category.id}">${category.categoryName}<span class="post-count">(${category.articleCount})</span></a>
                            </li>
                        </#list>
                    </ul>
                </div>

                <div class="widget widget-tag-cloud">
                    <h3 class="widget-title">标签云</h3>
                    <ul>
                        <#list tagDtoList as tag>
                            <li>
                                <a href="/?tagId=${tag.id}">${tag.tag}</a>
                            </li>
                        </#list>

                    </ul>
                </div>
            </aside>
        </div>
    </div>
</div>
<#include "components/footer.ftl">

<#include "components/mobile.ftl">
<script src="/static/js/script.js"></script>

</body>
</html>
