package com.target.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.target.model.response.Data;
import com.target.model.response.Response;

import org.springframework.stereotype.Service;

import com.target.driver.FeignServiceUtil;
import com.target.dto.ProductDetails;


@Service
public class RetailServiceImpl implements RetailService{
	
	@Autowired
	private FeignServiceUtil feignServiceUtil;
	
	Logger log = LogManager.getLogger(RetailServiceImpl.class);

	@Override
	public ProductDetails getProductAndPriceDetails(final String key, final String productId) {
		
		Response response = feignServiceUtil.getProductDetails(key, productId);
		
		ProductDetails productDetails = new ProductDetails();
		productDetails.setId(productId);
		
		if(response != null && response.getData()!= null && response.getData().getProduct() != null && 
				response.getData().getProduct().getItem() != null && response.getData().getProduct().getItem().getProductDescription() != null
					&& response.getData().getProduct().getItem().getProductDescription().getTitle() != null) {
			
			productDetails.setName(response.getData().getProduct().getItem().getProductDescription().getTitle());
			
		}
		
		return productDetails;
		
	}

	@Override
	public void updatePrice() {
		// TODO Auto-generated method stub
		
	}
}
