package com.target.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Price {
	
	private double value;
	
	@JsonProperty("currency_code")
	private String currencyCode;
	
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}

}
