package com.hotel.service;

import java.util.List;
import java.util.Map;

import com.hotel.entity.Role;
import com.hotel.vo.RoleVo;

public interface RoleService {
	//查询角色列表
	List<Role>findRolerList(RoleVo roleVo);
		
	//添加角色
	int addRole(Role role);

	//修改角色
	int updateRole(Role role);

	//删除角色
	int deleteById(Integer role_id);
	
	//分配菜单
	int saveRoleMenu(String ids, Integer role_id);
	
	//查询所有角色列表
    //因为多一个复选框属性，不能再用泛型，role没有这个复选框这个属性，所以用map封装
    List<Map<String,Object>> findRoleListByMap();
    
    //根据管理员id查询管理员拥有的角色列表(未实现)
	List<Integer> findHotelAdminRoleByHotelAdminId(int hotelAdmin_id);
}
