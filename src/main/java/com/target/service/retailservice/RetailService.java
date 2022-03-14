package com.target.service.retailservice;

import com.target.excpetion.RecordNotFoundException;
import com.target.model.dto.ProductDetails;

public interface RetailService {
	
	public ProductDetails getProductAndPriceDetails(String key, int productId) throws RecordNotFoundException;
	
	public ProductDetails updatePrice(int productId, ProductDetails productDetails);

}
