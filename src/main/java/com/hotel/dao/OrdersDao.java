package com.hotel.dao;

import java.util.List;

import com.hotel.entity.Orders;
import com.hotel.vo.OrdersVo;

public interface OrdersDao {
	
	//添加订单
    int addOrders(Orders orders);

    //查询订单列表
    List<Orders> findOrdersList(OrdersVo ordersVo);

    //修改订单
    int updateOrders(Orders orders);
}
