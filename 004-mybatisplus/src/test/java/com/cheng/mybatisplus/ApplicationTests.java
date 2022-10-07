package com.cheng.mybatisplus;

import com.cheng.mybatisplus.mapper.UserMapper;
import com.cheng.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private UserMapper userMapper;//@Repository

    @Test
    void testSelectList() {
        userMapper.selectList(null)
                .stream().filter(user -> user.getAge() > 25)
                .forEach(x -> System.out.println(x));
    }

    @Test
    public void testInsert(){
        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? );
        User user = new User();
        user.setAge(22);
        user.setEmail("jee@qq.com");
        user.setName("jee");
        userMapper.insert(user);
        System.out.println(user.getId());
    }

    /**
     * 删除
     */
    @Test
    public void testDelete(){
       // DELETE FROM user WHERE id=?
     //   int result = userMapper.deleteById(1567358550374645761L);
     //   System.out.println(result);

        // DELETE FROM user WHERE name = ? AND age = ?
        /*Map<String,Object> map = new HashMap<>();
        map.put("name","cheng");
        map.put("age",23);
        int result1 = userMapper.deleteByMap(map);//map中的条件
        System.out.println(result1);*/

        //DELETE FROM user WHERE id IN ( ? , ? , ? )
        List<Long> longs = Arrays.asList(1L, 2L, 3L);
        int result2 = userMapper.deleteBatchIds(longs);//通过多个id批量删除

    }

    @Test
    public void testUpdate(){
       /* User user = new User();
        user.setId(4L);
        user.setAge(22);
        user.setName("James");
        user.setEmail("James@qq.com");
        //UPDATE user SET name=?, age=?, email=? WHERE id=?
        int result = userMapper.updateById(user);
        System.out.println(result);*/

    }

    @Test
    public void testSelect(){
        // SELECT id,name,age,email FROM user WHERE id=?
       /* User user = userMapper.selectById(1L);
        System.out.println(user);*/

        // SELECT id,name,age,email FROM user WHERE id IN ( ? , ? )
       /* List<Long> longs = Arrays.asList(1L, 2L);
        userMapper.selectBatchIds(longs).forEach(System.out::println);*/

        /*Map<String,Object> map = new HashMap<>();
        map.put("name","Tom");
        map.put("age",28);
        //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        List<User> users = userMapper.selectByMap(map);//map集合条件查询
        System.out.println(users);*/

       userMapper.selectList(null).forEach(System.out::println);

      /*  Map<String, Object> stringObjectMap = userMapper.selectMapById(1L);
        System.out.println(stringObjectMap);*/
    }

}
