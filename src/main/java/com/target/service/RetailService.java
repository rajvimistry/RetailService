package com.target.service;

import com.target.dto.ProductDetails;
import com.target.excpetion.RecordNotFoundException;

public interface RetailService {
	
	public ProductDetails getProductAndPriceDetails(String key, String productId) throws RecordNotFoundException;
	
	public void updatePrice();

}
