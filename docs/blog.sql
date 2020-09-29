
create table `blog_article`(
    `id` int(11) not null primary key auto_increment comment 'id',
    `title` varchar(255) not null comment '标题',
    `content` text comment '内容',
    `summary` varchar(500) not null comment '概要',
    `user_id` int(11) not null comment '用户id',
    `view_count` int(11) not null default '0' comment '浏览次数',
    `comment_count` int(11) not null default '0' comment '评论次数',
    `status` int(4) not null default '0' comment '发布状态 0:未发布 1:已发布',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp comment '更新时间'
)comment='article';
create unique index `title_index` using btree on `blog_article`(`title`);


create table `blog_tag`(
    `id` int(11) not null primary key auto_increment comment 'id',
    `tag` varchar(30) not null comment 'tag',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp comment '更新时间'
)comment='tag';
create unique index `tag_index` using btree on `blog_tag`(`tag`);


create table `blog_category`(
    `id` int(11) not null primary key auto_increment comment 'id',
    `pid` int(11) not null default '0' comment 'parent id',
    `category_name` varchar(30) not null comment 'category name',
    `category_description` varchar(255) comment 'category description',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp comment '更新时间'
)comment='category';
create unique index `category_index` using btree on `blog_category`(`category_name`);


create table `blog_article_tag`(
    `id` int(11) not null primary key auto_increment comment 'id',
    `article_id` int(11) not null comment 'article id',
    `tag_id` int(11) not null comment 'tag id'
)comment='文章标签关联表';
create index `article_id_index` using btree on `blog_article_tag`(`article_id`);
create index `tag_id_index` using btree on `blog_article_tag`(`tag_id`);


create table `blog_article_category`(
    `id` int(11) not null primary key auto_increment comment 'id',
    `article_id` int(11) not null comment 'article id',
    `category_id` int(11) not null comment 'category id'
)comment='文章分类关联表';
create index `article_id_index` using btree on `blog_article_category`(`article_id`);
create index `category_id_index` using btree on `blog_article_category`(`category_id`);


create table `blog_comment`(
    `id` int(11) not null primary key auto_increment comment 'id',
    `pid` int(11) not null default '0',
    `article_id` int(11) not null comment 'article id',
    `author_name` varchar(30) not null comment '作者名称',
    `author_email` varchar(100) not null comment '作者Email',
    `comment` varchar(500) not null comment '评论',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp comment '更新时间'
)comment='评论表';


create table `blog_contact`(
    `id` int(11) not null primary key auto_increment comment 'id',
    `name` varchar(30) not null comment 'user name',
    `email` varchar(100) not null comment '联系方式',
    `subject` varchar(100) not null comment '主题',
    `message` text comment '留言',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp comment '更新时间'
)comment='留言表';


 create table `blog_user`(
     `id` int(11) not null auto_increment primary key,
     `username` varchar(50) not null,
     `password` varchar(50) not null,
     `enabled` int(4) not null default '0' comment '用户是否可用 0:不可用 1:可用',
     `roles` varchar(255) comment '用户角色，多个角色之间用逗号分隔',
     `create_time` datetime not null default current_timestamp comment '创建时间',
     `update_time` datetime not null default current_timestamp comment '更新时间'
 )comment='用户';
create unique index `username_index` using btree on `blog_user`(`username`);

create table `blog_introduction`(
    `id` int(11) not null auto_increment primary key,
    `user_id` int(11) not null comment '用户id',
    `introduction` text comment '自我介绍',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp comment '更新时间'
)comment='自我介绍';

