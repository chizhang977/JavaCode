package com.cheng.springcloud.dao;

import com.cheng.springcloud.domain.CommonResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 库存dao
 */
@Mapper
public interface StorageDao {

    void decrease(@Param("productId") Long productId,
                          @Param("count") Integer count);
}
