package com.cheng.mybatisplus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheng.mybatisplus.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public interface UserMapper  extends BaseMapper<User> {

    /**
     * 根据id查询用户信息为map集合
     * @param id
     * @return
     */
    @MapKey(value = "id")
    Map<String ,Object> selectMapById( Long id);

    Page<User> selectPageVo( @Param("page") Page<User> page,@Param("age") Integer age);
}
