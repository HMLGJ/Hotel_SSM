package com.hotel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.dao.RoomTypeDao;
import com.hotel.entity.RoomType;
import com.hotel.service.RoomTypeService;
import com.hotel.vo.RoomTypeVo;

@Service
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {
	
	@Resource
	private RoomTypeDao roomTypeDao;
	
	@Override
	public List<RoomType> findRoomTypeList(RoomTypeVo roomTypeVo) {
		return roomTypeDao.findRoomTypeList(roomTypeVo);
	}

	@Override
	public int addRoomType(RoomType roomType) {
		//可用房间数量默认是全部的房间数
		roomType.setRoomType_avilablenum(roomType.getRoomType_roomnum());
		//设置已预订数默认为0(防止空指针)
		roomType.setRoomType_reservednum(0);
		//设置已入住默认为0(防止空指针)
		roomType.setRoomType_livednum(0);
		return roomTypeDao.addRoomType(roomType);
	}

	@Override
	public int updateRoomType(RoomType roomType) {
		//可用房间数量默认是全部的房间数(提高代码健壮性，如果我不小心把默认的可用房间数改成了多于全部的房间，它会变回来)
		roomType.setRoomType_avilablenum(roomType.getRoomType_roomnum());
		//设置已预订数默认为0(防止空指针，提高代码健壮性，如果我不小心把默认数改了，它会变回来)
		roomType.setRoomType_reservednum(0);
		//设置已入住默认为0(防止空指针，提高代码健壮性，如果我不小心把默认数改了，它会变回来)
		roomType.setRoomType_livednum(0);
		return roomTypeDao.updateRoomType(roomType);
	}

	@Override
	public RoomType findById(Integer roomtypeid) {
		return roomTypeDao.findById(roomtypeid);
	}

	@Override
	public int deleteById(int roomType_id) {
		return roomTypeDao.deleteById(roomType_id);
	}

}
