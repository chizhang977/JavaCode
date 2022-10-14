package com.cheng.springcloud.dao;

import com.cheng.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    /**
     * 创建订单
     * @param order
     */
    void createOrder(Order order);


    /**
     * 修改订单状态
     * @param userId  用户id
     * @param status  0 未支付  1 已完成支付
     */
    void updateOrderStatus(@Param("userId")Long userId,@Param("status")Integer status);
}
