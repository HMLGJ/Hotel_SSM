package com.hotel.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.dao.DeptDao;
import com.hotel.entity.Dept;
import com.hotel.service.DeptService;
import com.hotel.vo.DeptVo;

@Service
@Transactional
public class DeptServiceImpl implements DeptService{
	@Resource
	private DeptDao deptdao;
	
	@Override
	public List<Dept> findDeptListByPage(DeptVo deptVo) {
		return deptdao.findDeptListByPage(deptVo);
	}

	@Override
	public int addDept(Dept dept) {
		//保存创建时间
		dept.setDept_createDate(new Date());
		return deptdao.addDept(dept);
	}

	@Override
	public int updateDept(Dept dept) {
		return deptdao.updateDept(dept);
	}

	@Override
	public int deleteById(Integer dept_id) {
		return deptdao.deleteById(dept_id);
	}

	@Override
	public List<Dept> findDeptList() {
		return deptdao.findDeptList();
	}

}
