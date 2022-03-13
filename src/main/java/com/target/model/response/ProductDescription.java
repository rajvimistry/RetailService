package com.target.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDescription {
	
	private String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDownStreamDescrition() {
		return downStreamDescrition;
	}

	public void setDownStreamDescrition(String downStreamDescrition) {
		this.downStreamDescrition = downStreamDescrition;
	}

	@JsonProperty("downstream_description")
	private String downStreamDescrition;

}
