package com.hotel.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.dao.RoleDao;
import com.hotel.entity.Role;
import com.hotel.service.RoleService;
import com.hotel.vo.RoleVo;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleDao roleDao;

	//查询角色列表
	@Override
	public List<Role> findRolerList(RoleVo roleVo) {
		return roleDao.findRolerList(roleVo);
	}

	@Override
	public int addRole(Role role) {
		return roleDao.addRole(role);
	}

	@Override
	public int updateRole(Role role) {
		return roleDao.updateRole(role);
	}

	@Override
	public int deleteById(Integer role_id) {
		return roleDao.deleteById(role_id);
	}

	@Override
	public int saveRoleMenu(String ids, Integer role_id) {
		try {
            //删除原有的菜单关系,后添加
			roleDao.deleteRoleMenu(role_id);//根据角色ID删除
            //将ids拆分成数组
            String [] idsStr = ids.split(",");
            //循环调用
            for (int i = 0; i <idsStr.length ; i++) {
                //调用保存菜单角色关系的方法
            	roleDao.addRoleMenu(role_id,idsStr[i]);
            }
            return 1;//成功
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;//失败
	}

	@Override
	public List<Map<String, Object>> findRoleListByMap() {
		
		return roleDao.findRoleListByMap();
	}
	
	////根据管理员id查询管理员拥有的角色列表(未实现)
	@Override
	public List<Integer> findHotelAdminRoleByHotelAdminId(int hotelAdmin_id) {
		return roleDao.findHotelAdminRoleByHotelAdminId(hotelAdmin_id);
	}

	

}
