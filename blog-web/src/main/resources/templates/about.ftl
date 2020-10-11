<!DOCTYPE html>
<html>
<head>
    <title>ZeNan's Blog</title>
    <#include "components/meta.ftl">
</head>

<body id="page">
<#include "components/header.ftl">

<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-12">
                <h1 class="page-title">关于我</h1>
                <article class="post">
                    <div class="entry-content clearfix" id="content">
                        ${userDto.introduction!""}
                    </div>
                </article>
            </main>
        </div>
    </div>
</div>
<#include "components/footer.ftl">

<#include "components/mobile.ftl">
<script src="/static/js/script.js"></script>

</body>
</html>
