package com.hotel.entity;

public class Room {
    private Integer room_id;
    private String room_photo;
    private String room_roomnum;
    private Integer room_roomtypeid;
    private Integer room_floorid;
    //房间状态(1-已预订 2-已入住 3-可预订)
    private Integer room_status;
    private String room_roomrequirement;
    private String room_remark;
    private String room_roomdesc;//房间的详情
       
    private String room_typeName;
    private String room_floorName;
   
    private Double room_price;
    private Integer room_dednum;
    
    private String room_statusStr;
    
	public Integer getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}
	public String getRoom_photo() {
		return room_photo;
	}
	public void setRoom_photo(String room_photo) {
		this.room_photo = room_photo;
	}
	public String getRoom_roomnum() {
		return room_roomnum;
	}
	public void setRoom_roomnum(String room_roomnum) {
		this.room_roomnum = room_roomnum;
	}
	public Integer getRoom_roomtypeid() {
		return room_roomtypeid;
	}
	public void setRoom_roomtypeid(Integer room_roomtypeid) {
		this.room_roomtypeid = room_roomtypeid;
	}
	public Integer getRoom_floorid() {
		return room_floorid;
	}
	public void setRoom_floorid(Integer room_floorid) {
		this.room_floorid = room_floorid;
	}
	public Integer getRoom_status() {
		return room_status;
	}
	public void setRoom_status(Integer room_status) {
		this.room_status = room_status;
	}
	public String getRoom_roomrequirement() {
		return room_roomrequirement;
	}
	public void setRoom_roomrequirement(String room_roomrequirement) {
		this.room_roomrequirement = room_roomrequirement;
	}
	public String getRoom_remark() {
		return room_remark;
	}
	public void setRoom_remark(String room_remark) {
		this.room_remark = room_remark;
	}
	public String getRoom_roomdesc() {
		return room_roomdesc;
	}
	public void setRoom_roomdesc(String room_roomdesc) {
		this.room_roomdesc = room_roomdesc;
	}
	public String getRoom_typeName() {
		return room_typeName;
	}
	public void setRoom_typeName(String room_typeName) {
		this.room_typeName = room_typeName;
	}
	public String getRoom_floorName() {
		return room_floorName;
	}
	public void setRoom_floorName(String room_floorName) {
		this.room_floorName = room_floorName;
	}
	public Double getRoom_price() {
		return room_price;
	}
	public void setRoom_price(Double room_price) {
		this.room_price = room_price;
	}
	public Integer getRoom_dednum() {
		return room_dednum;
	}
	public void setRoom_dednum(Integer room_dednum) {
		this.room_dednum = room_dednum;
	}
	public String getRoom_statusStr() {
		//判断状态是否为空
        if(room_status!=null){
            switch (room_status){
                case 1:
                	room_statusStr = "已预订";
                    break;
                case 2:
                	room_statusStr = "已入住";
                    break;
                case 3:
                	room_statusStr = "可预订";
                    break;
            }
        }
        return room_statusStr;
	}
	public void setRoom_statusStr(String room_statusStr) {
		this.room_statusStr = room_statusStr;
	}
	
	
	
	
}
