package com.cheng._006_mybatisx.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.cheng._006_mybatisx.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author cheng
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2022-09-08 21:19:06
* @Entity com.cheng._006_mybatisx.pojo.User
*/
public interface UserMapper extends BaseMapper<User> {

    int insertSelective(User user);

    int deleteByUidAndUserName(@Param("uid") Long uid, @Param("userName") String userName);

    int updateAgeAndUserNameByUid(@Param("age") Integer age, @Param("userName") String userName, @Param("uid") Long uid);

    List<User> selectAgeAndSexByAgeBetween(@Param("beginAge") Integer beginAge, @Param("endAge") Integer endAge);
}




