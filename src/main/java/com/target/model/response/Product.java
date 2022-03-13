package com.target.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

	@JsonProperty("tcin")
	private String tcin;
	
	@JsonProperty("item")
	private Item item;

	public String getTcin() {
		return tcin;
	}

	public void setTcin(String tcin) {
		this.tcin = tcin;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	
}
