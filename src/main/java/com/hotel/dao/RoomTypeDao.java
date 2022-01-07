package com.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.hotel.entity.RoomType;
import com.hotel.vo.RoomTypeVo;

public interface RoomTypeDao {

	//查询列表
	List<RoomType> findRoomTypeList(RoomTypeVo roomTypeVo);
	
	//添加房型
    int addRoomType(RoomType roomType);
    
    //修改房型
    int updateRoomType(RoomType roomType);

    //根据房型ID查询房型信息
    //@Select("select * from t_room_type where id = #{id}")
    RoomType findById(Integer roomtypeid);

    //删除房型
    int deleteById(int roomType_id);
}
