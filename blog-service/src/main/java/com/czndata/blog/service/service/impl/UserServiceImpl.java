package com.czndata.blog.service.service.impl;

import com.czndata.blog.mbg.dao.UserMapper;
import com.czndata.blog.mbg.entity.User;
import com.czndata.blog.service.dto.user.UserDto;
import com.czndata.blog.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto detail(Integer userId) {
        UserDto userDto = new UserDto();
        User user = userMapper.selectByPrimaryKey(userId);
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }
}
