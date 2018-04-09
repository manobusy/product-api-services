package com.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.dto.Product;
import com.product.exception.ProductException;
import com.product.repository.DBProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductApiServiceApplicationTests {

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	// Required to delete the data added for tests.
	// Directly invoke the APIs interacting with the DB
	@Autowired
	private DBProductRepository dbProductRepository;

	// Test RestTemplate to invoke the APIs.
	@Autowired
	private RestTemplate restTemplate;
	

	/**
	 * This JUnit test deletes all data from Product table
	 * 
	 * @throws ProductException
	 */
	@Before
	@Transactional
	public void setup() throws ProductException {
		dbProductRepository.deleteAll();
	}

	/**
	 * This JUnit test validates GET rest api
	 * 
	 * @throws ProductException
	 * @throws JSONException 
	 */
	@Test
	public void testGetAllProducts() throws ProductException, JSONException {
		// Add some test data for the API
		Product product1 = new Product(12345, 22.25, "USD");
		dbProductRepository.save(product1);

		Product product2 = new Product(45678, 99.99, "USD");
		dbProductRepository.save(product2);

		// Invoke the API
		String apiResponse = restTemplate.getForObject(
				"http://localhost:8080/api/v1/products/", String.class);

		JSONArray productList = new JSONArray(apiResponse);

		int totalProducts = productList.length();

		assertTrue(totalProducts == 2);

		// Delete the test data created
		dbProductRepository.delete(product1);
		dbProductRepository.delete(product2);
	}

	/**
	 * This JUnit test validates the GET rest api by product id
	 * 
	 * @throws ProductException
	 * @throws JSONException 
	 */
	@Test
	public void testGetProduct() throws ProductException, JSONException {
		// Add some test data for the API
		// name for below product from target api is BIG LEBOWSKI, THE Blu-ray
		Product product = new Product(13860428, 22.25, "USD");
		dbProductRepository.save(product);

		// Invoke the API
		String apiResponse = restTemplate.getForObject(
				"http://localhost:8080/api/v1/products/13860428", String.class);

		JSONObject jsonResponse = new JSONObject(apiResponse);

		assertEquals(13860428, jsonResponse.getLong("id"));

		assertEquals("The Big Lebowski (Blu-ray)",
				jsonResponse.getString("name"));

		// Delete the test data created
		dbProductRepository.delete(product);
	}

	/**
	 * This JUnit test validates the delete operation
	 * 
	 * @throws ProductException
	 */
	@Test
	public void testDeleteProduct() throws ProductException {
		// Create a new product using the ProductRepository API
		Product product = new Product(12345, 22.25, "USD");
		dbProductRepository.save(product);

		int productId = product.getId();

		dbProductRepository.delete(product);

		// Try to fetch from the DB directly
		Product productFromDb = dbProductRepository.findById(productId);

		// and assert that there is no data found
		assertNull(productFromDb);
	}
	
	/**
	 * This JUnit test validates the PUT rest api operation
	 * 
	 * @throws ProductException
	 * @throws JsonProcessingException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testPutProductAPI () throws ProductException, JsonProcessingException {
		// Create a new product using the ProductRepository API
		int id = 16483589;
		
		Product product = new Product(id, 218.25, "USD");
		dbProductRepository.save(product);
		
		//update the price and currency info
		double updatedPrice = 59.99;
		String updatedCurrency = "CAD";
		Map<String, Object> current_price  = new HashMap<String, Object>();
		current_price.put("value", updatedPrice);
		current_price.put("currency_code", updatedCurrency);
		
		//Request body
		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("id", id);
		requestBody.put("current_price", current_price);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		//Creating entity object
		HttpEntity<String> httpEntity =
		      new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

		//Invoke the API
		Map<String, Object> apiResponse = (Map)restTemplate.exchange("http://localhost:8080/api/v1/products/" + id,
		      HttpMethod.PUT, httpEntity, Map.class, Collections.EMPTY_MAP).getBody();

		assertNotNull(apiResponse);
		assertTrue(!apiResponse.isEmpty());	
		
		//Asserting the response of the API.
		Map<String,Object> price = (Map<String,Object>) apiResponse.get("current_price");
		
		//updated values should reflect in response
		assertEquals(price.get("value").toString(), String.valueOf(updatedPrice));
		assertEquals(price.get("currency_code").toString(), updatedCurrency);
		
		Object name = apiResponse.get("name");
		//This shouldn't be null
		assertNotNull(name);
		
		//Delete the data added for testing
		dbProductRepository.delete(product);
	}
	
	/**
	 * This jUnit test is meant to insert the valid data into product table
	 * 
	 * @throws ProductException
	 */
	@Test
	public void testSaveAllProducts() throws ProductException {
		Product product = new Product(15117729, 399.49, "USD");
		dbProductRepository.save(product);
		
		product = new Product(13860428, 13.49, "USD");
		dbProductRepository.save(product);
		
		product = new Product(16696652, 99.49, "USD");
		dbProductRepository.save(product);
		
		
		product = new Product(16752456, 259.49, "USD");
		dbProductRepository.save(product);
		
		product = new Product(16483589, 699.49, "USD");
		dbProductRepository.save(product);		
	}

}
