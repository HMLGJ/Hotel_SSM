package com.hotel.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HotelAdmin {
	private Integer hotelAdmin_id;
	private String hotelAdmin_name;
	private String hotelAdmin_password;//密码
	private String hotelAdmin_salt;//密码加密、盐值
	private String hotelAdmin_createdBy;//创建人
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd") //jackson
	private Date hotelAdmin_createDate;//创建时间
	private Integer hotelAdmin_deptId;//所属部门id
	private Integer hotelAdmin_roleId;//所属角色id
	private String hotelAdmin_desc;//部门备注
	private Date hotelAdmin_hireDate;//入职时间
	
	private String hotelAdmin_deptName;//所属部门名称
	
	public Integer getHotelAdmin_id() {
		return hotelAdmin_id;
	}
	public void setHotelAdmin_id(Integer hotelAdmin_id) {
		this.hotelAdmin_id = hotelAdmin_id;
	}
	public String getHotelAdmin_name() {
		return hotelAdmin_name;
	}
	public void setHotelAdmin_name(String hotelAdmin_name) {
		this.hotelAdmin_name = hotelAdmin_name;
	}
	public String getHotelAdmin_password() {
		return hotelAdmin_password;
	}
	public void setHotelAdmin_password(String hotelAdmin_password) {
		this.hotelAdmin_password = hotelAdmin_password;
	}
	public String getHotelAdmin_salt() {
		return hotelAdmin_salt;
	}
	public void setHotelAdmin_salt(String hotelAdmin_salt) {
		this.hotelAdmin_salt = hotelAdmin_salt;
	}
	public String getHotelAdmin_createdBy() {
		return hotelAdmin_createdBy;
	}
	public void setHotelAdmin_createdBy(String hotelAdmin_createdBy) {
		this.hotelAdmin_createdBy = hotelAdmin_createdBy;
	}
	public Date getHotelAdmin_createDate() {
		return hotelAdmin_createDate;
	}
	public void setHotelAdmin_createDate(Date hotelAdmin_createDate) {
		this.hotelAdmin_createDate = hotelAdmin_createDate;
	}
	public Integer getHotelAdmin_deptId() {
		return hotelAdmin_deptId;
	}
	public void setHotelAdmin_deptId(Integer hotelAdmin_deptId) {
		this.hotelAdmin_deptId = hotelAdmin_deptId;
	}
	public Integer getHotelAdmin_roleId() {
		return hotelAdmin_roleId;
	}
	public void setHotelAdmin_roleId(Integer hotelAdmin_roleId) {
		this.hotelAdmin_roleId = hotelAdmin_roleId;
	}
	public String getHotelAdmin_deptName() {
		return hotelAdmin_deptName;
	}
	public void setHotelAdmin_deptName(String hotelAdmin_deptName) {
		this.hotelAdmin_deptName = hotelAdmin_deptName;
	}
	public String getHotelAdmin_desc() {
		return hotelAdmin_desc;
	}
	public void setHotelAdmin_desc(String hotelAdmin_desc) {
		this.hotelAdmin_desc = hotelAdmin_desc;
	}
	public Date getHotelAdmin_hireDate() {
		return hotelAdmin_hireDate;
	}
	public void setHotelAdmin_hireDate(Date hotelAdmin_hireDate) {
		this.hotelAdmin_hireDate = hotelAdmin_hireDate;
	}
	
	
	
}
