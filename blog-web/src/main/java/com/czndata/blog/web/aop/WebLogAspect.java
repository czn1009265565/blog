package com.czndata.blog.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Pointcut("execution(public * com.czndata.blog.web.controller.*.*(..))")
    public void pointCut(){};

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        String url = request.getRequestURI();
        String ip = request.getRemoteAddr();

        // 获取经过Nginx代理后的真实用户ip
        String forwarded = request.getHeader("X-Forwarded-For");
        String real = request.getHeader("X-Real-IP");
        if (checkIp(forwarded)){
            ip = forwarded;
        }else if(checkIp(real)){
            ip = real;
        }

        // 请求的方法参数
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);

        StringBuilder params = new StringBuilder();
        if (args != null && paramNames != null) {
            for (int i = 0; i < args.length; i++) {
                params.append("  ").append(paramNames[i]).append(": ").append(args[i]);
            }
        }
        log.info("Url:{} IP:{} args:{}", url, ip, params.toString());
    }

    public boolean checkIp(String ip){
        return !(StringUtils.isEmpty(ip) || "unknown".equals(ip));
    }

}
