package com.czndata.blog.admin.service;

import com.czndata.blog.mbg.dao.UserMapper;
import com.czndata.blog.mbg.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootTest
public class UserProfileServiceTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void createAdmin(){
        User user = new User();
        user.setUsername("陈泽南");
        user.setPassword(passwordEncoder.encode("root"));
        user.setEnabled(1);
        user.setRoles("ROLE_ADMIN");
        userMapper.insertSelective(user);
//        System.out.println(passwordEncoder.encode("root"));
    }
}