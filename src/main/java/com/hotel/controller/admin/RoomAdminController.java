package com.hotel.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.entity.Room;
import com.hotel.service.RoomService;
import com.hotel.utils.DataGridViewResult;
import com.hotel.utils.SystemConstant;
import com.hotel.vo.RoomVo;

@RestController
@RequestMapping("/admin/room")
public class RoomAdminController {
	
	@Resource
	private RoomService roomService;
	
	//查询房间列表
	@RequestMapping("/list")
    public DataGridViewResult list(RoomVo roomVo){
        //设置分页信息
        PageHelper.startPage(roomVo.getPage(),roomVo.getLimit());
        //调用查询角色列表的方法
        List<Room> roomList = roomService.findRoomListByPage(roomVo);
        //创建分页信息对象
        PageInfo<Room> pageInfo = new PageInfo<Room>(roomList);
        //返回数据(参数1：总数量，参数2：角色列表)
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }
	
	//添加房型
    @RequestMapping("/addRoom")
    public String addRoom(Room room){
        Map<String,Object> map = new HashMap<String,Object>();
        if(roomService.addRoom(room)>0){
            map.put(SystemConstant.SUCCESS,true);//成功
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);//失败
            map.put(SystemConstant.MESSAGE,"添加失败");
        }
        return JSON.toJSONString(map);
    }
    
    //修改房型
    @RequestMapping("/updateRoom")
    public String updateRoom(Room room){
        Map<String,Object> map = new HashMap<String,Object>();
        if(roomService.updateRoom(room)>0){
            map.put(SystemConstant.SUCCESS,true);//成功
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);//失败
            map.put(SystemConstant.MESSAGE,"修改失败");
        }
        return JSON.toJSONString(map);
    }
    
    //删除房型
    @RequestMapping("/deleteById")
    public String deleteById(Integer room_id){
        Map<String,Object> map = new HashMap<String,Object>();
        if(roomService.deleteById(room_id)>0){
            map.put(SystemConstant.SUCCESS,true);//成功
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);//失败
            map.put(SystemConstant.MESSAGE,"删除失败");
        }
        return JSON.toJSONString(map);
    }
	
}
