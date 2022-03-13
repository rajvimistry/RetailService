package com.target.service;

import com.target.dto.ProductDetails;

public interface RetailService {
	
	public ProductDetails getProductAndPriceDetails(String key, String productId);
	
	public void updatePrice();

}
