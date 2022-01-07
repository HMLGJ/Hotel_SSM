package com.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.hotel.entity.Checkin;
import com.hotel.vo.CheckinVo;

public interface CheckinDao {
	
	//查询入住列表
    List<Checkin> findCheckinList(CheckinVo checkinVo);

    // 添加入住信息
    int addCheckin(Checkin checkin);

    //根据主键查询入住信息
    @Select("select * from t_checkin where checkin_id = #{checkin_id}")
    Checkin findById(Long checkin_id);

    //修改入住信息的方法
    int updateCheckin(Checkin checkin);

}
