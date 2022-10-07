package com.cheng._005mybatis_plus_datasource.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("t_user")
public class User {
    @TableId
    private Integer uid;
    private String userName;
    private Integer age;
    private Integer sex;
    private String email;
    private Integer isDeleted;
}
