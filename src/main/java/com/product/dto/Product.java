package com.product.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Manohar Perumal
 * 
 * Contains product id and price object
 *
 */
@Document(collection="product")
public class Product {
	
	@Id
	private int id;
	
	private String name;
	
	@Field(value="current_price")
	private Price current_price;
	
	public Product(){
		super();
	}
	
	public Product(int id, double value, String currency_code) {
		this.id = id;
		this.current_price = new Price(value, currency_code);
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the current_price
	 */
	public Price getCurrent_price() {
		return current_price;
	}
	/**
	 * @param current_price the current_price to set
	 */
	public void setCurrent_price(Price current_price) {
		this.current_price = current_price;
	}
		
}
