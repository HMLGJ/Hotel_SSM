package com.hotel.controller.admin;

import com.alibaba.fastjson.JSON;
import com.hotel.entity.Checkout;
import com.hotel.entity.HotelAdmin;
import com.hotel.service.CheckoutService;
import com.hotel.utils.SystemConstant;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/checkout")
public class CheckoutAdminController {
    
	@Resource
    private CheckoutService checkoutService;

    //办理退房
    @RequestMapping("/addCheckout")
    public String addCheckin(Checkout checkout, HttpSession session){
        Map<String,Object> map = new HashMap<String,Object>();
        //获取当前登录用户
        HotelAdmin hotelAdmin = (HotelAdmin) session.getAttribute(SystemConstant.LOGINUSER);
        //创建人
        checkout.setCheckOut_createdBy(hotelAdmin.getHotelAdmin_id());
        System.out.println(hotelAdmin.getHotelAdmin_id());
        //调用添加入住信息的方法
        if(checkoutService.addCheckout(checkout)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"退房成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"退房失败");
        }
        return JSON.toJSONString(map);
    }
}
