package com.cheng.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheng.mybatisplus.mapper.ProductMapper;
import com.cheng.mybatisplus.mapper.UserMapper;
import com.cheng.mybatisplus.pojo.Product;
import com.cheng.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusWapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void test01(){
        //查询的是：用户名包含a,年龄为20-30，邮箱不为null
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name","a")
                    .between("age",20,30)
                    .isNotNull("email");
        // SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user
        // WHERE is_deleted=0 AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    @Test
    public void  test2(){
        //按照年龄的降序排序，如年龄相同，则按照id升序排序
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age DESC,uid ASC
        queryWrapper.orderByDesc("age").orderByAsc("uid");

        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    @Test
    public void  test3(){
//        UPDATE t_user SET is_deleted=1 WHERE is_deleted=0 AND (email IS NULL)
        //删除email为空的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int delete = userMapper.delete(queryWrapper);
        System.out.println(delete);

    }

    @Test
    public void test4(){
        //将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        QueryWrapper<User> aNull = queryWrapper.gt("age", 20)
                                                .like("user_name", "a")
                                                .or().isNull("email");
        User user = new User();
        user.setName("xiaoming");
        user.setEmail("xiaolong@Test.com");
        //UPDATE t_user SET user_name=?, email=? WHERE is_deleted=0 AND (age > ? AND user_name LIKE ? OR email IS NULL)
        int update = userMapper.update(user, aNull);
        System.out.println(update);
    }

    @Test
    public void test5(){
        ////将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.like("user_name","a").and(age -> age.gt("age",22).or().isNull("email"));

        User user = new User();
        user.setAge(200);
        //UPDATE t_user SET age=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        int update = userMapper.update(user, queryWrapper);
        System.out.println(update);

    }

    @Test
    public void test6(){
//        查询用户信息的username和age字段
        //SELECT user_name,age FROM t_user WHERE is_deleted=0
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_name","age");
        List<Map<String, Object>> mapList = userMapper.selectMaps(queryWrapper);
        mapList.forEach(System.out::println);
    }

    @Test
    public void test7(){
        //查询id小于等于3的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper();
       /* queryWrapper.select("uid",
                           "user_name",
                           "age",
                            "email",
                            "is_deleted"
        ).in("uid", Arrays.asList(1L,2L,3L));*/
        queryWrapper.inSql("uid","select uid from t_user where uid <= 3");
        //SELECT uid,user_name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (uid IN (?,?,?))
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0
        // AND (uid IN (select uid from t_user where uid <= 3))
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);

    }

    @Test
    public void  test8(){

        //将（年龄大于20或邮箱为null）并且用户名中包含有a的用户信息修改
        //组装set子句以及修改条件
        // UPDATE t_user SET email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
//        UpdateWrapper<User> wapper = new UpdateWrapper<>();
//        wapper.like("user_name","a").and(w -> w.gt("age",20).or().isNull("email"));
//        User user = new User();
//        user.setEmail("cheng@qq.com");
//        int update = userMapper.update(user, wapper);
//        System.out.println("影响的记录条数"+update);

        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("email","bo@qq.com").like("user_name","a").and(w -> w.gt("age",20).or().isNull("email"));
        int update = userMapper.update(null, wrapper);
        System.out.println(update);


    }



    @Test
    public void testProduct(){

        //小李查询商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李查询的"+productLi.getPrice());

        //小王查询商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小王查询的"+productWang.getPrice());

        //小李加50
        productLi.setPrice(productLi.getPrice()+50);
        productMapper.updateById(productLi);

        //小王减去30
        productWang.setPrice(productWang.getPrice()-30);
        int result = productMapper.updateById(productWang);
        if (result == 0){
            //操作失败
            Product productNew = productMapper.selectById(1);

            productNew.setPrice(productNew.getPrice() - 30);

            productMapper.updateById(productNew);
        }

        //老板查询
        Product productBoss = productMapper.selectById(1);
        System.out.println("老板查询的"+productBoss.getPrice());
    }

    @Test
    public void test9(){
        //定义查询条件，有可能为null（用户未输入或未选择）
        String username = null;
        Integer ageBegin = 25;
        Integer ageEnd = 30;
//SELECT uid AS id,user_name AS name,age,email,sex,is_deleted FROM t_user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            queryWrapper.like("user_name","a");
        }
        if (ageBegin != null){
            queryWrapper.ge("age",ageBegin);
        }
        if (ageEnd != null){
            queryWrapper.le("age",ageEnd);
        }
        userMapper.selectList(queryWrapper).forEach(System.out::println);

    }

    @Test
    public void test10(){
        //定义查询条件，有可能为null（用户未输入或未选择）
        String username = null;
        Integer ageBegin = 25;
        Integer ageEnd = 30;
//SELECT uid AS id,user_name AS name,age,email,sex,is_deleted FROM t_user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),"user_name","a")
                        .ge(ageBegin !=null ,"age",ageBegin)
                                .le(ageEnd !=null ,"age",ageEnd);
        userMapper.selectList(queryWrapper).forEach(System.out::println);

    }

    @Test
    public void test11(){
        //定义查询条件，有可能为null（用户未输入或未选择）
        String username = "a";
        Integer ageBegin = 25;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),User::getName,"a")
                .ge(ageBegin !=null ,User::getAge,ageBegin)
                .le(ageEnd !=null ,User::getAge,ageEnd);
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    @Test
    public void test12() {
//组装set子句
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(User::getAge, 18)
                .set(User::getEmail, "user@atguigu.com")
                .like(User::getName, "a")
                .and(i -> i.lt(User::getAge, 24).or().isNull(User::getEmail)); //lambda
        //表达式内的逻辑优先运算
        User user = new User();
        int result = userMapper.update(user, updateWrapper);
        System.out.println("受影响的行数：" + result);
    }

    @Test
    public void testPaget(){
    //SELECT uid AS id,user_name AS name,age,email,sex,is_deleted FROM t_user WHERE is_deleted=0 LIMIT ?
        Page<User> page = new Page<>(1,3);
        userMapper.selectPage(page,null);

        System.out.println("当前页："+page.getCurrent());
        System.out.println("每页显示的条数："+page.getSize());
        System.out.println("总记录数："+page.getTotal());
        System.out.println("总页数："+page.getPages());
        System.out.println("是否有上一页："+page.hasPrevious());
        System.out.println("是否有下一页："+page.hasNext());

    }

    @Test
    public void testPage1(){

        Page<User> page = new Page<>(1,3);
        userMapper.selectPageVo(page,20);

        System.out.println("当前页："+page.getCurrent());
        System.out.println("每页显示的条数："+page.getSize());
        System.out.println("总记录数："+page.getTotal());
        System.out.println("总页数："+page.getPages());
        System.out.println("是否有上一页："+page.hasPrevious());
        System.out.println("是否有下一页："+page.hasNext());

    }



}
