package com.cheng.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        String config = "mybatis-config.xml";
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream(config);
            sqlSessionFactory  = new SqlSessionFactoryBuilder().build(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static SqlSession getSqlSession(){
         return sqlSessionFactory.openSession();
    }
}
