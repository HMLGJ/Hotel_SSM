package com.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hotel.entity.HotelAdmin;
import com.hotel.vo.HotelAdminVo;

public interface HotelAdminDao {
	//根据酒店管理名字查询信息
	HotelAdmin findHotelAdmin_name(@Param("hotelAdmin_name") String hotelAdmin_name);
	
	//根据部门编号查询员工（酒店管理员）数量
	int getHotelAdminCountByDeptId(Integer hotelAdmin_deptId);
	
	//根据角色编号查询员工（酒店管理员）数量
	int gerHotelAdminCountByRoleId(Integer hotelAdmin_roleId);
	
	//查询员工列表<HotelAdmin>(泛型)
	List<HotelAdmin> findHotelAdminList(HotelAdminVo hotelAdminVo);
	
	//添加员工
	int addHotelAdmin(HotelAdmin hotelAdmin);
	
	//修改员工
	int updateHotelAdmin(HotelAdmin hotelAdmin);
	
	//删除员工
	int deleteById(Integer hotelAdmin_id);
	
	//删除管理员角色关系的数据
	void deleteHotelAdminAndRloe(Integer hotelAdmin_id);
	
	//完善功能，根据部门id查询部门名称(测试)
	HotelAdmin ceshi(HotelAdmin hotelAdmin);
	
}
