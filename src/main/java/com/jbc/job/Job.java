package com.jbc.job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.jbc.dao.CouponRepositoryImpl;
import com.jbc.dao.CustomerRepositoryImpl;
import com.jbc.model.Coupon;
import com.jbc.model.CouponPurchase;
import com.jbc.model.Customer;
//job class
@Component
@Qualifier("Job")
public class Job implements Runnable{
	@Autowired
	@Qualifier("CouponRepositoryImpl")
	CouponRepositoryImpl dbCoupon;
	@Autowired
	@Qualifier("CustomerRepositoryImpl")
	CustomerRepositoryImpl dbCustomer;
	
	private volatile boolean running = true;

	public Job() {}
	//method to start the thread
	public void start() {
		Thread t = new Thread(this);
		t.start();
	}
	//checks if the coupons are out of date than deletes the coupons from all the customers if the coupons are out of date
	@Override
	public void run() {
		while(running) {
			List<Coupon> CouponList = dbCoupon.findAllCoupons();
			List<Customer> CustomerList = dbCustomer.findAllCustomers();
			Date today = new Date(System.currentTimeMillis());
			for(int i = 0; running && i<CouponList.size();i++) {
				if(CouponList.get(i).getEndDate().getTime() <= today.getTime()) {
					for(int j = 0; j<CustomerList.size();j++) {
						CouponPurchase cp = new CouponPurchase();
						cp.setCouponId(CouponList.get(i).getId());
						cp.setCustomerId(CustomerList.get(j).getId());
						dbCustomer.deleteCouponPurchase(cp);
					}
					dbCoupon.deleteById(CouponList.get(i).getId());
				}
			}
		}
	}
	//method to quit the job 
	public void quit() {
		this.running = false;
	}
}
