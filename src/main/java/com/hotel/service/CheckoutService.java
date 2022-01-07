package com.hotel.service;

import com.hotel.entity.Checkout;

public interface CheckoutService {
	
	//添加退房记录
    int addCheckout(Checkout checkout);
}
