package com.jbc.exceptions;
//coupon date exception
public class CouponDateException extends Exception {
	/**
	 * Coupon Date is invalid
	 */
	private static final long serialVersionUID = 1L;
	public CouponDateException(String message) {
		super(message);
	}
	//customized exception for outdated coupon
	public CouponDateException() {
		super("Purchase Exception: Cannot Purchase Coupon - Coupon Date is either expired or didn't start yet");
	}
}
