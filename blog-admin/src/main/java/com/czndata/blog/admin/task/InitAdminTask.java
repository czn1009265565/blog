package com.czndata.blog.admin.task;

import com.czndata.blog.service.dto.user.UserParam;
import com.czndata.blog.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Order(value = 1)
@Component
public class InitAdminTask implements ApplicationRunner {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("初始化Admin账号密码");
        task();
    }

    private void task(){
        UserParam userParam = UserParam.builder()
                .username("root")
                .password(passwordEncoder.encode("root"))
                .roles("ROLE_ADMIN")
                .enabled(1)
                .build();
        userService.create(userParam);
    }
}
