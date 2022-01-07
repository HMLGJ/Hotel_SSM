package com.hotel.utils;

public interface SystemConstant {
	//加密的次数
	Integer PASSWORD_COUNT = 5;
	//后台登录用户保护的Key
	String LOGINUSER = "loginUser";
	//成功
	String SUCCESS = "success";
	//验证的提示信息
	String MESSAGE = "message";
	String EXIST = "exist";
	
	//默认密码
	String DEFAULT_LOGIN_PWD = "123456";
	
	//图片上传的地址
	String IMAGE_UPLOAD_PATH = "D:/project/hotel/upload/";
	
	//前台用户登录时保存key
	String FRONT_LOGIN_USER = "currentUser";
}
