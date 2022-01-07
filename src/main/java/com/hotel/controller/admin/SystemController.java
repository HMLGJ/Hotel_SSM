package com.hotel.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotel.utils.SystemConstant;

/**
 * 负责跳转页面(后台管理页面)
 *
 */

@Controller
@RequestMapping("/admin")
public class SystemController {
	
	//去到登录页面
	@RequestMapping("/login")
	public String login() {
		return "admin/login";
	}
	
	//去到后台首页
	@RequestMapping("/home")
	public String home() {
		return "admin/home";
	}
	
	//退出登录
	@RequestMapping("/logout")
	public String login(HttpSession session) {
		//清空session
		session.removeAttribute(SystemConstant.LOGINUSER);
		//重定向到登录页面
		return "redirect:/admin/login";		
	}
	
	//去到首页页面
	@RequestMapping("/desktop")
	public String toShouYe(HttpSession session) {
		return "/admin/desktop/desktop";		
	}
	
	//去到部门管理页面
	@RequestMapping("/toDeptManager")
	public String toDeptManager() {
		return "admin/dept/deptManager";
	}
	
	//去到角色管理页面
	@RequestMapping("/toRoleManager")
	public String toRoleManager() {
		return "admin/role/roleManager";
	}
	
	//去到酒店管理员管理页面
	@RequestMapping("/toHotelAdminManager")
	public String toHotelAdminManager() {
		return "admin/hotelAdmin/hotelAdminManager";
	}
	
	//去到菜单管理页面
	@RequestMapping("/toMenuManager")
	public String toMenuManager() {
		return "admin/Menu/MenuManager";
	}
	
	//去到楼层管理页面
	@RequestMapping("/toFloorManager")
	public String toFloorManager() {
		return "admin/floor/floorManager";
	}
	
	//去到房型管理页面
	@RequestMapping("/toRoomTypeManager")
	public String toRoomTypeManager() {
		return "admin/roomType/roomTypeManager";
	}
	
	//去到房间管理页面
	@RequestMapping("/toRoomManager")
	public String toRoomManager() {
		return "admin/room/roomManager";
	}
	
	//去到订单管理页面
	@RequestMapping("/toOrdersManager")
	public String toOrdersManager() {
		return "admin/orders/ordersManager";
	}
	
	//去到入住管理页面
	@RequestMapping("/toCheckinManager")
	public String toCheckinManager() {
		return "admin/checkin/checkinManager";
	}
}
