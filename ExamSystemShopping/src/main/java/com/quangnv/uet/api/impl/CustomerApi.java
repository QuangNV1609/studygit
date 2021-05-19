package com.quangnv.uet.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quangnv.uet.api.AbstractRestHandler;
import com.quangnv.uet.dto.extend.CustomerDto;
import com.quangnv.uet.dto.extend.ImageDto;
import com.quangnv.uet.service.CustomerService;

@RestController
@RequestMapping(value = "/customer")
@CrossOrigin(originPatterns = "**")
public class CustomerApi extends AbstractRestHandler{
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public ResponseEntity<CustomerDto> getByUserId(@RequestParam("id") String userId) {
		CustomerDto customerDto = customerService.getCustomerByUserId(userId);
		return  new ResponseEntity<CustomerDto>(customerDto, HttpStatus.OK);
	}
	
	@PostMapping(value = "/{userId}")
//	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public ResponseEntity<CustomerDto> saveByUserId(@PathVariable("userId") String userId, @RequestBody CustomerDto customerDto){
		customerDto = customerService.saveCustomerByUserId(userId, customerDto);
		return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/image/{customerId}")
	public ResponseEntity<ImageDto> getCustomerImageByCustomerId(@PathVariable("customerId") String customerId){
		ImageDto imageDto = customerService.getCustomerImageByCustomerId(customerId);
		return new ResponseEntity<ImageDto>(imageDto, HttpStatus.OK);
	}
}
