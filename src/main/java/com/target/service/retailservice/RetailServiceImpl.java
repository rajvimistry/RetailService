package com.target.service.retailservice;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.target.model.dto.Price;
import com.target.model.dto.ProductDetails;
import com.target.model.response.Response;
import com.target.service.productdetails.ProductDetailsRepositoryService;
import com.target.util.FeignServiceUtil;

import org.springframework.stereotype.Service;

import com.target.excpetion.RecordNotFoundException;
import com.target.excpetion.ServiceException;
import com.target.excpetion.ValidationException;


@Service
public class RetailServiceImpl implements RetailService{
	
	@Autowired
	private FeignServiceUtil feignServiceUtil;
	
	@Autowired
	private ProductDetailsRepositoryService repositoryService;
	
	Logger log = LogManager.getLogger(RetailServiceImpl.class);

	@Override
	public ProductDetails getProductAndPriceDetails(final String key, final int productId) throws RecordNotFoundException, ServiceException {
		
		ResponseEntity<Response> response = feignServiceUtil.getProductDetails(key, productId);
			
		return createProductDetailsResponse(response,productId);			
				
	}
	
	private ProductDetails createProductDetailsResponse(ResponseEntity<Response> response, int productId) throws RecordNotFoundException, ServiceException {
		
		if(HttpStatus.NOT_FOUND == response.getStatusCode()) {
			log.error("Product Not Found");
			throw new RecordNotFoundException("Product "+ productId +" Not Found");
		}
		
		if(HttpStatus.INTERNAL_SERVER_ERROR == response.getStatusCode()) {
			throw new ServiceException("Please Try After Sometime!");
		}
		
		ProductDetails productDetails = null;
	
		if(response != null && response.getBody() != null && response.getBody().getData()!= null && 
				response.getBody().getData().getProduct() != null && 
				response.getBody().getData().getProduct().getItem()!= null && 
				response.getBody().getData().getProduct().getItem().getProductDescription() != null && 
				response.getBody().getData().getProduct().getItem().getProductDescription().getTitle() != null) {
			
				productDetails = new ProductDetails();
				productDetails.setId(productId);
				productDetails.setName(response.getBody().getData().getProduct().getItem().getProductDescription().getTitle());
				productDetails.setCurrentPrice(repositoryService.getProductPriceById(productId));
		}
		
		return productDetails;
		
	}

	@Override
	public ProductDetails updatePrice(int productId, ProductDetails productDetails) {
		
		validateRequest(productId,productDetails);
		Price price = productDetails.getCurrentPrice();
		price = repositoryService.updatePriceDetails(productId, price);
		
		if(price != null) {
			return productDetails;
		}else {
			throw new RecordNotFoundException("Product "+ productId +" Not Found");
		}
		
	}
	
	private void validateRequest(int productId, ProductDetails productDetails) {

		if(productId != productDetails.getId()) {
			throw new ValidationException("Invalid Payload");
		} else if(productDetails.getCurrentPrice() == null) {
			throw new ValidationException("Invalid Payload");
		} else if(productDetails.getCurrentPrice().getCurrencyCode() == null ||
				productDetails.getCurrentPrice().getCurrencyCode().isEmpty()) {
			throw new ValidationException("Invalid Payload");
		}
		
	}
}
