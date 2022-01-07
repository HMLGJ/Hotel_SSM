package com.hotel.dao;

import com.hotel.entity.Checkout;

public interface CheckoutDao {
	
	//添加退房记录
    int addCheckout(Checkout checkout);
}
