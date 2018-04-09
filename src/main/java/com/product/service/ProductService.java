package com.product.service;

import java.util.List;

import com.product.dto.Product;
import com.product.exception.ProductException;

/**
 * @author Manohar Perumal
 * 
 * Interface - Product Service
 *
 */
public interface ProductService {
	
	public List<Product> getAllProducts() throws ProductException;
	
	public List<Product> getParallelAllProducts() throws ProductException;
	
	public Product getProductName(Product product) throws ProductException;
	
	public Product getProductById(int productId) throws ProductException;

	public String saveProduct(Product product) throws ProductException;

}
