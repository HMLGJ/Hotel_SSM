package com.hotel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Orders {
    private Integer orders_id;//订单主键
    private String orders_no;//订单号
    private Integer orders_accountid;//用户id
    private Integer orders_roomtypeid;//房型ID
    private Integer orders_roomid;//房间ID
    private String orders_reservationname;//预订人姓名
    private String orders_idcard;//身份证号码
    private String orders_phone;//电话
    private Integer orders_status;//订单状态 1-待确认 2-已确认 3-已入住
    //@JSONField(format = "yyyy-MM-dd HH:mm:ss") //阿里巴巴
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //jackson
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orders_reservedate;//预订时间
    //@DateTimeFormat(pattern = "yyyy-MM-dd")//这个不能用jackson格式，不然会报400错误
    @DateTimeFormat(pattern = "yyyy-MM-dd")//前台往后台传数据
    @JsonFormat(pattern = "yyyy-MM-dd")//后台往前台传数据
    private Date orders_arrivedate;//入住时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")//前台往后台传数据
    @JsonFormat(pattern = "yyyy-MM-dd")//后台往前台传数据
    private Date orders_leavedate;//离店时间
    private Double orders_reserveprice;//预订房价
    private String orders_remark;//备注
  
    private Room orders_room;//房间对象
    private RoomType orders_roomType;//房型对象
	public Integer getOrders_id() {
		return orders_id;
	}
	public void setOrders_id(Integer orders_id) {
		this.orders_id = orders_id;
	}
	public String getOrders_no() {
		return orders_no;
	}
	public void setOrders_no(String orders_no) {
		this.orders_no = orders_no;
	}
	public Integer getOrders_accountid() {
		return orders_accountid;
	}
	public void setOrders_accountid(Integer orders_accountid) {
		this.orders_accountid = orders_accountid;
	}
	public Integer getOrders_roomtypeid() {
		return orders_roomtypeid;
	}
	public void setOrders_roomtypeid(Integer orders_roomtypeid) {
		this.orders_roomtypeid = orders_roomtypeid;
	}
	public Integer getOrders_roomid() {
		return orders_roomid;
	}
	public void setOrders_roomid(Integer orders_roomid) {
		this.orders_roomid = orders_roomid;
	}
	public String getOrders_reservationname() {
		return orders_reservationname;
	}
	public void setOrders_reservationname(String orders_reservationname) {
		this.orders_reservationname = orders_reservationname;
	}
	public String getOrders_idcard() {
		return orders_idcard;
	}
	public void setOrders_idcard(String orders_idcard) {
		this.orders_idcard = orders_idcard;
	}
	public String getOrders_phone() {
		return orders_phone;
	}
	public void setOrders_phone(String orders_phone) {
		this.orders_phone = orders_phone;
	}
	public Integer getOrders_status() {
		return orders_status;
	}
	public void setOrders_status(Integer orders_status) {
		this.orders_status = orders_status;
	}
	public Date getOrders_reservedate() {
		return orders_reservedate;
	}
	public void setOrders_reservedate(Date orders_reservedate) {
		this.orders_reservedate = orders_reservedate;
	}
	public Date getOrders_arrivedate() {
		return orders_arrivedate;
	}
	public void setOrders_arrivedate(Date orders_arrivedate) {
		this.orders_arrivedate = orders_arrivedate;
	}
	public Date getOrders_leavedate() {
		return orders_leavedate;
	}
	public void setOrders_leavedate(Date orders_leavedate) {
		this.orders_leavedate = orders_leavedate;
	}
	public Double getOrders_reserveprice() {
		return orders_reserveprice;
	}
	public void setOrders_reserveprice(Double orders_reserveprice) {
		this.orders_reserveprice = orders_reserveprice;
	}
	public String getOrders_remark() {
		return orders_remark;
	}
	public void setOrders_remark(String orders_remark) {
		this.orders_remark = orders_remark;
	}
	public Room getOrders_room() {
		return orders_room;
	}
	public void setOrders_room(Room orders_room) {
		this.orders_room = orders_room;
	}
	public RoomType getOrders_roomType() {
		return orders_roomType;
	}
	public void setOrders_roomType(RoomType orders_roomType) {
		this.orders_roomType = orders_roomType;
	}

    

}
