package com.hotel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.dao.RoomDao;
import com.hotel.entity.Room;
import com.hotel.service.RoomService;
import com.hotel.vo.RoomVo;

@Service
@Transactional
public class RoomServiceImpl implements RoomService{
	
	@Resource
	private RoomDao roomDao;

	@Override
	public List<Room> findRoomListByPage(RoomVo roomVo) {
		return roomDao.findRoomListByPage(roomVo);
	}

	@Override
	public int addRoom(Room room) {
		return roomDao.addRoom(room);
	}

	@Override
	public int updateRoom(Room room) {
		return roomDao.updateRoom(room);
	}

	@Override
	public int deleteById(int room_id) {
		return roomDao.deleteById(room_id);
	}

	
	@Override
	public List<Room> findRoomListByFloorId() {
		return roomDao.findRoomListByFloorId();
	}

	@Override
	public Room findById(Integer room_id) {
		return roomDao.findById(room_id);
	}

	
}
