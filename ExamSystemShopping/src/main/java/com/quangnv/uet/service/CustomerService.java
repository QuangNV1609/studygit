package com.quangnv.uet.service;

import com.quangnv.uet.dto.extend.CustomerDto;
import com.quangnv.uet.dto.extend.ImageDto;

public interface CustomerService {
	public CustomerDto getCustomerByUserId(String userId);
	
	public CustomerDto saveCustomerByUserId(String userId, CustomerDto customerDto);
	
	public ImageDto getCustomerImageByCustomerId(String customerId);
	
}
