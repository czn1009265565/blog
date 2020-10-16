package com.czndata.blog.service.service;

import com.czndata.blog.mbg.entity.User;
import com.czndata.blog.service.dto.user.UserDto;
import com.czndata.blog.service.dto.user.UserParam;

public interface UserService {

    UserDto detail(Integer userId);

    int create(UserParam userParam);

    User selectByUsername(String username);
}
