<!DOCTYPE html>
<html>
<head>
    <title>ZeNan's Blog</title>

    <#include "components/meta.ftl">
</head>

<body id="single">
<#include "components/header.ftl">

<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-8">
                <article class="post post-1">
                    <header class="entry-header">
                        <h1 class="entry-title">${articleDetailDto.title}</h1>
                        <div class="entry-meta">
                            <span class="post-date"><a href="#"><time class="entry-date">${articleDetailDto.createTime?datetime}</time></a></span>
                            <span class="post-author"><a href="#">${articleDetailDto.userDto.username}</a></span>
                            <span class="comments-link"><a href="#">${articleDetailDto.commentCount} 评论</a></span>
                            <span class="views-count"><a href="#">${articleDetailDto.viewCount} 阅读</a></span>
                        </div>
                    </header>
                    <div class="entry-content clearfix">
                        ${articleDetailDto.content}
                    </div>
                </article>
                <section class="comment-area" id="comment-area">
                    <hr>
                    <h3>发表评论</h3>
                    <form action="/comment" method="post" class="comment-form">
                        <div class="row">
                            <div class="col-md-4" style="display: none">
                                <input type="text" name="articleId" value="${articleDetailDto.id}" required>
                            </div>

                            <div class="col-md-4">
                                <label for="id_name">名字：</label>
                                <input type="text" id="id_name" name="authorName" required>
                            </div>
                            <div class="col-md-4">
                                <label for="id_email">邮箱：</label>
                                <input type="email" id="id_email" name="authorEmail" required>
                            </div>
                            <div class="col-md-4">
                                <label for="id_url">网址：</label>
                                <input type="text" id="id_url" name="url">
                            </div>
                            <div class="col-md-12">
                                <label for="id_comment">评论：</label>
                                <textarea name="comment" id="id_comment" required></textarea>
                                <button type="submit" class="comment-btn">发表</button>
                            </div>
                        </div>    <!-- row -->
                    </form>
                    <div class="comment-list-panel">
                        <h3>评论列表，共 <span>${articleDetailDto.commentCount}</span> 条评论</h3>
                        <ul class="comment-list list-unstyled">
                            <#list articleDetailDto.commentDtoList as comment>
                                <li class="comment-item">
                                    <span class="nickname">${comment.authorName}</span>
                                    <time class="submit-date">${comment.createTime?datetime}</time>
                                    <div class="text">
                                        ${comment.comment}
                                    </div>
                                </li>
                            </#list>


                        </ul>
                    </div>
                </section>
            </main>
            <aside class="col-md-4">
                <div class="widget widget-content">
                    <h3 class="widget-title">文章目录</h3>
                    <ul>
                        <#list articleDetailDto.direcotryList as direcotry>
                            <li>
                                <a href="#">${direcotry}</a>
                            </li>
                        </#list>


                    </ul>
                </div>
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
                                <a href="/?categoryId=${category.id}">${category.categoryName}<span class="post-count">(${category.articleCount!"0"})</span></a>
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
