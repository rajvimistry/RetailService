package com.target.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Enrichment {
	
	//@JsonProperty("images")
	private Images images;

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}
	

}
