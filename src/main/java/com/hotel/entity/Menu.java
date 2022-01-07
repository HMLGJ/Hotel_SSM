package com.hotel.entity;

public class Menu {
	private Integer menu_id;//菜单编号
	private Integer menu_pid;//所属父菜单id
	private String menu_title;//菜单的名称
	private Integer menu_spread;//菜单是否展开(0-否，1-是)
	private String menu_target;//打开方式
	private String menu_icon;//
	private String menu_href;//跳转的链接
	public Integer getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}
	public Integer getMenu_pid() {
		return menu_pid;
	}
	public void setMenu_pid(Integer menu_pid) {
		this.menu_pid = menu_pid;
	}
	public String getMenu_title() {
		return menu_title;
	}
	public void setMenu_title(String menu_title) {
		this.menu_title = menu_title;
	}
	public Integer getMenu_spread() {
		return menu_spread;
	}
	public void setMenu_spread(Integer menu_spread) {
		this.menu_spread = menu_spread;
	}
	public String getMenu_target() {
		return menu_target;
	}
	public void setMenu_target(String menu_target) {
		this.menu_target = menu_target;
	}
	public String getMenu_icon() {
		return menu_icon;
	}
	public void setMenu_icon(String menu_icon) {
		this.menu_icon = menu_icon;
	}
	public String getMenu_href() {
		return menu_href;
	}
	public void setMenu_href(String menu_href) {
		this.menu_href = menu_href;
	}
	
	
}
