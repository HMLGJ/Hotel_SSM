package com.hotel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.dao.FloorDao;
import com.hotel.entity.Floor;
import com.hotel.service.FloorService;
import com.hotel.vo.FloorVo;

@Service
@Transactional
public class FloorServiceImpl implements FloorService{
	
	@Resource
	private FloorDao floorDao;
	
	@Override
	public List<Floor> findFloorList(FloorVo floorVo) {
		return floorDao.findFloorList(floorVo);
	}

	@Override
	public int addFloor(Floor floor) {
		return floorDao.addFloor(floor);
	}

	@Override
	public int updateFloor(Floor floor) {
		return floorDao.updateFloor(floor);
	}

	@Override
	public int deleteById(Integer floor_id) {
		return floorDao.deleteById(floor_id);
	}

}
