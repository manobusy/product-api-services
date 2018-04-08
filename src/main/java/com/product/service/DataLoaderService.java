package com.product.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.dto.Product;
import com.product.exception.ProductException;
import com.product.repository.DBProductRepository;

@Service
public class DataLoaderService {
	
	private static final Logger log = LoggerFactory.getLogger(DataLoaderService.class);
	
	@Autowired
	private DBProductRepository dbProductRepository;
	
	//constructor
	public DataLoaderService() {};

	@PostConstruct
	public void init() throws ProductException{
		loadProductPriceInDB();
	}
	
	//Load the products in DB
	private void loadProductPriceInDB() {


		if(dbProductRepository != null) {

			List<Product> prodList = new ArrayList<Product>();
			Product product1 = new Product(13860428,13.49,"USD");
			prodList.add(product1);
			
			Product product2 = new Product(15117729,119.49,"USD");
			prodList.add(product2);
			
			Product product3 = new Product(16483589,299.49,"USD");
			prodList.add(product3);
			
			Product product4 = new Product(16696652,167.49,"USD");
			prodList.add(product4);
			
			Product product5 = new Product(16752456,123.49,"USD");
			prodList.add(product5);
			
			Product product6 = new Product(15643793,189.49,"USD");
			prodList.add(product6);			
			
			//Deleting any data before load
			log.info("Deleting all the product records");
			dbProductRepository.deleteAll();
			
			prodList.forEach(product -> dbProductRepository.save(product));
			
			log.info("Adding all the product records");

		}
	}

}
