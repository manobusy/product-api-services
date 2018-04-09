package com.product.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.product.exception.ProductException;

/**
 * @author Manohar Perumal
 * 
 * Exception handler for the Rest response
 *
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends
		ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

	@ExceptionHandler(value = { ProductException.class, Throwable.class })
	protected ResponseEntity<Object> handleRuntimeException (Throwable ex, WebRequest request) {
		String bodyOfResponse = "An error occured. Cannot process your request at this time.";
		log.error(bodyOfResponse);
		return new ResponseEntity<Object>(bodyOfResponse, HttpStatus.FAILED_DEPENDENCY) ;
	}
	
	
}
