package com.target.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDetails {
	
	private String id;
	private String name;
	@JsonProperty("current_price")
	private Price currentPrice;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Price getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Price currentPrice) {
		this.currentPrice = currentPrice;
	}

}
