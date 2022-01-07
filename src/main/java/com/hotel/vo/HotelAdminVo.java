package com.hotel.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.hotel.entity.HotelAdmin;

public class HotelAdminVo extends HotelAdmin{
	//当前页码
    private Integer page;
    //每页显示数量
    private Integer limit;
    //开始日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    //结束日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    
    
}
