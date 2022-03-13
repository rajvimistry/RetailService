package com.target.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class RetailContoller {
	
	@GetMapping("/{id}")
	public void getProductAndPriceDetails(String productId) {
		
	}
	
	@PutMapping("/{id}")
	public void updateProductAndPriceDetails(String productId) {
		
	}

}
