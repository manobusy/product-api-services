package com.product.webservice.client;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Manohar Perumal
 * 
 * External Rest API to get the product name by product Id
 *
 */
@Component
@Configuration
@PropertySource("classpath:application.properties")
public class ProductDetailClient {
	
	private static final Logger log = LoggerFactory.getLogger(ProductDetailClient.class);
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@Value("${api.url}")
	private String apiUrl;
	
	@Value("${api.excludes}")
	private String excludes;
	
	public String getProductNameById(int productId) {
		String productName = "";
		try {
			String getURL = this.apiUrl + productId + excludes;
			
			log.info(getURL);
			
			String responseEntity = restTemplate.getForObject(getURL, String.class);
			
			if(null != responseEntity) {
				JSONObject responseObj = new JSONObject(responseEntity);
				JSONObject compositeResponse = responseObj.getJSONObject("product");
				JSONObject item = compositeResponse.getJSONObject("item");
				if(item.has("product_description")){
					JSONObject productDescription = item.getJSONObject("product_description");
					productName = productDescription.getString("title");
				}
			}
		} catch (RestClientException e) {
			log.error("Exception occured while fetching product name from external API.");
		} catch (JSONException e) {
			log.error("Exception occured while parsing JSON Response.");
		} catch (Exception e) {
			log.error("Unknow exception occured while fetching product name from external API.");
		}
		return productName;
	}
}
