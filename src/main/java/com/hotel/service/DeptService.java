package com.hotel.service;

import java.util.List;

import com.hotel.entity.Dept;
import com.hotel.vo.DeptVo;

public interface DeptService {
	//部门查询列表
	List<Dept> findDeptListByPage(DeptVo deptVo);
	
	//添加部门
	int addDept(Dept dept);
		
	//修改部门
	int updateDept(Dept dept);

	//删除部门
	int deleteById(Integer dept_id);


	//查询所有部门
	List<Dept> findDeptList();
}
