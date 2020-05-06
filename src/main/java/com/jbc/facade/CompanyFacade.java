package com.jbc.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jbc.model.Category;
import com.jbc.model.Company;
import com.jbc.model.Coupon;
import com.jbc.model.CouponPurchase;
import com.jbc.model.Customer;
@Service
public class CompanyFacade extends ClientFacade{

	private int companyId;

	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId+1;
	}
	@Override
	public boolean login(String email, String password) {
		return CompanyDao.existByEmailAndPassword(email, password);
	}
	public void updateCoupon(Coupon coupon) {
		coupon.setCompanyId(companyId);
		CouponDao.updateCoupon(coupon);
	}
	public void deleteCoupon(int couponId) {
		for(Customer  c : CustomerDao.findAllCustomers()) {
			CouponPurchase cp = new CouponPurchase();
			cp.setCouponId(couponId);
			cp.setCustomerId(c.getId());
			CustomerDao.deleteCouponPurchase(cp);
		}
		CouponDao.deleteById(couponId);
	}
	public void addCoupon(Coupon coupon) {
		boolean check = true;
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(getCompanyCouponsId()!=null) {
			list = new ArrayList<Integer>(getCompanyCouponsId());
			for(int c : getCompanyCouponsId()) {
				if(CouponDao.FindCouponById(c).get().getTitle() == coupon.getTitle()) {
					check = false;
					break;
				}
			}
		}
		if(check) {
			CouponDao.addCoupon(coupon);
			list.add(coupon.getId());
			CompanyDao.addCompanyCoupon(list,companyId);
		}
	}
	public List<Coupon> getCompanyCoupons(){
		ArrayList<Coupon> list = new ArrayList<Coupon>();
		if(getCompanyCouponsId()!=null)
			for(int i : getCompanyCouponsId()) {
				list.add(CouponDao.FindCouponById(i).get());
			}
		return list;
	}
	private List<Integer> getCompanyCouponsId(){
		return new ArrayList<Integer>(CompanyDao.FindCompanyById(companyId).getCouponIds());
	}
	public List<Coupon> getCompanyCoupons(Category category){
		ArrayList<Coupon> list = new ArrayList<Coupon>(getCompanyCoupons());
		for(int i = 0;i<list.size();i++) {
			if(!list.get(i).getCategory().equals(category)) {
				list.remove(i);
			}
		}
		return list;
	}
	public List<Coupon> getCompanyCoupons(float maxPrice){
		ArrayList<Coupon> list = new ArrayList<Coupon>(getCompanyCoupons());
		for(int i = 0;i<list.size();i++) {
			if(list.get(i).getPrice() > maxPrice) {
				list.remove(i);
			}
		}
		return list;
	}
	public Company getCompanyDetails() {
		return CompanyDao.FindCompanyById(companyId);
	}



}
