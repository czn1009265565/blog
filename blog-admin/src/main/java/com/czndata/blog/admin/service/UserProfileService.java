package com.czndata.blog.admin.service;

import com.czndata.blog.admin.entity.UserProfile;
import com.czndata.blog.mbg.dao.UserMapper;
import com.czndata.blog.mbg.entity.User;
import com.czndata.blog.mbg.entity.UserExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class UserProfileService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)){
            throw new UsernameNotFoundException("用户不存在");
        }

        UserExample userExample = new UserExample();
        userExample.or().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList.size() != 1){
            throw new UsernameNotFoundException("用户不存在");
        }

        UserProfile userProfile = new UserProfile();
        User user = userList.get(0);
        BeanUtils.copyProperties(user, userProfile);
        return userProfile;
    }
}
