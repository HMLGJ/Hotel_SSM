package com.hotel.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {
    private Long user_id;//用户编号
    private String user_name;//登陆账号
    private String user_password;//登录密码
    private String user_idCard;//身份证号码(因为超过11，不能用Int类型)
    private String user_phone;//手机号码
    private Integer user_status;//状态 1-可用  0-异常
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date user_createDate;//注册时间
    private String user_salt;//盐值
    private String user_realName;
    
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_idCard() {
		return user_idCard;
	}
	public void setUser_idCard(String user_idCard) {
		this.user_idCard = user_idCard;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public Integer getUser_status() {
		return user_status;
	}
	public void setUser_status(Integer user_status) {
		this.user_status = user_status;
	}
	public Date getUser_createDate() {
		return user_createDate;
	}
	public void setUser_createDate(Date user_createDate) {
		this.user_createDate = user_createDate;
	}
	public String getUser_salt() {
		return user_salt;
	}
	public void setUser_salt(String user_salt) {
		this.user_salt = user_salt;
	}
	public String getUser_realName() {
		return user_realName;
	}
	public void setUser_realName(String user_realName) {
		this.user_realName = user_realName;
	}

   
}
