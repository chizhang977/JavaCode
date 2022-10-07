package com.cheng._005mybatis_plus_datasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheng._005mybatis_plus_datasource.mapper.ProductMapper;
import com.cheng._005mybatis_plus_datasource.pojo.Product;
import com.cheng._005mybatis_plus_datasource.service.ProductService;
import org.springframework.stereotype.Service;


@Service
@DS("slave_1")
public class ProductServicedImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
