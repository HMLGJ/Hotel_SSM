package com.hotel.dao;

import org.apache.ibatis.annotations.Param;

import com.hotel.entity.User;

public interface UserDao {
	
	User findUser_Phone(@Param("user_phone") String user_phone);
	
	//添加用户
	int addUser(User user);
	
	//根据用户名查询用户信息
    User findUserByName(String user_name);
    
    
	
}
