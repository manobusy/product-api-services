package com.product.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.Product;
import com.product.exception.ProductException;
import com.product.service.ProductService;


/**
 * @author Manohar Perumal
 * 
 *This controller API is used to manage the Product Information
 *
 */
@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	/*@RequestMapping("/landing")
	public String landing(){
		return "Welcome to Products New Display Page";
	}*/

	@GetMapping(value = "/products", produces = "application/json; charset=UTF-8")
	public List<Product> getAllProducts() throws ProductException{
		return productService.getParallelAllProducts();
	}

	@GetMapping(value = "/products/{id}", produces = "application/json; charset=UTF-8")
	public Product getProductById(@PathVariable("id") Integer id) throws ProductException{
		return productService.getProductById(id);
	}

	@SuppressWarnings("unchecked")
	@PutMapping(value = "/products/{id}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<?>  update(@PathVariable Integer id, @RequestBody Product product)  throws ProductException {
		String response = productService.saveProduct(product);
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("Response", response);
		return new ResponseEntity(map, HttpStatus.CREATED);
	}

}
