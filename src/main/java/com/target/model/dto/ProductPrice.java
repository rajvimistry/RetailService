package com.target.model.dto;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("Product_Price")
public class ProductPrice {
	
	@PrimaryKey("product_id")
	private int productId;
	
	private double price;
	
	@Column("currency_code")
	private String currencyCode;
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public ProductPrice(int productId, double price, String currencyCode) {
		this.productId = productId;
		this.price = price;
		this.currencyCode = currencyCode;
	}
}
