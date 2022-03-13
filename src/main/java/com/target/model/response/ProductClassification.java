package com.target.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductClassification {
	
	@JsonProperty("product_type_name")
	private String productTypeName;
	
	@JsonProperty("merchandise_type_name")
	private String merchandiseTypeName;

	
	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getMerchandiseTypeName() {
		return merchandiseTypeName;
	}

	public void setMerchandiseTypeName(String merchandiseTypeName) {
		this.merchandiseTypeName = merchandiseTypeName;
	}

}
