package com.cheng.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheng.mybatisplus.pojo.User;
import com.cheng.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
public class MybatisplusServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void  testGetCount(){
        long count = userService.count();
        System.out.println(count);
    }

    @Test
    public void testBatInsert(){
        List<User> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("cheng"+i);
            user.setAge(20+i);
            user.setEmail("null");
            list.add(user);
        }
        boolean saveBatch = userService.saveBatch(list);//批量添加
        System.out.println(saveBatch);
    }




}
