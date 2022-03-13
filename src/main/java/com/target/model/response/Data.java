package com.target.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
	
	//@JsonProperty("product")
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	

}
