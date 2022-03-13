package com.target.service;

import com.target.dto.ProductDetails;

public interface RetailService {
	
	public ProductDetails getProductAndPriceDetails();
	
	public void updatePrice();

}
