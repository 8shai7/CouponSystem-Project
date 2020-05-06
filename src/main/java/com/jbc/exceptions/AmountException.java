package com.jbc.exceptions;
//amount exception 
public class AmountException extends Exception {
	/**
	 * Amount is invalid
	 */
	private static final long serialVersionUID = 1L;
	public AmountException(String message) {
		super(message);
	}
	//customized exception
	public AmountException() {
		super("Purchase Exception: Cannot Purchase Coupon - Coupon Amount is 0");
	}
}
