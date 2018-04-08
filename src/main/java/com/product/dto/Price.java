package com.product.dto;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Manohar Perumal
 * 
 * Contains price amount and currency code
 *
 */
public class Price {
	
	@Field(value="value")
	private double value;
	
	@Field(value="currency_code")
	private String currency_code;
	
	
	public Price() {
		super();
	}
	
	public Price(double value, String currency_code) {
		this.value = value;
		this.currency_code = currency_code;
	}
	
	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}
	/**
	 * @return the currency_code
	 */
	public String getCurrency_code() {
		return currency_code;
	}
	/**
	 * @param currency_code the currency_code to set
	 */
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	
}
