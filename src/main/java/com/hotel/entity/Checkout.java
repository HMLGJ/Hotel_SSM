package com.hotel.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Checkout {

    private Long checkOut_id;
    private String checkOut_number;
    private Long checkOut_checkInId;
    private Double checkOut_consumePrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd")//前台往后台传数据
    @JsonFormat(pattern = "yyyy-MM-dd")//后台往前台传数据
    private Date checkOut_createDate;
    private Integer checkOut_createdBy;
    private String checkOut_remark;
	public Long getCheckOut_id() {
		return checkOut_id;
	}
	public void setCheckOut_id(Long checkOut_id) {
		this.checkOut_id = checkOut_id;
	}
	public String getCheckOut_number() {
		return checkOut_number;
	}
	public void setCheckOut_number(String checkOut_number) {
		this.checkOut_number = checkOut_number;
	}
	public Long getCheckOut_checkInId() {
		return checkOut_checkInId;
	}
	public void setCheckOut_checkInId(Long checkOut_checkInId) {
		this.checkOut_checkInId = checkOut_checkInId;
	}
	public Double getCheckOut_consumePrice() {
		return checkOut_consumePrice;
	}
	public void setCheckOut_consumePrice(Double checkOut_consumePrice) {
		this.checkOut_consumePrice = checkOut_consumePrice;
	}
	public Date getCheckOut_createDate() {
		return checkOut_createDate;
	}
	public void setCheckOut_createDate(Date checkOut_createDate) {
		this.checkOut_createDate = checkOut_createDate;
	}
	public Integer getCheckOut_createdBy() {
		return checkOut_createdBy;
	}
	public void setCheckOut_createdBy(Integer checkOut_createdBy) {
		this.checkOut_createdBy = checkOut_createdBy;
	}
	public String getCheckOut_remark() {
		return checkOut_remark;
	}
	public void setCheckOut_remark(String checkOut_remark) {
		this.checkOut_remark = checkOut_remark;
	}


    
}
