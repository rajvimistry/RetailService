package com.target.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Images {
	
	@JsonProperty("primary_image_url")
	private String primaryImageUrl;

	public String getPrimaryImageUrl() {
		return primaryImageUrl;
	}

	public void setPrimaryImageUrl(String primaryImageUrl) {
		this.primaryImageUrl = primaryImageUrl;
	}
}
