package com.hotel.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.hotel.entity.Role;
import com.hotel.vo.RoleVo;

public interface RoleDao {
	//查询角色列表
	List<Role>findRolerList(RoleVo roleVo);
	
	//添加角色
    int addRole(Role role);

    //修改角色
    int updateRole(Role role);

    //删除角色
    int deleteById(Integer role_id);
    
    //删除原有关系(如果不删除原有关系，那会添加重复的数据)
    @Delete("delete from sys_role_menu where role_id=#{role_id}")
	void deleteRoleMenu(Integer role_id);
    
    //添加角色菜单关系数据
    @Insert("insert into sys_role_menu(menu_id,role_id) values(#{menu_id},#{role_id})")
    //Mybatis,如果不加@Param无法读取数据
	void addRoleMenu(@Param("role_id")Integer role_id, @Param("menu_id")String menu_id);
    
    //查询所有角色列表
    //因为多一个复选框属性，不能再用泛型，role没有这个复选框这个属性，所以用map封装
    List<Map<String,Object>> findRoleListByMap();
    
    //根据管理员id查询管理员拥有的角色列表(未实现)
  	List<Integer> findHotelAdminRoleByHotelAdminId(int hotelAdmin_id);
}
