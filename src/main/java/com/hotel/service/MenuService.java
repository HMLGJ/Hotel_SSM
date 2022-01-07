package com.hotel.service;

import java.util.List;

import com.hotel.entity.Menu;
import com.hotel.vo.MenuVo;

public interface MenuService {
	//查询所有菜单列表
	List<Menu>findMenuList();
	
	//根据角色ID查询该角色已经拥有的菜单ID的方法
	List<Integer>findMenuIdListByRoleId(int role_id);

	//根据菜单编号查询菜单信息
	List<Menu> findMenuByMenuId(List<Integer> currentRoleMenuIds);
	
	//查询菜单列表
	List<Menu> findMenuListByPage(MenuVo menuVo);
	
	//添加菜单
	int addMenu(Menu menu);
	
	//修改菜单
	int updateMenu(Menu menu);
	
	//删除菜单
	int deleteById(int menu_id);
	
	//查询该菜单是否有子菜单
	int getMenuCountByMenuId(Integer menu_id);
	
}
