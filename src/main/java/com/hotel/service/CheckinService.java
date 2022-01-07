package com.hotel.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.hotel.entity.Checkin;
import com.hotel.vo.CheckinVo;

public interface CheckinService {
	
	//查询入住列表
    List<Checkin> findCheckinList(CheckinVo checkinVo);

    // 添加入住信息
    int addCheckin(Checkin checkin);


    //修改入住信息的方法
    int updateCheckin(Checkin checkin);

}
