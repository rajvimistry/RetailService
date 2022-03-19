package com.target.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.target.excpetion.RecordNotFoundException;
import com.target.model.dto.ProductDetails;
import com.target.service.retailservice.RetailService;

@RestController
@RequestMapping("/products")
public class RetailController {
	
	@Autowired
	private RetailService retailService;
	
	private final String key = "3yUxt7WltYG7MFKPp7uyELi1K40ad2ys";
	
	@GetMapping("/status")
	public String status() {
		return "UP";
	}
	
	@GetMapping("/{id}")
	public ProductDetails getProductAndPriceDetails(@PathVariable("id") int productId) throws RecordNotFoundException {
		return retailService.getProductAndPriceDetails(key, productId);
	}
	
	@PutMapping("/{id}")
	public ProductDetails updatePriceDetails(@PathVariable("id")int productId, @RequestBody ProductDetails productDetails ) {
		return retailService.updatePrice(productId, productDetails);
	}

}
