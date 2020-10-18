package com.czndata.blog.service.vo;

import com.czndata.blog.service.enums.ResultEnum;
import lombok.Data;

@Data
public class ResponseVO<T> {
    // 错误码
    private Integer code;
    // 提示信息
    private String message;
    // 具体内容
    private T data;

    public ResponseVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseVO<String> failed(ResultEnum resultEnum){
        return new ResponseVO<>(resultEnum.getCode(), resultEnum.getDesc());
    }

    public static ResponseVO<String> failed(Integer code, String message){
        return new ResponseVO<>(code, message);
    }

    public static <T>ResponseVO<T> success(T data){
        return new ResponseVO<>(ResultEnum.SUCCESS.getCode(), "success", data);
    }
}
