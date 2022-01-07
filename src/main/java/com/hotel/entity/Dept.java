package com.hotel.entity;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

public class Dept {
	private Integer dept_id;
	private String dept_name;
	private String dept_address;
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd") //jackson
	private Date dept_createDate;
	private String dept_remark;
	public Integer getDept_id() {
		return dept_id;
	}
	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getDept_address() {
		return dept_address;
	}
	public void setDept_address(String dept_address) {
		this.dept_address = dept_address;
	}
	public Date getDept_createDate() {
		return dept_createDate;
	}
	public void setDept_createDate(Date dept_createDate) {
		this.dept_createDate = dept_createDate;
	}
	public String getDept_remark() {
		return dept_remark;
	}
	public void setDept_remark(String dept_remark) {
		this.dept_remark = dept_remark;
	}
	
	
}
