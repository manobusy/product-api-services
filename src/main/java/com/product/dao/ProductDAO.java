package com.product.dao;

import java.util.List;

import com.product.dto.Product;
import com.product.exception.ProductException;

/**
 * @author Manohar Perumal
 * 
 * Interface - Product DAO
 *
 */
public interface ProductDAO {
	
	public Product findById(int productId) throws ProductException;
	
	public List<Product> findAll() throws ProductException;
	
	public Product save(Product product) throws ProductException;

}
