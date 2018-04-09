package com.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.dao.ProductDAO;
import com.product.dto.Product;
import com.product.exception.ProductException;
import com.product.webservice.client.ProductDetailClient;
import com.product.webservice.client.ProductDetailWebClient;

/**
 * @author Manohar Perumal
 * 
 * Implementation - Product Service
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDetailClient productDetailClient;
	
	@Autowired
	private ProductDetailWebClient productDetailWebClient;
	
	@Autowired
	private ProductDAO productDAO;

	//Find All
	public List<Product> getAllProducts() throws ProductException {
		List<Product> products = productDAO.findAll();
		products.stream()
		.map(product -> getProductName(product))
		.toArray();
		return products;
	}
	
	//Find All - Parallel Stream
	public List<Product> getParallelAllProducts() throws ProductException {
		List<Product> products = productDAO.findAll();
		products.parallelStream()
		.map(product -> getProductName(product))
		.collect(Collectors.toList());
		return products;
	}
	
	//Get Product Name - Call the client service to get the product name
	public Product getProductName(Product product) {
		String name = productDetailClient.getProductNameById(product.getId());
		product.setName(name);
		return product;
	}

	@Override
	public Product getProductById(int productId) throws ProductException {
		Product product = productDAO.findById(productId);
		product.setName(productDetailWebClient.getProductNameById(product.getId()));
		return product;
	}

	@Override
	public String saveProduct(Product product) throws ProductException {
		product = productDAO.save(product);
		return "Product Saved Successfully";
	}

}
