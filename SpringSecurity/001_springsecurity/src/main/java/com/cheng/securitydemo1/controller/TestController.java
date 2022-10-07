package com.cheng.securitydemo1.controller;


import com.cheng.securitydemo1.entity.Users;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("hello")
    public String add(){
        return "hello spring security";
    }

    @GetMapping("index")
    public String index(){
        return "hello index";
    }



   // @Secured({"ROLE_sale","ROLE_manager"})
   //@PreAuthorize("hasAnyAuthority('admins')")
    @PostAuthorize("hasAnyAuthority('admins')")
    @GetMapping("update")
    public String update(){
        System.out.println("update...");
        return "hello udpate";
    }


    @RequestMapping("getAll")
    @PreAuthorize("hasAnyAuthority('admins')")//方法执行前开始权限的认证
    @PostFilter("filterObject.username.equals('admin1')")//对数据的返回进行过滤
    public List<Users> getAllUser(){
        ArrayList<Users> list = new ArrayList<>();
        list.add(new Users(1,"admin1","6666"));
        list.add(new Users(2,"admin2","888"));
        System.out.println(list);
        return list;
    }

}
