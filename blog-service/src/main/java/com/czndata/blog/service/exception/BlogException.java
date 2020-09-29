package com.czndata.blog.service.exception;

import com.czndata.blog.service.enums.ResultEnum;
import lombok.Getter;

@Getter
public class BlogException extends RuntimeException{
    private Integer code;
    private String desc;

    public BlogException(Integer code, String desc) {
        super(desc);
        this.code = code;
        this.desc = desc;
    }

    public BlogException(ResultEnum resultEnum) {
        super(resultEnum.getDesc());
        this.code = resultEnum.getCode();
        this.desc = resultEnum.getDesc();
    }

}
