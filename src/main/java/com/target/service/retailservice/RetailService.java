package com.target.service.retailservice;

import com.target.excpetion.RecordNotFoundException;
import com.target.excpetion.ServiceException;
import com.target.model.dto.ProductDetails;

public interface RetailService {
	
	public ProductDetails getProductAndPriceDetails(String key, int productId) throws RecordNotFoundException, ServiceException;
	
	public ProductDetails updatePrice(int productId, ProductDetails productDetails);

}
