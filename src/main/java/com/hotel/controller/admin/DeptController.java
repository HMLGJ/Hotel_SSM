package com.hotel.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hotel.utils.SystemConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.entity.Dept;
import com.hotel.service.DeptService;
import com.hotel.service.HotelAdminService;
import com.hotel.utils.DataGridViewResult;
import com.hotel.vo.DeptVo;

@RestController
@RequestMapping("/admin/dept")
public class DeptController {
	@Resource
	private DeptService deptService;
	@Resource
	private HotelAdminService hotelAdminService;
	
	@RequestMapping("/list")
	public DataGridViewResult list(DeptVo deptVo) {
		//设置分页信息(必须写在调用分页查询的方法之前，否则会失效)
		PageHelper.startPage(deptVo.getPage(),deptVo.getLimit());
		//调用分页查询的方法
		List<Dept> deptList = deptService.findDeptListByPage(deptVo);
		//创建分页对象
		PageInfo<Dept> pageInfo = new PageInfo<Dept>(deptList);
		//返回封装数据表格类DataGridViewResult
		return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());	
	}
	
	//添加部门
    @RequestMapping("/addDept")
    public String addDept(Dept dept){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用添加部门的方法
        if(deptService.addDept(dept)>0){
            map.put(SystemConstant.SUCCESS,true);//成功
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);//失败
            map.put(SystemConstant.MESSAGE,"添加失败");
        }
        //将map集合以JSON格式返回
        return JSON.toJSONString(map);
    }

    // 修改部门
    @RequestMapping("/updateDept")
    public String updateDept(Dept dept){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用修改部门的方法
        if(deptService.updateDept(dept)>0){
            map.put(SystemConstant.SUCCESS,true);//成功
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);//失败
            map.put(SystemConstant.MESSAGE,"修改失败");
        }
        //将map集合以JSON格式返回
        return JSON.toJSONString(map);
    }


    //检查该部门下是否存在员工信息
	 @RequestMapping("/checkDeptHasEmployee") 
	 public String checkDeptHasEmployee(Integer dept_id){ 
		 Map<String,Object> map = new HashMap<String,Object>();
		//调用根据部门编号查询员工数量的方法
		 if(hotelAdminService.getHotelAdminCountByDeptId(dept_id)>0){
			map.put(SystemConstant.EXIST,true);//存在
		 	map.put(SystemConstant.MESSAGE,"该部门存在员工信息，无法删除!");
		 	}else{
		 	map.put(SystemConstant.EXIST,false);//不存在 
		 }
		 //System.out.println(hotelAdminService.getHotelAdminCountByDeptId(dept_id));
		 return JSON.toJSONString(map);
	 }
	

    //删除部门
    @RequestMapping("/deleteById")
    public String deleteById(Integer dept_id){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用删除部门的方法
        if(deptService.deleteById(dept_id)>0){
            map.put(SystemConstant.SUCCESS,true);//成功
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);//失败
            map.put(SystemConstant.MESSAGE,"删除失败");
        }
        return JSON.toJSONString(map);
    }

    // 查询所有部门
    @RequestMapping("/deptList")
    public String deptList(){
        //调用查询所有部门信息的方法并返回到页面,用于员工管理的部门下拉
        return JSON.toJSONString(deptService.findDeptList());
    }

}
