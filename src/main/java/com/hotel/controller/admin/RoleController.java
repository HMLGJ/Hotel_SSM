package com.hotel.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hotel.entity.Menu;
import com.hotel.utils.TreeNode;
import com.hotel.utils.SystemConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.entity.Role;
import com.hotel.service.HotelAdminService;
import com.hotel.service.MenuService;
import com.hotel.service.RoleService;
import com.hotel.utils.DataGridViewResult;
import com.hotel.vo.RoleVo;

@RestController
@RequestMapping("/admin/role")
public class RoleController {
	
	@Resource
	private RoleService roleService;
	@Resource
	private HotelAdminService hotelAdminService;
	@Resource
	private MenuService menuService;
	
	@RequestMapping("/list")
    public DataGridViewResult list(RoleVo roleVo){
        //设置分页信息
        PageHelper.startPage(roleVo.getPage(),roleVo.getLimit());
        //调用查询角色列表的方法
        List<Role> roleList = roleService.findRolerList(roleVo);
        //创建分页信息对象
        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
        //返回数据(参数1：总数量，参数2：角色列表)
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }
	
	//添加角色
    @RequestMapping("/addRole")
    public String addRole(Role role){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用添加角色的方法
        if(roleService.addRole(role)>0){
            map.put(SystemConstant.SUCCESS,true);//成功
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);//失败
            map.put(SystemConstant.MESSAGE,"添加失败");
        }
        //将map集合以JSON格式返回
        return JSON.toJSONString(map);
    }

    // 修改角色
    @RequestMapping("/updateRole")
    public String updateRole(Role role){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用修改角色的方法
        if(roleService.updateRole(role)>0){
            map.put(SystemConstant.SUCCESS,true);//成功
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);//失败
            map.put(SystemConstant.MESSAGE,"修改失败");
        }
        //将map集合以JSON格式返回
        return JSON.toJSONString(map);
    }
    
  //检查该角色下是否存在员工信息
  	 @RequestMapping("/checkRoleHasEmployee") 
  	 public String checkRoleHasEmployee(Integer role_id){ 
  		 Map<String,Object> map = new HashMap<String,Object>();
  		//调用根据角色编号查询员工数量的方法
  		 if(hotelAdminService.gerHotelAdminCountByRoleId(role_id)>0){
  			map.put(SystemConstant.EXIST,true);//存在
  		 	map.put(SystemConstant.MESSAGE,"该角色存在员工信息，无法删除!");
  		 	}else{
  		 	map.put(SystemConstant.EXIST,false);//不存在 
  		 }
  		 //System.out.println(hotelAdminService.getHotelAdminCountByDeptId(role_id));
  		 return JSON.toJSONString(map);
  	 }
  	 
  	//删除角色
     @RequestMapping("/deleteById")
     public String deleteById(Integer role_id){
         Map<String,Object> map = new HashMap<String,Object>();
         //调用删除角色的方法
         if(roleService.deleteById(role_id)>0){
             map.put(SystemConstant.SUCCESS,true);//成功
             map.put(SystemConstant.MESSAGE,"删除成功");
         }else{
             map.put(SystemConstant.SUCCESS,false);//失败
             map.put(SystemConstant.MESSAGE,"删除失败");
         }
         return JSON.toJSONString(map);
     }
     
     //根据角色ID查询该角色拥有的菜单
     @RequestMapping("/initMenuTree")
     public DataGridViewResult initMenuTree(Integer role_id){
         //调用查询菜单列表的方法
         List<Menu> menuList = menuService.findMenuList();
         //调用根据角色ID查询该角色已经拥有的菜单ID的方法（拥有的菜单）
         List<Integer> currentRoleMenuIds = menuService.findMenuIdListByRoleId(role_id);
         //定义集合，保存菜单信息（全部的菜单）
         List<Menu> currentMenus = new ArrayList<Menu>();
         //判断集合是否存在数据
         if(currentRoleMenuIds!=null && currentRoleMenuIds.size()>0){
             //根据菜单ID查询该菜单的信息（用于判断全部菜单与拥有菜单，如果相等，复选框就选中）
             currentMenus = menuService.findMenuByMenuId(currentRoleMenuIds);
         }
         //创建集合保存树节点信息
         List<TreeNode> treeNodes = new ArrayList<TreeNode>();
         //循环遍历所有菜单
         for (Menu menu : menuList) {
             //定义变量，标识是否选中
             String checkArr = "0";//0表示复选框不选中，1表示选中复选框
             //内层循环遍历当前角色拥有的权限菜单
             //循环比较的原因：比较两个集合中的数据是否有相同的，有相同的表示当前角色拥有这个权限
             for (Menu currentMenu : currentMenus) {
                 //如果ID相等，表示当前角色有这个菜单，有这个菜单则需要将复选框选中
                 if(menu.getMenu_id() == currentMenu.getMenu_id()){
                     checkArr ="1";//选中
                     break;
                 }
             }
             //定义变量，表示菜单是否展开
             Boolean spread = (menu.getMenu_spread()==null || menu.getMenu_spread()==1) ? true : false;
             treeNodes.add(new TreeNode(menu.getMenu_id(),menu.getMenu_pid(),menu.getMenu_title(),spread,checkArr));
         }
         //将数据返回到页面
         return new DataGridViewResult(treeNodes);
     }
     
     //分配菜单
     @RequestMapping("/saveRoleMenu")
     public String saveRoleMenu(String ids,Integer role_id){
         Map<String,Object> map = new HashMap<String,Object>();
         //调用保存角色菜单关系的方法
         if(roleService.saveRoleMenu(ids,role_id)>0){
             map.put("message","菜单分配成功");
         }else{
             map.put("message","菜单分配失败");
         }
         return JSON.toJSONString(map);
     }
     
     //根据管理员id查询管理员拥有的角色(未实现)
     @RequestMapping("/initRloeByHotelAdminId")
     public DataGridViewResult initRloeByHotelAdminId(int role_id) {
    	//调用查询所有角色列表的方法
         List<Map<String, Object>> roleList = roleService.findRoleListByMap();
         //调用根据员工ID查询该员工拥有的角色列表的方法
         List<Integer> roleIds = roleService.findHotelAdminRoleByHotelAdminId(role_id);
         //循环比较两个集合中的角色ID值是否相等，相等则选中该角色
         for (Map<String, Object> map : roleList) {
             //定义变量，标识是否选中
             boolean flag = false;//不选中
             //获取每一个角色ID
             Integer rid = (Integer) map.get("role_id");//id是主键，以主键作为map集合中key
             //内层循环遍历该员工拥有的角色列表
             for (Integer roleId : roleIds) {
                 //判断两个集合中是否存在角色ID相同
                 if(rid ==  role_id){
                     flag = true;//选中该角色
                     break;
                 }
             }
             //将状态保存在map集合中
             map.put("LAY_CHECKED",flag);//key必须是LAY_CHECKED,参考文档上就是它
         }

         return new DataGridViewResult(Long.valueOf(roleList.size()),roleList);
     }
}
