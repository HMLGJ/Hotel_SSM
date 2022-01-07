package com.hotel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.dao.UserDao;
import com.hotel.entity.User;
import com.hotel.service.UserService;
import com.hotel.utils.PasswordUtil;
import com.hotel.utils.SystemConstant;
import com.hotel.utils.UUIDUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;
	
	//根据用户登录
	@Override
	public User login(String user_name, String user_password) {
		User user = userDao.findUserByName(user_name);
		//判断对象是否为空
		if(user!=null) {
			//将密码加密处理
			String newPassword = PasswordUtil.md5(user_password,user.getUser_salt(),SystemConstant.PASSWORD_COUNT);
			//比较密码
			if(user.getUser_password().equals(newPassword)) {
				return user;
			}
		}
		//登录失败
		return null;
	}
	
	//添加用户
	@Override
	public int addUser(User user) {
		//自动生存盐值
		user.setUser_salt(UUIDUtils.randomUUID());
		//将密码加密
		user.setUser_password(PasswordUtil.md5(user.getUser_password(), user.getUser_salt(), SystemConstant.PASSWORD_COUNT));
		return userDao.addUser(user);
	}
	
	
	//根据user_name查询信息
	@Override
	public User findUserByName(String user_name) {
		return userDao.findUserByName(user_name);
	}

}
