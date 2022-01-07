package com.hotel.service;

import java.util.List;

import com.hotel.entity.Floor;
import com.hotel.vo.FloorVo;

public interface FloorService {
	
	//查询楼层列表
	List<Floor> findFloorList(FloorVo floorVo);
	
	//添加楼层
    int addFloor(Floor floor);

    // 修改楼层
    int updateFloor(Floor floor);
    
    //删除楼层
    int deleteById(Integer floor_id);
}
