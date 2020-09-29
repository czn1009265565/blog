package com.czndata.blog.service.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),
    OTHER_ERROR(2, "未知异常"),

    EXIST_TAG(10, "标签已存在"),
    EXIST_CATEGORY(11, "分类已存在")
    ;

    private Integer code;
    private String desc;

    ResultEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
