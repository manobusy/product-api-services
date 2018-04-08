package com.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Manohar Perumal
 * 
 *This controller API is used to manage the Product Information
 *
 */
@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	@RequestMapping("/landing")
	public String landing(){
		return "Welcome to Products New Display Page";
	}

}
