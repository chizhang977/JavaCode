package com.cheng.securitydemo1.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheng.securitydemo1.entity.Users;
import com.cheng.securitydemo1.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询数据库
        QueryWrapper<Users> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        Users users = usersMapper.selectOne(queryWrapper);

        if (users == null){//查询失败
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> list  = AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");
        return new User(users.getUsername(),new BCryptPasswordEncoder().encode(users.getPassword()),list);
    }
}
