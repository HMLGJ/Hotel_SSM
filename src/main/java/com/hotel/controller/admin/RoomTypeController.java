package com.hotel.controller.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hotel.entity.RoomType;
import com.hotel.utils.DataGridViewResult;
import com.hotel.utils.UUIDUtils;
import com.hotel.vo.RoomTypeVo;
import com.alibaba.fastjson.JSON;
import com.hotel.utils.SystemConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.service.RoomTypeService;

@RestController
@RequestMapping("/admin/roomType")
public class RoomTypeController {
	
	 @Resource
	 private RoomTypeService roomTypeService;
	 
	 //查询房型列表
	 @RequestMapping("/list")
	 public DataGridViewResult list(RoomTypeVo roomTypeVo){
		 //设置分页信息
		 PageHelper.startPage(roomTypeVo.getPage(),roomTypeVo.getLimit());
		 //调用查询的方法
		 List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(roomTypeVo);
		 //创建分页对象
		 PageInfo<RoomType> pageInfo = new PageInfo<RoomType>(roomTypeList);
		 //返回数据
		 return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }
	 
	 //文件上传功能、这次是上传到本地的，去到公司的话要上传到阿里云的。
	 @RequestMapping("/uploadFile")
	 public String uploadFile(MultipartFile attach) {
		 //创建Map集合保存返回的数据
		 Map<String,Object> map = new HashMap<String,Object>();
		 //判断是否有选中文件
		 if(!attach.isEmpty()) {
			 String path = "D:/project/hotel/upload/";
			 //获取源文件的名称
			 String oldFileNmae = attach.getOriginalFilename();
			 //获取文件的后缀名
			 String extension = FilenameUtils.getExtension(oldFileNmae);
			 //重命名旧文件
			 String newFileName = UUIDUtils.randomUUID()+"."+extension;
			 //为了解决同一个文件夹下文件过多的问题，使用日期作为文件夹管理
			 String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			 //组装最终的文件名
			 String finalName = datePath + "/" + newFileName;
			 //创建文件对象
			 //参数1：文件上传的地址 参数2：文件名称
			 File dest = new File(path,finalName);
			 //判断该文件夹是否存在，不存在则创建文件夹
			 if(!dest.getParentFile().exists()){
				 dest.getParentFile().mkdirs();//创建文件夹
			 	}
		 	
		 	try {
		 		//进行文件上传
		 		attach.transferTo(dest);
		 		map.put("code",0);//状态码
		 		map.put("msg","上传成功");//执行消息
		 		Map<String,Object> dataMap = new HashMap<String,Object>();
		 		dataMap.put("src","/hotel/show/"+finalName);
		 		map.put("data",dataMap);//文件数据
		 		map.put("imagePath",finalName);//隐藏域的值，只保留日期文件夹+重命名后的文件名
		 		} catch (IOException e) {
		 			e.printStackTrace();
		 		}
		 	}
			return JSON.toJSONString(map);
	 }
	 
	 	//添加房型
	    @RequestMapping("/addRoomType")
	    public String addRoomType(RoomType roomType){
	        Map<String,Object> map = new HashMap<String,Object>();
	        if(roomTypeService.addRoomType(roomType)>0){
	            map.put(SystemConstant.SUCCESS,true);//成功
	            map.put(SystemConstant.MESSAGE,"添加成功");
	        }else{
	            map.put(SystemConstant.SUCCESS,false);//失败
	            map.put(SystemConstant.MESSAGE,"添加失败");
	        }
	        return JSON.toJSONString(map);
	    }


	    // 修改房型
	    @RequestMapping("/updateRoomType")
	    public String updateRoomType(RoomType roomType){
	        Map<String,Object> map = new HashMap<String,Object>();
	        if(roomTypeService.updateRoomType(roomType)>0){
	            map.put(SystemConstant.SUCCESS,true);//成功
	            map.put(SystemConstant.MESSAGE,"修改成功");
	        }else{
	            map.put(SystemConstant.SUCCESS,false);//失败
	            map.put(SystemConstant.MESSAGE,"修改失败");
	        }
	        return JSON.toJSONString(map);
	    }

	    // 删除房型
	    @RequestMapping("/deleteById")
	    public String deleteById(Integer roomType_id){
	        Map<String,Object> map = new HashMap<String,Object>();
	        if(roomTypeService.deleteById(roomType_id)>0){
	            map.put(SystemConstant.SUCCESS,true);//成功
	            map.put(SystemConstant.MESSAGE,"删除成功");
	        }else{
	            map.put(SystemConstant.SUCCESS,false);//失败
	            map.put(SystemConstant.MESSAGE,"删除失败");
	        }
	        return JSON.toJSONString(map);
	    }

	    // 查询所有房间类型
	    @RequestMapping("/findAll")
	    public String findAll(){
	        return JSON.toJSONString(roomTypeService.findRoomTypeList(null));
	    }
}
