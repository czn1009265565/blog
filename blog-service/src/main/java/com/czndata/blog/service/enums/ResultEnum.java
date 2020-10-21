package com.czndata.blog.service.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),
    OTHER_ERROR(2, "未知异常"),

    EXIST_ARTICLE(9, "文章已存在"),
    EXIST_TAG(10, "标签已存在"),
    EXIST_CATEGORY(11, "分类已存在"),
    NOT_EXIST_TAG(12, "标签不存在"),
    NOT_EXIST_CATEGORY(13, "分类不存在"),
    NOT_EXIST_ARTICLE(14, "文章不存在"),
    NOT_EXIST_COMMENT(15, "评论不存在"),
    NOT_EXIST_INTRODUCTION(16, "自我接收不存在"),
    NOT_EXIST_USER(17, "用户不存在"),
    USERNAME_IS_EMPTY(18, "用户名为空")
    ;

    private Integer code;
    private String desc;

    ResultEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
