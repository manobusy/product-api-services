package com.product.exception;

/**
 * @author Manohar Perumal
 * 
 * This is a wrapper object for exception
 *
 */
public class ProductException extends Exception {
	private static final long serialVersionUID = -2135378215452746720L;

	public ProductException() {
		super();
	}
	
	public ProductException(String msg) {
		super(msg);
	}
	
	public ProductException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
