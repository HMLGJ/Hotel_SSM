package com.hotel.service;

import java.util.List;

import com.hotel.entity.RoomType;
import com.hotel.vo.RoomTypeVo;

public interface RoomTypeService {
	
	//查询列表
	List<RoomType> findRoomTypeList(RoomTypeVo roomTypeVo);
	
	//添加房型
    int addRoomType(RoomType roomType);
    
    //修改房型
    int updateRoomType(RoomType roomType);

    //根据房型ID查询房型信息
    RoomType findById(Integer roomtypeid);

    //删除房型
    int deleteById(int roomType_id);
}
