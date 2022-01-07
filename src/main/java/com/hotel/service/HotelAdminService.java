package com.hotel.service;

import java.util.List;

import com.hotel.entity.HotelAdmin;
import com.hotel.vo.HotelAdminVo;

public interface HotelAdminService {
	//根据酒店管理名字查询信息
	HotelAdmin login(String hotelAdmin_name,String hotelAdmin_password);
	
	//根据部门编号查询员工（酒店管理员）数量
	int getHotelAdminCountByDeptId(Integer hotelAdmin_deptId);
	
	//根据角色编号查询员工（酒店管理员）数量
	int gerHotelAdminCountByRoleId(Integer hotelAdmin_roleId);
	
	//查询员工列表
	List<HotelAdmin> findHotelAdminList(HotelAdminVo hotelAdminVo);
	
	//添加员工
	int addHotelAdmin(HotelAdmin hotelAdmin);
	
	//修改员工
	int updateHotelAdmin(HotelAdmin hotelAdmin);
	
	//删除员工
	int deleteById(Integer hotelAdmin_id);
	
	//重置密码
	int resetPwd(int hotelAdmin_id);
	
	//测试
	HotelAdmin ceshi(HotelAdmin hotelAdmin);
}
