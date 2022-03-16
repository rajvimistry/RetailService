package com.target.service.productdetails;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.target.driver.ProductDetailsRepository;
import com.target.model.dto.Price;
import com.target.model.dto.ProductPrice;

@ExtendWith(MockitoExtension.class)
class ProductDetailsRepositoryServiceTest {
	
	@Mock
	private ProductDetailsRepository repository;
	
	@InjectMocks
	private ProductDetailsRepositoryService productRepositoryService;
	
	@Mock
	private Price price;
	
	
	private Optional<ProductPrice> productPrice = null;
	
	@BeforeEach
	public void beforeEachTest() {
		ProductPrice newProductPrice = new ProductPrice(12345,10.99,"USD");
		productPrice = Optional.of(newProductPrice);
    }

	@Test
	void getProductByIdSuccess() {
		
		when(repository.findById(12345)).thenReturn(productPrice);
		
		Assertions.assertNotNull(productRepositoryService.getProductPriceById(12345));
	}
	
	@Test
	void updatePriceDetails() {
		when(repository.findById(12345)).thenReturn(productPrice);
		when(price.getValue()).thenReturn(12.00);
		when(price.getCurrencyCode()).thenReturn("CAD");
		
		Price newPrice = productRepositoryService.updatePriceDetails(12345, price);
		
		Assertions.assertEquals("CAD", newPrice.getCurrencyCode());
		Assertions.assertEquals(12.00, newPrice.getValue());
		
	}
	
	@Test
	void updatePriceDetailsWhenProductNotFound() {
		when(repository.findById(12345)).thenReturn(Optional.empty());
		
		Assertions.assertNull(productRepositoryService.updatePriceDetails(12345, price));
		
	}

}
