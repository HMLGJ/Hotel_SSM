package com.hotel.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.dao.HotelAdminDao;
import com.hotel.entity.Dept;
import com.hotel.entity.HotelAdmin;
import com.hotel.service.HotelAdminService;
import com.hotel.utils.PasswordUtil;
import com.hotel.utils.SystemConstant;
import com.hotel.utils.UUIDUtils;
import com.hotel.vo.HotelAdminVo;

@Service
@Transactional
public class HotelAdminServiceImpl implements HotelAdminService{
	@Resource
	private HotelAdminDao hotelAdminDao;

	@Override
	public HotelAdmin login(String hotelAdmin_name, String hotelAdmin_password) {
		HotelAdmin hotelAdmin = hotelAdminDao.findHotelAdmin_name(hotelAdmin_name);
		//判断对象是否为空
		if(hotelAdmin!=null) {
			//将密码加密处理
			String newPassword = PasswordUtil.md5(hotelAdmin_password,hotelAdmin.getHotelAdmin_salt(),SystemConstant.PASSWORD_COUNT);
			//比较密码
			if(hotelAdmin.getHotelAdmin_password().equals(newPassword)) {
				return hotelAdmin;
			}
		}
		//登录失败
		return null;
	}

	@Override
	public int getHotelAdminCountByDeptId(Integer hotelAdmin_deptId) {
		return hotelAdminDao.getHotelAdminCountByDeptId(hotelAdmin_deptId);
	}

	@Override
	public int gerHotelAdminCountByRoleId(Integer hotelAdmin_roleId) {
		return hotelAdminDao.gerHotelAdminCountByRoleId(hotelAdmin_roleId);
	}

	@Override
	public List<HotelAdmin> findHotelAdminList(HotelAdminVo hotelAdminVo) {
		return hotelAdminDao.findHotelAdminList(hotelAdminVo);
	}

	@Override
	public int addHotelAdmin(HotelAdmin hotelAdmin) {
		hotelAdmin.setHotelAdmin_salt(UUIDUtils.randomUUID());//加密盐值
		hotelAdmin.setHotelAdmin_createDate(new Date());//创建时间
		hotelAdmin.setHotelAdmin_password(PasswordUtil.md5(SystemConstant.DEFAULT_LOGIN_PWD,hotelAdmin.getHotelAdmin_salt(),SystemConstant.PASSWORD_COUNT));//密码
		return hotelAdminDao.addHotelAdmin(hotelAdmin);

	}

	@Override
	public int updateHotelAdmin(HotelAdmin hotelAdmin) {
		return hotelAdminDao.updateHotelAdmin(hotelAdmin);
	}

	@Override
	public int deleteById(Integer hotelAdmin_id) {
		//删除管理员角色关系的数据
		hotelAdminDao.deleteHotelAdminAndRloe(hotelAdmin_id);
		//调用删除员工的方法
		return hotelAdminDao.deleteById(hotelAdmin_id);
	}

	@Override
	//重置密码
	public int resetPwd(int hotelAdmin_id) {
		HotelAdmin hotelAdmin = new HotelAdmin();
		//先加密盐值
		hotelAdmin.setHotelAdmin_salt(UUIDUtils.randomUUID());
		//加完密盐值后给密码重新负责赋值
		hotelAdmin.setHotelAdmin_password(PasswordUtil.md5(SystemConstant.DEFAULT_LOGIN_PWD, hotelAdmin.getHotelAdmin_salt(), SystemConstant.PASSWORD_COUNT));
		//根据主键改
		hotelAdmin.setHotelAdmin_id(hotelAdmin_id);
		//调用修改密码方法
		return hotelAdminDao.updateHotelAdmin(hotelAdmin);
	}

		//测试
	@Override
	public HotelAdmin ceshi(HotelAdmin hotelAdmin) {
		Dept dept = new Dept();
		if(hotelAdmin.getHotelAdmin_deptId()==dept.getDept_id()) {
			hotelAdmin.setHotelAdmin_deptName(dept.getDept_name());
		}
		return null;
	}

}
