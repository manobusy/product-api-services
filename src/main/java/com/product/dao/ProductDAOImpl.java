package com.product.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.dto.Product;
import com.product.exception.ProductException;
import com.product.repository.DBProductRepository;

/**
 * @author Manohar Perumal
 * 
 * Implementation - Product DAO Impl
 *
 */
@Component
public class ProductDAOImpl implements ProductDAO{
	
		@Autowired
		private DBProductRepository dbProductRepository;	
		
		//Find All
		public List<Product> findAll() throws ProductException {
			List<Product> products = dbProductRepository.findAll(); 
			return products;
		}
		
		//Find By Id
		public Product findById(int productId) throws ProductException {
			Product product = dbProductRepository.findById(productId);
			return product;
		}
		
		//Save
		public Product save(Product product)  throws ProductException{
			dbProductRepository.save(product);
			return product;
		}

}
