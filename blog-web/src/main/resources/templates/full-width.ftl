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
					<main class="col-md-12">
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
									<li><a href="/article/?pageNum=${pageInfo.prePage}"><<</a></li>
								</#if>

								<#if (pageInfo.pageNum-2) gt 0>
									<li><a href="/article/?pageNum=${pageInfo.pageNum-2}">${pageInfo.pageNum-2}</a></li>
								</#if>
								<#if (pageInfo.pageNum-1) gt 0>
									<li><a href="/article/?pageNum=${pageInfo.pageNum-1}">${pageInfo.pageNum-1}</a></li>
								</#if>

								<li class="current"><a href="/article/?pageNum=${pageInfo.pageNum}">${pageInfo.pageNum}</a></li>

								<#if pageInfo.pageNum lt pageInfo.pages>
									<li><a href="/article/?pageNum=${pageInfo.pageNum+1}">${pageInfo.pageNum+1}</a></li>
								</#if>

								<#if (pageInfo.pageNum+1) lt pageInfo.pages>
									<li><a href="/article/?pageNum=${pageInfo.pageNum+2}">${pageInfo.pageNum+2}</a></li>
								</#if>

								<#if pageInfo.hasNextPage>
									<li><a href="/article/?pageNum=${pageInfo.nextPage}">>></a></li>
								</#if>
							</ul>
						</div>
					</main>
				</div>
			</div>
		</div>
		<#include "components/footer.ftl">

		<#include "components/mobile.ftl">
		<script src="/static/js/script.js"></script>
	</body>
</html>
