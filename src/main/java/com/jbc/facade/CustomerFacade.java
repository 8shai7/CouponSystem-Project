package com.jbc.facade;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jbc.exceptions.AmountException;
import com.jbc.exceptions.CouponDateException;
import com.jbc.model.Category;
import com.jbc.model.Coupon;
import com.jbc.model.CouponPurchase;
import com.jbc.model.Customer;
@Service
public class CustomerFacade extends ClientFacade{

	private int customerId;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public boolean login(String email, String password) {
		return (CustomerDao.existByEmailAndPassword(email, password));
	}
	public void purchaseCoupon(Coupon coupon) {
		try {
			Calendar cal = Calendar.getInstance();
			Date today = new Date(cal.getTimeInMillis());
			if(coupon.getId()>=0)
				if(today.after(coupon.getEndDate())){
					if(today.before(coupon.getStartDate()))
					{
						if(coupon.getAmount()>0) {
							CouponPurchase cp = new CouponPurchase();
							cp.setCouponId(coupon.getId());
							cp.setCustomerId(customerId);
							CustomerDao.addCouponPurchase(cp);
							coupon.setAmount(coupon.getAmount()-1);
							CouponDao.updateCoupon(coupon);
						}else
							throw new AmountException();
					}else {
						throw new CouponDateException();
					}
				}else
					throw new CouponDateException();
		}catch (AmountException e) {
			System.out.println(e.getMessage());
		} catch (CouponDateException e) {
			System.out.println(e.getMessage());
		}
	}
	public List<Coupon> getCustomerCoupons(){
		if(CustomerDao.FindCustomerById(customerId).isPresent())
			return CustomerDao.FindCustomerById(customerId).get().getCoupons();
		return new ArrayList<Coupon>();
	}
	public List<Coupon> getCustomerCoupons(float maxPrice){
		List<Coupon> list = new ArrayList<Coupon>();
		if(getCustomerDetails()!=null) {
			list = getCustomerDetails().getCoupons();
			for(int i = 0;i<list.size();i++) {
				if(list.get(i).getPrice() > maxPrice) {
					list.remove(i);
				}
			}
		}
		return list;
	}
	public List<Coupon> getCustomerCoupons(Category category){
		List<Coupon> list = new ArrayList<Coupon>();
		if(getCustomerDetails()!=null) {
			list = getCustomerDetails().getCoupons();
			for(int i = 0;i<list.size();i++) {
				if(!(list.get(i).getCategory().equals(category))) {
					list.remove(i);
				}
			}
		}
		return list;
	}
	public Customer getCustomerDetails() {
		if(CustomerDao.FindCustomerById(customerId).isPresent())
			return CustomerDao.FindCustomerById(customerId).get();
		return null;
	}
}
