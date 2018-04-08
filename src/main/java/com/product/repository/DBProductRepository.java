package com.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.product.dto.Product;

/**
 * @author Manohar Perumal
 * 
 * Get the Repository Product Object
 *
 */
@Repository
public interface DBProductRepository extends MongoRepository <Product,String> {
    
	public Product findById(int id);
	
}
