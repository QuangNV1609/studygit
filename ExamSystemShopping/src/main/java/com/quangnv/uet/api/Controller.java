package com.quangnv.uet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quangnv.uet.service.ProductService;

@RestController
public class Controller {
	@Autowired
	private ProductService productService;

	@GetMapping(value = "/test")
	public String home() {
		return null;
	}
}
