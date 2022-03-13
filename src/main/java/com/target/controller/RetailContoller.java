package com.target.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetailContoller {
	
	@GetMapping("/products/{id}")
	public void getProductAndPriceDetails(String productId) {
		
	}
	
	@PutMapping("/products/{id}")
	public void updateProductAndPriceDetails(String productId) {
		
	}

}
