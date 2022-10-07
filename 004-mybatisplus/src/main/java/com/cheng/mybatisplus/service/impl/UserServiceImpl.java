package com.cheng.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheng.mybatisplus.mapper.UserMapper;
import com.cheng.mybatisplus.pojo.User;
import com.cheng.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
