package com.product.exception;

/**
 * @author Manohar Perumal
 * 
 * This is a wrapper object for exception
 *
 */
public class ProductException extends Exception {
	private static final long serialVersionUID = -2135378215452746720L;
	
	private int errorCode;
	private String errorMessage;

	public ProductException() {
		super();
	}
	
	public ProductException(String msg) {
		super(msg);
	}
	
	public ProductException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public ProductException(int errorCode,String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorCode= errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
