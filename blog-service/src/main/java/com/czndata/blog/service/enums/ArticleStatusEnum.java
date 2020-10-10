package com.czndata.blog.service.enums;

import lombok.Getter;

@Getter
public enum ArticleStatusEnum {
    PUBLISH(1, "已发布"),
    NOT_PUBLISH(0, "未发布")
    ;

    private Integer code;
    private String desc;

    ArticleStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
