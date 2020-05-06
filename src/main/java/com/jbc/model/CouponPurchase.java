package com.jbc.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
@Entity
@Scope("prototype")
public class CouponPurchase {
	@Id
	private int couponId;
	private int customerId;

	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	@Override
	public String toString() {
		return "CouponPurchaseId [couponId=" + couponId + ", customerId=" + customerId + "]";
	}
	
	
}
