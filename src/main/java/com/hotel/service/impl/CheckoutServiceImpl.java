package com.hotel.service.impl;

import com.hotel.dao.CheckinDao;
import com.hotel.dao.CheckoutDao;
import com.hotel.dao.OrdersDao;
import com.hotel.dao.RoomDao;
import com.hotel.dao.RoomTypeDao;
import com.hotel.entity.Checkin;
import com.hotel.entity.Checkout;
import com.hotel.entity.Orders;
import com.hotel.entity.Room;
import com.hotel.entity.RoomType;
import com.hotel.service.CheckoutService;
import com.hotel.utils.UUIDUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import javax.annotation.Resource;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {

	@Resource
    private CheckoutDao checkoutDao;

	@Resource
    private CheckinDao checkinDao;

	@Resource
    private OrdersDao ordersDao;

	@Resource
    private RoomTypeDao roomTypeDao;

	@Resource
    private RoomDao roomDao;

	//添加退房记录
	@Override
	public int addCheckout(Checkout checkout) {
		//#1.新增一条退房记录
        checkout.setCheckOut_createDate(new Date());//创建时间（什么时候操作了办理退房）
        checkout.setCheckOut_number(UUIDUtils.randomUUID());//随机生成订单号
        //调用新增退房记录的方法
        int count = checkoutDao.addCheckout(checkout);
        //System.out.println(count);
        if(count>0){

            //#2.修改t_checkin中status状态，修改成2(已离店)
            Checkin checkin = checkinDao.findById(checkout.getCheckOut_checkInId());
            
            checkin.setCheckin_status(2);//已离店
            //调用修改入住订单的方法
            checkinDao.updateCheckin(checkin);

            //#3.修改t_orders表中的status状态，修改成4(订单已完成)
            Orders orders = new Orders();
            orders.setOrders_status(4);//订单已完成
            orders.setOrders_id(checkin.getCheckin_ordersid());
            //调用修改订单的方法
            ordersDao.updateOrders(orders);

            //#4.修改t_room_type表中的可用房间数(+1),已入住房间数-1
            RoomType roomType = roomTypeDao.findById(checkin.getCheckin_roomtypeid());
            roomType.setRoomType_avilablenum(roomType.getRoomType_avilablenum()+1);//可用房间数+1
            roomType.setRoomType_livednum(roomType.getRoomType_livednum()-1);//已入住房间数-1
            //调用修改房型的方法
            roomTypeDao.updateRoomType(roomType);
             //注意：退房对象Checkout中无法获取订单主键ID，也无法获取房型主键ID
            
            //修改房间状态(修改成可预订的状态)
            Room room = new Room();
            room.setRoom_id(checkin.getCheckin_roomid().intValue());//房间编号
            room.setRoom_status(3);//可预订状态
            roomDao.updateRoom(room);
            

        }
        return count;
	}

    

}
