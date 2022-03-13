package com.target.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.target.dto.ProductDetails;
import com.target.excpetion.RecordNotFoundException;
import com.target.service.RetailService;

@RestController
@RequestMapping("/products")
public class RetailContoller {
	
	@Autowired
	RetailService retailService;
	
	private final String key = "3yUxt7WltYG7MFKPp7uyELi1K40ad2ys";
	
	@GetMapping
	public String status() {
		return "UP";
	}
	
	@GetMapping("/{id}")
	public ProductDetails getProductAndPriceDetails(@PathVariable("id") String productId) throws RecordNotFoundException {
		return retailService.getProductAndPriceDetails(key, productId);
	}
	
	@PutMapping("/{id}")
	public void updateProductAndPriceDetails(String productId) {
		
	}

}
