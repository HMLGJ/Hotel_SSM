package com.hotel.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.entity.HotelAdmin;
import com.hotel.service.HotelAdminService;
import com.hotel.utils.DataGridViewResult;
import com.hotel.utils.SystemConstant;
import com.hotel.vo.HotelAdminVo;

@RestController
@RequestMapping("/admin/hotelAdmin")
public class HotelAdminController {
	@Resource
	private HotelAdminService hotelAdminService;
	
	@RequestMapping("/login")
	public String login(String hotelAdmin_name,String hotelAdmin_password,HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		//调用用户登录的方法
		HotelAdmin hotelAdmin = hotelAdminService.login(hotelAdmin_name, hotelAdmin_password);
		//判断对象是否为空，不为空表示登录成功
		if(hotelAdmin!=null) {
			//调用SystemConstant接口属性，方便维护
			session.setAttribute(SystemConstant.LOGINUSER, hotelAdmin);
			map.put(SystemConstant.SUCCESS, true);//成功
		}else {
			map.put(SystemConstant.SUCCESS, false);//失败
			map.put(SystemConstant.MESSAGE, "账号密码错误，请重新输入！");
		}
		return JSON.toJSONString(map);
		
		}	
	
	//查询员工列表
	@RequestMapping("/list")
	public DataGridViewResult list(HotelAdminVo hotelAdminVo) {
		//设置分页信息(必须写在调用分页查询的方法之前，否则会失效)
		PageHelper.startPage(hotelAdminVo.getPage(),hotelAdminVo.getLimit());
		//调用分页查询的方法
		List<HotelAdmin> hotelAdminList = hotelAdminService.findHotelAdminList(hotelAdminVo);
		//创建分页对象
		PageInfo<HotelAdmin> pageInfo = new PageInfo<HotelAdmin>(hotelAdminList);
		//返回封装数据表格类DataGridViewResult
		return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());	
	}
	
	//添加管理员
	@RequestMapping("/addHotelAdmin")
	public String addHotelAdmin(HotelAdmin hotelAdmin,HttpSession session) {
		//获取当前登录用户
		HotelAdmin loginName = (HotelAdmin)session.getAttribute(SystemConstant.LOGINUSER);
		//设置创建人
		hotelAdmin.setHotelAdmin_createdBy(loginName.getHotelAdmin_name());
		//设置部门名称(如何获取到部门名称(获取下拉列表的id，让表1部门名称等于表2部门名称))
		//hotelAdmin.setHotelAdmin_deptName();
		Map<String,Object> map = new HashMap<String,Object>();
		//调用添加管理员的方法
		if(hotelAdminService.addHotelAdmin(hotelAdmin)>0) {
			map.put(SystemConstant.SUCCESS,true);//成功
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);//失败
            map.put(SystemConstant.MESSAGE,"添加失败");
		}
		//将map集合以JSON格式返回
        return JSON.toJSONString(map);
	}
	
	//修改管理员
	@RequestMapping("/updateHotelAdmin")
	public String updateHotelAdmin(HotelAdmin hotelAdmin,HttpSession session) {
		//获取当前登录用户
		HotelAdmin loginName = (HotelAdmin)session.getAttribute(SystemConstant.LOGINUSER);
		//设置创建人
		//hotelAdmin.setHotelAdmin_createdBy(loginName.getHotelAdmin_name());
		Map<String,Object> map = new HashMap<String,Object>();
		//调用添加管理员的方法
		if(hotelAdminService.updateHotelAdmin(hotelAdmin)>0) {
			map.put(SystemConstant.SUCCESS,true);//成功
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);//失败
            map.put(SystemConstant.MESSAGE,"修改失败");
		}
		//将map集合以JSON格式返回
        return JSON.toJSONString(map);
	}
	
	//删除管理员
	@RequestMapping("/deleteById")
	public String deleteById(int hotelAdmin_id) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(hotelAdminService.deleteById(hotelAdmin_id)>0) {
			map.put(SystemConstant.SUCCESS,true);//成功
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);//失败
            map.put(SystemConstant.MESSAGE,"删除失败");
		}
		//将map集合以JSON格式返回
        return JSON.toJSONString(map);
	}
	
	//重置密码
	@RequestMapping("/resetPwd")
	public String resetPwd(int hotelAdmin_id) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(hotelAdminService.resetPwd(hotelAdmin_id)>0) {
			map.put(SystemConstant.SUCCESS,true);//成功
            map.put(SystemConstant.MESSAGE,"密码重置成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);//失败
            map.put(SystemConstant.MESSAGE,"密码重置失败");
		}
		//将map集合以JSON格式返回
        return JSON.toJSONString(map);
	}
}
