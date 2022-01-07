package com.hotel.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hotel.vo.MenuVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.utils.DataGridViewResult;
import com.hotel.utils.TreeNode;
import com.hotel.entity.HotelAdmin;
import com.hotel.entity.Menu;
import com.hotel.service.MenuService;
import com.hotel.utils.MenuNode;
import com.hotel.utils.SystemConstant;
import com.hotel.utils.TreeUtil;

@RestController
@RequestMapping("/admin/menu")
public class MenuController {
	//注入Service
	@Resource
	private MenuService menuService;
	
	//加载首页左侧菜单导航
	@RequestMapping("/loadMenuList")
    public String loadMenuList(HttpSession session){
		//创建三个Map集合，保存json里面的三个菜单信息
        //创建Map集合，保存MenuInfo菜单信息
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        //创建Map集合，保存homeInfo信息
        Map<String,Object> homeInfo = new LinkedHashMap<String,Object>();
        //创建Map集合，保存logoInfo信息
        Map<String,Object> logoInfo = new LinkedHashMap<String,Object>();
        //调用查询所有菜单列表的方法
        List<Menu> menuList = menuService.findMenuList();//该方法无论是哪个角色的用户登录，都能够查询所有的功能模块
        //获取当前登录员工
        HotelAdmin hotelAdmin = (HotelAdmin) session.getAttribute(SystemConstant.LOGINUSER);
        //根据当前用户的角色动态显示菜单列表(未实现，需要加一个方法，然后把第46行代码注释掉)
        //List<Menu> menuList = menuService.findMenuListByEmployeeId(employee.getId());
        //创建集合，保存菜单关系
        List<MenuNode> menuNodeList = new ArrayList<MenuNode>();
        //循环遍历菜单列表,目的是创建菜单之间层级关系（layuimini的json要求这么写）
        for (Menu menu : menuList) {
            //创建菜单节点对象
            MenuNode menuNode = new MenuNode();
            menuNode.setHref(menu.getMenu_href());//链接地址
            menuNode.setIcon(menu.getMenu_icon());//菜单图标
            menuNode.setId(menu.getMenu_id());//菜单编号
            menuNode.setPid(menu.getMenu_pid());//父级菜单编号
            menuNode.setSpread(menu.getMenu_spread());//是否展开
            menuNode.setTarget(menu.getMenu_target());//打开方式
            menuNode.setTitle(menu.getMenu_title());//菜单名称
            //将对象添加到集合
            menuNodeList.add(menuNode);
        }
        //保存HomeInfo信息(按init.json里面的格式)
        homeInfo.put("title","酒店信息管理系统首页");
        homeInfo.put("href","/admin/desktop");
        //保存logoInfo信息(按init.json里面的格式)
        logoInfo.put("title","酒店管理系统");//logo标题
        logoInfo.put("image","/statics/layui/images/logo.png");//logo图片
        logoInfo.put("href","/admin/home");//首页地址
        //将菜单信息添加到MenuInfo集合中(按init.json里面的格式)
        map.put("menuInfo", TreeUtil.toTree(menuNodeList,0));
        map.put("homeInfo",homeInfo);
        map.put("logoInfo",logoInfo);

        return JSON.toJSONString(map);
    }
	
	//加载菜单管理页面的左侧导航树（菜单管理）
    @RequestMapping("/loadMenuTree")
    public DataGridViewResult loadMenuTree(){
        //调用查询所有菜单列表的方法
        List<Menu> menuList = menuService.findMenuList();
        //创建集合保存节点信息
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        //循环遍历菜单列表集合
        for (Menu menu : menuList) {
            //判断当前菜单是否展开
            Boolean spread = (menu.getMenu_spread()==null || menu.getMenu_spread()==1) ? true : false;
            //将菜单信息保存到treeNodes集合中
            treeNodes.add(new TreeNode(menu.getMenu_id(),menu.getMenu_pid(),menu.getMenu_title(),spread));
        }
        //返回数据
        return new DataGridViewResult(treeNodes);
    }
    
    //分页查询菜单列表
    @RequestMapping("/list")
    public DataGridViewResult list(MenuVo menuVo){
        //设置分页信息
        PageHelper.startPage(menuVo.getPage(),menuVo.getLimit());
        //调用查询菜单列表的方法
        List<Menu> menuList = menuService.findMenuListByPage(menuVo);
        //创建分页对象
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(menuList);
        //返回数据(getTotal总数量，getList记录列表)
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }
    
    //添加菜单
    @RequestMapping("/addMenu")
    public String addMenu(Menu menu){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用新增菜单的方法
        if(menuService.addMenu(menu)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加失败");
        }
        return JSON.toJSONString(map);
    }
    
    //修改菜单
    @RequestMapping("/updateMenu")
    public String updateMenu(Menu menu){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用修改菜单的方法
        if(menuService.updateMenu(menu)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"修改失败");
        }
        return JSON.toJSONString(map);
    }
    
    //判断该菜单是否有子菜单
    @RequestMapping("/checkMenuHasChild")
    public String checkMenuHasChild(Integer menu_id){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用查询菜单的方法
        if(menuService.getMenuCountByMenuId(menu_id)>0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"该菜单下有子菜单，无法删除");
        }else{
            map.put(SystemConstant.EXIST,false);
        }
        return JSON.toJSONString(map);
    }
    
    //删除菜单
    @RequestMapping("/deleteById")
    public String deleteById(Integer menu_id){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用修改菜单的方法
        if(menuService.deleteById(menu_id)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除失败");
        }
        return JSON.toJSONString(map);
    }
}
