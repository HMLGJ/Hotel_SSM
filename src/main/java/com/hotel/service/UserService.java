package com.hotel.service;

import com.hotel.entity.User;

public interface UserService {
	//根据用户名登录
	User login(String user_name,String user_password);
	
	//添加用户
	int addUser(User user);
		
	//根据用户名查询用户信息
    User findUserByName(String user_name);
    

}
