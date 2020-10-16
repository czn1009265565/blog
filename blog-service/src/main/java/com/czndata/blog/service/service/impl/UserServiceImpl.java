package com.czndata.blog.service.service.impl;

import com.czndata.blog.mbg.dao.UserMapper;
import com.czndata.blog.mbg.entity.User;
import com.czndata.blog.mbg.entity.UserExample;
import com.czndata.blog.service.constant.BlogConstant;
import com.czndata.blog.service.dto.user.UserDto;
import com.czndata.blog.service.dto.user.UserParam;
import com.czndata.blog.service.enums.ResultEnum;
import com.czndata.blog.service.enums.UserEnabledEnum;
import com.czndata.blog.service.exception.BlogException;
import com.czndata.blog.service.service.UserService;
import com.czndata.blog.service.utils.MarkdownUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.security.util.Password;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto detail(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null || UserEnabledEnum.DISENABLED.getCode().equals(user.getEnabled())){
            throw new BlogException(ResultEnum.NOT_EXIST_USER);
        }

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);

        String introduction = userDto.getIntroduction();
        if (!StringUtils.isEmpty(introduction)){
            userDto.setIntroduction(MarkdownUtils.markdown2Html(introduction));
        }

        return userDto;
    }

    @Override
    public int create(UserParam userParam) {
        // 如果用户存在则不创建
        String username = userParam.getUsername();
        if (selectByUsername(username) != null){
            return 0;
        }

        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        return userMapper.insertSelective(user);
    }

    @Override
    public User selectByUsername(String username) {
        if (StringUtils.isEmpty(username)){
            throw new BlogException(ResultEnum.USERNAME_IS_EMPTY);
        }

        UserExample userExample = new UserExample();
        userExample.or().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.size() == BlogConstant.EMPTY){
            return null;
        }
        return userList.get(0);
    }
}
