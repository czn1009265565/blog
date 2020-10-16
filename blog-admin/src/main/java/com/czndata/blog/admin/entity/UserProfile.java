package com.czndata.blog.admin.entity;


import com.czndata.blog.mbg.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class UserProfile implements UserDetails  {
    private Integer id;

    private String username;

    private String password;

    private Integer enabled;

    private String roles;

    private Date createTime;

    private Date updateTime;

    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities == null) {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled.equals(1);
    }

    @Override
    public boolean equals(Object object){
        if (object instanceof User){
            String username_ = ((User) object).getUsername();
            return username.equals(username_);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
