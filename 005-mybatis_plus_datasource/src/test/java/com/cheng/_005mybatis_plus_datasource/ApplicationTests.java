package com.cheng._005mybatis_plus_datasource;

import com.cheng._005mybatis_plus_datasource.service.ProductService;
import com.cheng._005mybatis_plus_datasource.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private  UserService userService;


    @Test
    void contextLoads() {
        System.out.println(userService.getById(1));
        System.out.println(productService.getById(1));

    }

}
