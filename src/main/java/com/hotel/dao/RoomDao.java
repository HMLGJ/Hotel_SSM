package com.hotel.dao;

import java.util.List;

import com.hotel.entity.Room;
import com.hotel.vo.RoomVo;

public interface RoomDao {
	
	//查询房间列表
    List<Room> findRoomListByPage(RoomVo roomVo);

    //添加房间
    int addRoom(Room room);
    
    //修改房间
    int updateRoom(Room room);

    //删除房间
    int deleteById(int room_id);

    //根据楼层查询房间列表(@Param(room_floorid)Integer room_floorid,@Param(size)Integer size)
    List<Room> findRoomListByFloorId();

    //查看房间详情
    Room findById(Integer room_id);
}
