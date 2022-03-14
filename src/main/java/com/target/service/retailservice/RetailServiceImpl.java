package com.target.service.retailservice;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.target.model.dto.ProductDetails;
import com.target.model.response.Response;
import com.target.service.productdetails.ProductDetailsRepositoryService;
import com.target.util.FeignServiceUtil;

import org.springframework.stereotype.Service;

import com.target.excpetion.RecordNotFoundException;


@Service
public class RetailServiceImpl implements RetailService{
	
	@Autowired
	private FeignServiceUtil feignServiceUtil;
	
	@Autowired
	private ProductDetailsRepositoryService repositoryService;
	
	Logger log = LogManager.getLogger(RetailServiceImpl.class);

	@Override
	public ProductDetails getProductAndPriceDetails(final String key, final String productId) throws RecordNotFoundException {
		
		try {
				Response response = feignServiceUtil.getProductDetails(key, productId);
			
				ProductDetails productDetails = new ProductDetails();
				productDetails.setId(productId);
			
				if(response != null && response.getData()!= null && response.getData().getProduct() != null && 
						response.getData().getProduct().getItem()!= null && 
						response.getData().getProduct().getItem().getProductDescription() != null && 
						response.getData().getProduct().getItem().getProductDescription().getTitle() != null) {

						productDetails.setName(response.getData().getProduct().getItem().getProductDescription().getTitle());
						productDetails.setCurrentPrice(repositoryService.getProductPriceById(productId));
				}
			
				return productDetails;
				
		} catch(Exception e) {
			
			if(e.getMessage().contains("No product found with tcin")) {
				throw new RecordNotFoundException("Product "+ productId +" Not Found");
			}
			
			throw e;
		}
		
	}

	@Override
	public void updatePrice() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
