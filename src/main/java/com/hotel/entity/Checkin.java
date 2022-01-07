package com.hotel.entity;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Checkin implements Serializable {
    private Long checkin_id;
    private Integer checkin_roomtypeid;
    private Long checkin_roomid;
    private String checkin_customername;
    private String checkin_idcard;
    private String checkin_phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")//前台往后台传数据
    @JsonFormat(pattern = "yyyy-MM-dd")//后台往前台传数据
    private Date checkin_arrivedate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")//前台往后台传数据
    @JsonFormat(pattern = "yyyy-MM-dd")//后台往前台传数据
    private Date checkin_leavedate;
    private BigDecimal checkin_payprice;
    private Integer checkin_status;
    private String checkin_remark;
    private Integer checkin_ordersid;
    private Integer checkin_createdby;
    private Date checkin_createdate;
    private Integer checkin_modifyby;
    private Date checkin_modifydate;

    //房型类型
    private RoomType checkin_roomType;
    //房间
    private Room checkin_room;
	public Long getCheckin_id() {
		return checkin_id;
	}
	public void setCheckin_id(Long checkin_id) {
		this.checkin_id = checkin_id;
	}
	public Integer getCheckin_roomtypeid() {
		return checkin_roomtypeid;
	}
	public void setCheckin_roomtypeid(Integer checkin_roomtypeid) {
		this.checkin_roomtypeid = checkin_roomtypeid;
	}
	public Long getCheckin_roomid() {
		return checkin_roomid;
	}
	public void setCheckin_roomid(Long checkin_roomid) {
		this.checkin_roomid = checkin_roomid;
	}
	public String getCheckin_customername() {
		return checkin_customername;
	}
	public void setCheckin_customername(String checkin_customername) {
		this.checkin_customername = checkin_customername;
	}
	public String getCheckin_idcard() {
		return checkin_idcard;
	}
	public void setCheckin_idcard(String checkin_idcard) {
		this.checkin_idcard = checkin_idcard;
	}
	public String getCheckin_phone() {
		return checkin_phone;
	}
	public void setCheckin_phone(String checkin_phone) {
		this.checkin_phone = checkin_phone;
	}
	public Date getCheckin_arrivedate() {
		return checkin_arrivedate;
	}
	public void setCheckin_arrivedate(Date checkin_arrivedate) {
		this.checkin_arrivedate = checkin_arrivedate;
	}
	public Date getCheckin_leavedate() {
		return checkin_leavedate;
	}
	public void setCheckin_leavedate(Date checkin_leavedate) {
		this.checkin_leavedate = checkin_leavedate;
	}
	public BigDecimal getCheckin_payprice() {
		return checkin_payprice;
	}
	public void setCheckin_payprice(BigDecimal checkin_payprice) {
		this.checkin_payprice = checkin_payprice;
	}
	public Integer getCheckin_status() {
		return checkin_status;
	}
	public void setCheckin_status(Integer checkin_status) {
		this.checkin_status = checkin_status;
	}
	public String getCheckin_remark() {
		return checkin_remark;
	}
	public void setCheckin_remark(String checkin_remark) {
		this.checkin_remark = checkin_remark;
	}
	public Integer getCheckin_ordersid() {
		return checkin_ordersid;
	}
	public void setCheckin_ordersid(Integer checkin_ordersid) {
		this.checkin_ordersid = checkin_ordersid;
	}
	public Integer getCheckin_createdby() {
		return checkin_createdby;
	}
	public void setCheckin_createdby(Integer checkin_createdby) {
		this.checkin_createdby = checkin_createdby;
	}
	public Date getCheckin_createdate() {
		return checkin_createdate;
	}
	public void setCheckin_createdate(Date checkin_createdate) {
		this.checkin_createdate = checkin_createdate;
	}
	public Integer getCheckin_modifyby() {
		return checkin_modifyby;
	}
	public void setCheckin_modifyby(Integer checkin_modifyby) {
		this.checkin_modifyby = checkin_modifyby;
	}
	public Date getCheckin_modifydate() {
		return checkin_modifydate;
	}
	public void setCheckin_modifydate(Date checkin_modifydate) {
		this.checkin_modifydate = checkin_modifydate;
	}
	public RoomType getCheckin_roomType() {
		return checkin_roomType;
	}
	public void setCheckin_roomType(RoomType checkin_roomType) {
		this.checkin_roomType = checkin_roomType;
	}
	public Room getCheckin_room() {
		return checkin_room;
	}
	public void setCheckin_room(Room checkin_room) {
		this.checkin_room = checkin_room;
	}


}