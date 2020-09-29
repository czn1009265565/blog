package com.czndata.blog.service.handler;

import com.czndata.blog.service.enums.ResultEnum;
import com.czndata.blog.service.exception.BlogException;
import com.czndata.blog.service.vo.ResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BlogException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseVO handleUserNotExistsException(BlogException e) {
        return ResponseVO.failed(e.getCode(), e.getDesc());
    }

    // 通用@valid表单参数异常处理
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseVO notValidExceptionHandle(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Objects.requireNonNull(bindingResult.getFieldError()); // 空指针

        Integer code = ResultEnum.PARAM_ERROR.getCode();
        String message = bindingResult.getFieldError().getField() + " " + bindingResult.getFieldError().getDefaultMessage();
        return ResponseVO.failed(code, message);
    }
}
