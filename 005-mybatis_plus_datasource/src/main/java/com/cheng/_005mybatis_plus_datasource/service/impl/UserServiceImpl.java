package com.cheng._005mybatis_plus_datasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheng._005mybatis_plus_datasource.mapper.UserMapper;
import com.cheng._005mybatis_plus_datasource.pojo.User;
import com.cheng._005mybatis_plus_datasource.service.UserService;
import org.springframework.stereotype.Service;

@Service
@DS("master")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
