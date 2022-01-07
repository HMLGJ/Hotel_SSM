package com.hotel.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.entity.RoomType;
import com.hotel.dao.CheckinDao;
import com.hotel.dao.OrdersDao;
import com.hotel.dao.RoomTypeDao;
import com.hotel.entity.Checkin;
import com.hotel.entity.Orders;
import com.hotel.service.CheckinService;
import com.hotel.vo.CheckinVo;

@Service
@Transactional
public class CheckinServiceImpl implements CheckinService{
	
	@Resource
	private CheckinDao checkinDao;
	
	@Resource
    private OrdersDao ordersDao;

	@Resource
    private RoomTypeDao roomTypeDao;
	
	@Override
	public List<Checkin> findCheckinList(CheckinVo checkinVo) {
		return checkinDao.findCheckinList(checkinVo);
	}

	@Override
	public int addCheckin(Checkin checkin) {
		//入住状态
		checkin.setCheckin_status(1);//1-已入住
        checkin.setCheckin_createdate(new Date());//创建时间
		//添加入住信息
		int count = checkinDao.addCheckin(checkin);
		if(count>0) {
		//修改预订订单的状态,修改状态为3（已入住）
		Orders orders = new Orders();
		orders.setOrders_id(checkin.getCheckin_ordersid());//预订订单ID主键
		orders.setOrders_status(2);//已入住
		//修改房型表中的已入住数量
		ordersDao.updateOrders(orders);
		//修改房型表的已入住数量
		//根据id查询原有的房型信息，要先查信息再+1，不然没数据会写死成1
		RoomType roomType = roomTypeDao.findById(checkin.getCheckin_roomtypeid());
        roomType.setRoomType_livednum(roomType.getRoomType_livednum()+1);
        //调用修改房型的方法
        roomTypeDao.updateRoomType(roomType);
		}
		return count;
	}


	@Override
	public int updateCheckin(Checkin checkin) {
		return checkinDao.updateCheckin(checkin);
	}
}
