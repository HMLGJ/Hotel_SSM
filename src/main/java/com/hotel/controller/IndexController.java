package com.hotel.controller;

import com.hotel.entity.Floor;
import com.hotel.entity.Room;
import com.hotel.entity.RoomType;
import com.hotel.service.FloorService;
import com.hotel.service.RoomService;
import com.hotel.service.RoomTypeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Resource
    private RoomTypeService roomTypeService;
    @Resource
    private FloorService floorService;
    @Resource
    private RoomService roomService;

    @RequestMapping("/home")
    public String index(Model model){
        //调用查询房型列表的方法(偷懒了直接调用，SQL执行了两次，一条是统计，一条是查询分页的方法，应该单独写一个方法的)
        List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(null);
        //调用查询所有楼层列表的方法
        List<Floor> floorList = floorService.findFloorList(null);
        //调用查询每个楼层的房间列表
        //本来是在roomDao、service与serviceImpl地方加了一个size,然后在这里赋值size为4,查询4条数据
        List<Room> roomList = roomService.findRoomListByFloorId();
        //将数据放到模型中
        model.addAttribute("roomTypeList",roomTypeList);
        model.addAttribute("floorList",floorList);
        model.addAttribute("roomList",roomList);
        //return "forward:/home.jsp";
        return "forward:/home.jsp";
    }

}
