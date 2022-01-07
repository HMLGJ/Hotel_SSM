package com.hotel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.dao.MenuDao;
import com.hotel.entity.Menu;
import com.hotel.service.MenuService;
import com.hotel.vo.MenuVo;

@Service
@Transactional
public class MenuServiceImpl implements MenuService{
	@Resource
	private MenuDao menuDao;
	
	@Override
	public List<Menu> findMenuList() {
		return menuDao.findMenuList();
	}

	@Override
	public List<Integer> findMenuIdListByRoleId(int role_id) {
		return menuDao.findMenuIdListByRoleId(role_id);
	}

	@Override
	public List<Menu> findMenuByMenuId(List<Integer> currentRoleMenuIds) {
		return menuDao.findMenuByMenuId(currentRoleMenuIds);
	}

	@Override
	public List<Menu> findMenuListByPage(MenuVo menuVo) {
		return menuDao.findMenuListByPage(menuVo);
	}

	@Override
	public int addMenu(Menu menu) {
		//如果没有选择父级菜单，则默认设置父级菜单为0
		if(menu.getMenu_pid()==null) {
			menu.setMenu_pid(0);
		}
		menu.setMenu_target("_self");//设置打开的方式,没用上,先随便个赋值，或者赋个空值也行
		return menuDao.addMenu(menu);
	}
	
	@Override
	//修改菜单
    public int updateMenu(Menu menu) {
		System.out.println(menu.getMenu_id());
		System.out.println(menu.getMenu_title());
		System.out.println(menu.getMenu_icon());
		System.out.println(menu.getMenu_pid());
        return menuDao.updateMenu(menu);
    }

	@Override
	public int deleteById(int menu_id) {
		return menuDao.deleteById(menu_id);
	}

	@Override
	public int getMenuCountByMenuId(Integer menu_id) {
		return menuDao.getMenuCountByMenuId(menu_id);
	}

	
	

}
