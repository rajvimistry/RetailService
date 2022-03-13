package com.target.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
	
	@JsonProperty("product_description")
	private ProductDescription productDescription;
	
	@JsonProperty("enrichment")
	private Enrichment enrichment;
	
	@JsonProperty("product_classification")
	private ProductClassification productClassification;
	
	@JsonProperty("primary_brand")
	private PrimaryBrand primaryBrand;

	public ProductDescription getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(ProductDescription productDescription) {
		this.productDescription = productDescription;
	}

	public Enrichment getEnrichment() {
		return enrichment;
	}

	public void setEnrichment(Enrichment enrichment) {
		this.enrichment = enrichment;
	}

	public ProductClassification getProductClassification() {
		return productClassification;
	}

	public void setProductClassification(ProductClassification productClassification) {
		this.productClassification = productClassification;
	}

	public PrimaryBrand getPrimaryBrand() {
		return primaryBrand;
	}

	public void setPrimaryBrand(PrimaryBrand primaryBrand) {
		this.primaryBrand = primaryBrand;
	}
	

}
