package com.hotel.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hotel.entity.User;
import com.hotel.service.UserService;
import com.hotel.utils.SystemConstant;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	
	//用户登录
	@ResponseBody
	@RequestMapping("/login2")
	public String login2(String user_phone,String user_password,HttpSession session) {
		//创建Map集合，保存结果信息
		Map<String,Object> map = new HashMap<String,Object>();
		//调用用户登录的方法
		User user = userService.login(user_phone, user_password);
		//判断对象是否为空，不为空表示登录成功
		if(user!=null) {
			map.put(SystemConstant.SUCCESS, true);//成功
			user.setUser_password(null);//清空
			//调用SystemConstant接口属性，方便维护.并保存用户信息到会话中
			session.setAttribute(SystemConstant.LOGINUSER, user);
		}else {
			map.put(SystemConstant.SUCCESS, false);//失败
			map.put(SystemConstant.MESSAGE, "账号密码错误，请重新输入！");
		}
		return JSON.toJSONString(map);
		
		}
	
	//添加用户（注册用户）
    @ResponseBody
    @RequestMapping("/register")
    public String register(User user){
        //创建Map集合，保存结果信息
        Map<String,Object> map = new HashMap<String,Object>();
        //调用注册的方法
        if(userService.addUser(user)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"恭喜你,注册成功!");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"很遗憾,注册失败,请重新尝试!");
        }
        return JSON.toJSONString(map);
    }
    
    /**
     * 检查用户名是否存在
     * @param loginName
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkName")
    public String checkName(String user_name){
        //创建Map集合，保存结果信息
        Map<String,Object> map = new HashMap<String,Object>();
        //调用根据用户名查询用户信息的方法
        if(userService.findUserByName(user_name)!=null){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"用户名已被使用,请重新输入！");
        }else{
            map.put(SystemConstant.EXIST,false);
        }
        return JSON.toJSONString(map);
    }
    
    /**
     * 登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public String login(String user_name, String user_password, HttpSession session){
        //创建Map集合，保存结果信息
        Map<String,Object> map = new HashMap<String,Object>();
        //调用登录的方法
        User loginUser = userService.login(user_name, user_password);
        //判断对象是否为空
        if(loginUser!=null){
            map.put(SystemConstant.SUCCESS,true);
            loginUser.setUser_password(null);//清空
            //保存用户信息到会话中,设置前台的登录用户，不能与后台登录session一样，不然两个键值同样，同时登录会报500错误
            session.setAttribute(SystemConstant.FRONT_LOGIN_USER,loginUser);
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"用户名或密码错误，请重新输入！");
        }
        return JSON.toJSONString(map);
    }
	
}
