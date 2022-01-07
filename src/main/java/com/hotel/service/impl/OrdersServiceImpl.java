package com.hotel.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.entity.Room;
import com.hotel.entity.RoomType;
import com.hotel.dao.RoomDao;
import com.hotel.dao.RoomTypeDao;
import com.hotel.dao.OrdersDao;
import com.hotel.entity.Orders;
import com.hotel.service.OrdersService;
import com.hotel.utils.UUIDUtils;
import com.hotel.vo.OrdersVo;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService{
	
	@Resource
	private OrdersDao ordersDao;
	@Resource
    private RoomDao roomDao;
	@Resource
    private RoomTypeDao roomTypeDao;
	

	@Override
	//事务回滚，只要触发了运行时的异常，就回滚我们的事务
	//rollbackFor回滚的意思，RuntimeException触发运行时异常
	@Transactional(rollbackFor = RuntimeException.class)
	public int addOrders(Orders orders) {
		orders.setOrders_status(1);//设置状态默认值为1，待确认
		orders.setOrders_no(UUIDUtils.randomUUID());//设置一个订单编号的随机数，类似于淘宝运货编号
		
		orders.setOrders_reservedate(new Date());//预订时间为当前系统时间
        int count = ordersDao.addOrders(orders);
        //判断订单是否添加成功，添加成功操作房间及房型
        if(count>0){
            //修改房间状态为已预订(状态码为1)
            //查询房间信息
            Room room = roomDao.findById(orders.getOrders_roomid());
            room.setRoom_status(1);//修改房间状态为已预订(编号1)
            //调用修改房间信息的方法
            roomDao.updateRoom(room);

            //修改房型(可用房间数-1，已预订数量+1)
            RoomType roomType = roomTypeDao.findById(orders.getOrders_roomtypeid());
            //修改可用房间数量
            roomType.setRoomType_avilablenum(roomType.getRoomType_avilablenum()-1);
            //修改已预订数量
            roomType.setRoomType_reservednum(roomType.getRoomType_reservednum()+1);
            //调用修改房型的方法
            roomTypeDao.updateRoomType(roomType);
        }

        return count;
	}


	@Override
	public List<Orders> findOrdersList(OrdersVo ordersVo) {
		return ordersDao.findOrdersList(ordersVo);
	}


	@Override
	public int updateOrders(Orders orders) {
		return ordersDao.updateOrders(orders);
	}
}
