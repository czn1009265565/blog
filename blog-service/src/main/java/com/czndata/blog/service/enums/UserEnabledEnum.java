package com.czndata.blog.service.enums;

import lombok.Getter;

@Getter
public enum UserEnabledEnum {
    DISENABLED(0, "未启用"),
    ENABLED(1, "已启用")
    ;

    private Integer code;
    private String desc;

    UserEnabledEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
