package com.cheng.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.cheng.mybatisplus.mapper.ProductMapper;
import com.cheng.mybatisplus.mapper.UserMapper;
import com.cheng.mybatisplus.pojo.Product;
import com.cheng.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class FastAutoGeneratorTest {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/mybatis_plus?characterEncoding=utf-8&userSSL=false", "root", "root")
                        .globalConfig(builder -> {
                            builder.author("cheng") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                                    .fileOverride() // 覆盖已生成文件
                                    .outputDir("D://mybatis_plus"); // 指定输出目录
                        })
                        .packageConfig(builder -> {
                            builder.parent("com.cheng") // 设置父包名
                                    .moduleName("mybatisplus") // 设置父包模块名
                                    .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://mybatis_plus"));
                        // 设置mapperXml生成路径
                        })
                        .strategyConfig(builder -> {
                            builder.addInclude("t_user") // 设置需要生成的表名
                                    .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                        })
                        .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker
                //引擎模板，默认的是Velocity引擎模板
                        .execute();
    }

}

