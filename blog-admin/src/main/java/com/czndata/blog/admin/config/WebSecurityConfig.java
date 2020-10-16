package com.czndata.blog.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.
                cors().and()
                // 登录行为由自己实现，参考 AuthController#login
                .formLogin()
                .loginPage("/login")  // 登录跳转 URL
                .loginProcessingUrl("/login") // 处理表单登录 URL,此处对应前端action="login"
//                .successHandler(loginSuccessHandler) // 登陆成功处理逻辑
//                .failureHandler(loginFailureHandler) // 登录失败处理逻辑
                .and()
                .httpBasic().disable() // 关闭http basic认证
                .csrf().disable() // 关闭csrf token
                // 认证请求
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                // 所有请求都需要登录访问
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement() // 添加Session管理器
        ;
    }
}
