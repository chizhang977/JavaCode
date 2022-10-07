package com.cheng._006_mybatisx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheng._006_mybatisx.pojo.User;
import com.cheng._006_mybatisx.service.UserService;
import com.cheng._006_mybatisx.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author cheng
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-09-08 21:19:06
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




