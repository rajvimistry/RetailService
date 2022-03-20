package com.target.service.retailservice;


import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.target.excpetion.RecordNotFoundException;
import com.target.excpetion.ServiceException;
import com.target.excpetion.ValidationException;
import com.target.model.dto.Price;
import com.target.model.dto.ProductDetails;
import com.target.model.response.Data;
import com.target.model.response.Item;
import com.target.model.response.Product;
import com.target.model.response.ProductDescription;
import com.target.model.response.Response;
import com.target.service.productdetails.ProductDetailsRepositoryService;
import com.target.util.FeignServiceUtil;

@ExtendWith(MockitoExtension.class)
class RetailServiceImplTest {
	
	@Mock
	private FeignServiceUtil feignServiceUtil;
	
	@Mock
	private ProductDetailsRepositoryService repositoryService;
	
	@InjectMocks
	private RetailServiceImpl retailService;
	
	@Mock
	private ResponseEntity<Response> responseEntity;
	
	@Mock
	private Response response;
	
	@Mock
	private Data data;
	
	@Mock
	private Product product;
	
	@Mock
	private Item item;
	
	@Mock
	private ProductDescription description;
	
	@Mock
	private Price price;
	
	@Mock
	private ProductDetails productDetails;

	@Test
	void getProductAndPriceDetails() throws Exception {
		
		when(feignServiceUtil.getProductDetails("ABCD1234", 123456)).thenReturn(responseEntity);
		when(responseEntity.getBody()).thenReturn(response);
		when(response.getData()).thenReturn(data);
		when(data.getProduct()).thenReturn(product);
		when(product.getItem()).thenReturn(item);
		when(item.getProductDescription()).thenReturn(description);
		when(description.getTitle()).thenReturn("Test");
		when(repositoryService.getProductPriceById(123456)).thenReturn(price);
		
		ProductDetails productDetails = retailService.getProductAndPriceDetails("ABCD1234", 123456);
		
		Assertions.assertEquals("Test", productDetails.getName());
		Assertions.assertNotNull(productDetails.getCurrentPrice());
		
	}
	
	@Test
	void getProductAndPriceDetailsProductNotFound() {
		when(feignServiceUtil.getProductDetails("ABCD1234", 123456)).thenReturn(responseEntity);
		when(responseEntity.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);
		
		RecordNotFoundException e = Assertions.assertThrows(
				RecordNotFoundException.class,
				() -> retailService.getProductAndPriceDetails("ABCD1234", 123456),
				"Record Not Found Exception is supposed to thrown"
				);
		
	}
	
	@Test
	void getProductAndPriceDetailsInternalServerError() {
		when(feignServiceUtil.getProductDetails("ABCD1234", 123456)).thenReturn(responseEntity);
		when(responseEntity.getStatusCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);
		
		ServiceException e = Assertions.assertThrows(
				ServiceException.class,
				() -> retailService.getProductAndPriceDetails("ABCD1234", 123456),
				"ServiceException is supposed to thrown"
				);
		
	}
	
	@Test
	void getProductAndPriceDetailsUnknownException() {
		when(feignServiceUtil.getProductDetails("ABCD1234", 123456)).thenThrow(new RecordNotFoundException("Unknown"));
		
		Exception e = Assertions.assertThrows(
				Exception.class,
				() -> retailService.getProductAndPriceDetails("ABCD1234", 123456),
				"Exception is supposed to thrown"
				);
		
	}
	
	@Test
	void updatePriceInvalidPayload() {
		
		ValidationException e = Assertions.assertThrows(
				ValidationException.class,
				() -> retailService.updatePrice(1234, productDetails)
				);
		
	}
	
	@Test
	void updatePriceInvalidPayloadCurrencyCodeNull() {
		
		when(productDetails.getId()).thenReturn(1234);
		when(productDetails.getCurrentPrice()).thenReturn(price);
		when(price.getCurrencyCode()).thenReturn(null);
		ValidationException e = Assertions.assertThrows(
				ValidationException.class,
				() -> retailService.updatePrice(1234, productDetails)
				);
		
	}
	
	@Test
	void updatePriceInvalidPayloadCurrencyCodeEmpty() {
		
		when(productDetails.getId()).thenReturn(1234);
		when(productDetails.getCurrentPrice()).thenReturn(price);
		when(price.getCurrencyCode()).thenReturn("");
		ValidationException e = Assertions.assertThrows(
				ValidationException.class,
				() -> retailService.updatePrice(1234, productDetails)
				);
		
	}
	
	@Test
	void updatePriceInvalidPayloadCurrencyPriceNull() {
		
		when(productDetails.getId()).thenReturn(1234);
		when(productDetails.getCurrentPrice()).thenReturn(null);
		ValidationException e = Assertions.assertThrows(
				ValidationException.class,
				() -> retailService.updatePrice(1234, productDetails)
				);
		
	}
	
	@Test
	void updatePriceSuccess() {
		
		when(productDetails.getId()).thenReturn(1234);
		when(productDetails.getCurrentPrice()).thenReturn(price);
		when(price.getCurrencyCode()).thenReturn("USD");
		when(repositoryService.updatePriceDetails(1234, price)).thenReturn(price);
		Assertions.assertNotNull(retailService.updatePrice(1234, productDetails));
		
	}
	
	@Test
	void updatePriceRecordNotFound() {
		when(productDetails.getId()).thenReturn(1234);
		when(productDetails.getCurrentPrice()).thenReturn(price);
		when(price.getCurrencyCode()).thenReturn("USD");
		when(repositoryService.updatePriceDetails(1234, price)).thenReturn(null);
		
		RecordNotFoundException e = Assertions.assertThrows(
				RecordNotFoundException.class,
				() -> retailService.updatePrice(1234, productDetails),
				"Record Not Found Exception is supposed to thrown"
				);
	}

}
