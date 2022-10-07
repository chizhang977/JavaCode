package com.cheng.mybatisplus;


import com.cheng.mybatisplus.enums.SexEnum;
import com.cheng.mybatisplus.mapper.UserMapper;
import com.cheng.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisPlusEnumTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void  test(){
        User user = new User();
        user.setName("admin");
        user.setAge(33);
        user.setSex(SexEnum.MALE);
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

}
