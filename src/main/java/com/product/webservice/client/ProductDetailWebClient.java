package com.product.webservice.client;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
@PropertySource("classpath:application.properties")
public class ProductDetailWebClient {
	
	private static final Logger log = LoggerFactory.getLogger(ProductDetailClient.class);
	
	@Value("${api.url}")
	private String apiUrl;
	
	@Value("${api.excludes}")
	private String excludes;
	
	public String getProductNameById(int productId) {
		String productName = "";
		try {
			String getURL = this.apiUrl + productId + excludes;
			
			log.info(getURL);
			
			WebClient webClient = WebClient.create(this.apiUrl);
			
			Mono<ClientResponse> result = webClient.get()
					.uri(productId + excludes)
					.accept(MediaType.APPLICATION_JSON)
					.exchange();
					
			String responseEntity = result.flatMap(res -> res.bodyToMono(String.class)).block();
			
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
