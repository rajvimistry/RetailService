package com.target.service.productdetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.dao.ProductDetailsRepository;
import com.target.model.dto.Price;
import com.target.model.dto.ProductPrice;

@Service
public class ProductDetailsRepositoryService{
	
	Logger log = LogManager.getLogger(ProductDetailsRepositoryService.class);
	
	@Autowired
	private ProductDetailsRepository repository;
	
//	@PostConstruct
//	public void savePriceDetails() {
//		List<ProductPrice> list = new ArrayList<>();
//		
//		list.add(new ProductPrice(13860428,13.49,"USD"));
//		list.add(new ProductPrice(54456119,40.49,"USD"));
//		list.add(new ProductPrice(13264003,10.11,"USD"));
//		list.add(new ProductPrice(12954218,32.99,"USD"));
//		
//		repository.saveAll(list);
//		
//	}
	
	public Price getProductPriceById(int productId) {
		
		Price price = null;
		Optional<ProductPrice> productPrice = repository.findById(productId);
		
		if(!productPrice.isEmpty()) {
			price = getProductPrice(productPrice.get());
		}
		
		return price;
		
	}
	
	private Price getProductPrice(ProductPrice productPrice) {
		
		Price price = new Price();
		price.setCurrencyCode(productPrice.getCurrencyCode());
		price.setValue(productPrice.getPrice());
		return price;
		
	}
	
	public Price updatePriceDetails(int productId, Price price) {
		
		Optional<ProductPrice> productPrice = repository.findById(productId);
		
		if(productPrice.isPresent()) {
			ProductPrice newPrice = productPrice.get();
			newPrice.setPrice(price.getValue());	
			newPrice.setCurrencyCode(price.getCurrencyCode());
			repository.save(newPrice);
			return getProductPrice(newPrice);
			
		}else {
			return null;
		}
		
	}

}