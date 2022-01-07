package com.hotel.controller;

import com.hotel.entity.Room;
import com.hotel.entity.RoomType;
import com.hotel.service.RoomService;
import com.hotel.service.RoomTypeService;
import com.hotel.vo.RoomVo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import javax.annotation.Resource;

@Controller
@RequestMapping("/room")
public class RoomController {

	@Resource
    private RoomService roomService;

	@Resource
    private RoomTypeService roomTypeService;

    /**
     * 查询房间详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/{room_id}")
    public String detail(@PathVariable Integer room_id, Model model){
        //调用查询房间详情的方法
        Room room = roomService.findById(room_id);
        //System.out.println(roomService.findById(room_id));
        //将数据放到模型中
        model.addAttribute("room",room);
        return "detail";
    }

    /**
     * 查询全部房间列表
     * @param model
     * @return
     */
    @RequestMapping("/hotelList.html")
    public String list(Model model){
        //调用查询所有房型列表的方法
        List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(null);
        //创建查询条件类
        RoomVo roomVo = new RoomVo();
        roomVo.setRoom_status(3);//设置只能查看可预订的房间
        //查询房间列表
        List<Room> roomList = roomService.findRoomListByPage(roomVo);
        //将数据放到模型中
        model.addAttribute("roomTypeList",roomTypeList);
        model.addAttribute("roomList",roomList);
        return "hotelList";
    }


    /**
     * 根据房型查询房间列表
     * @param model
     * @return
     */
    @RequestMapping("/list/{roomType_id}")
    public String list(@PathVariable Integer roomType_id,Model model){
        //调用查询所有房型列表的方法
        List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(null);
        //创建查询条件类
        RoomVo roomVo = new RoomVo();
        roomVo.setRoom_roomtypeid(roomType_id);//房型ID
        roomVo.setRoom_status(3);//设置只能查看可预订的房间
        //查询房间列表
        List<Room> roomList = roomService.findRoomListByPage(roomVo);
        //将数据放到模型中
        model.addAttribute("roomTypeList",roomTypeList);
        model.addAttribute("roomList",roomList);
        model.addAttribute("typeId",roomType_id);//将当前选中的房型ID保存到模型中，目的是在页面中回显选中的文本(改变选中的颜色)
        return "hotelList";
    }


}
